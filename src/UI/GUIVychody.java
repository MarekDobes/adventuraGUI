/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import logika.HerniPlan;
import logika.IHra;
import main.Main;
import utils.Observer;


/*
 * Třída GUIVychody vypisuje real time vychody z lokace pomoci List View na pravém border panu. 
 * Rozšiřuje ListView a používá integrálně observera na hernimPlanu.
 * Díky integrálnímu observeru dokáže zareagovat na změny v Lokaci, 
 * tedy jméno aktualni Lokace a všechny příslušené východy z dané akt. lokace.
 *
 * @author dobm03
 * @version ZS 2017/2018
 *
*/



public class GUIVychody implements Observer
{

    private HerniPlan plan;
    private TextArea centerText;
    private TextField zadejPrikazTextArea;
    ListView<String> Lview;
    ObservableList<String> infoVych;
    private String noway;

    

    /*
    * Konstruktor
    * Vytvoření panelu východů.
    */
    public GUIVychody(HerniPlan plan, TextArea text,TextField field)
      {
        this.plan = plan;
        plan.registerObserver(this);
        centerText = text;
        zadejPrikazTextArea = field;
        
        init();
      }
    /**
     * přehrání zvuku kroků
     */
private void prehrajKroky(){
       
        String zvuk = main.Main.class.getResource("/zdroje/kroky.mp3").toString();
        Media kroky = new Media(zvuk);
        MediaPlayer mediaPlayer = new MediaPlayer(kroky);
        mediaPlayer.setVolume(0.8);
        mediaPlayer.play();  
    }
    
    /*
    * Metoda init vytvoří list pro východy jako List View.
    */
    private void init()
      {
        infoVych = FXCollections.observableArrayList();
        
        Lview = new ListView<>();
        Lview.setItems(infoVych);
        Lview.setPrefWidth(125);
        Lview.setPrefHeight(400);
        
        
        /*
        * Metoda umožňující přechod z lokace do další lokace (pokud existuje přísl. východ) na "klik myši".
        * Nakonec při přechodu přehraje krátký zvukový vzorek kroků
        */
        Lview.setOnMouseClicked(new EventHandler<MouseEvent>() 
        { 
            @Override
            public void handle(MouseEvent click)
            {
                if (click.getClickCount() == 1) 
                {
                    noway = " Dál už to nepůjde";
                    String iPrikaz = "jdi "+Lview.getSelectionModel().getSelectedItem();
                    String zpracuj = plan.getHra().zpracujPrikaz("jdi "+Lview.getSelectionModel().getSelectedItem());

                    centerText.appendText("\n" + iPrikaz + "\n");
                    centerText.appendText("\n" + zpracuj + "\n");
                    prehrajKroky();
                    
                    if (plan.getHra().konecHry()) {
                    
                    zadejPrikazTextArea.setEditable(false);
                    centerText.appendText(plan.getHra().vratEpilog());
                    centerText.appendText(noway);
                    
                    }
                     
                    plan.notifyAllObservers();
                }
            }
            
        });
        update();
      }
    
    /*
    * Metoda aktualizuje východy, které lze použít v aktuální situaci.
    * Nejdříve si "nahraje" všechny východy k dané lokaci, oddělí seznam východů pomocí " " (mezera) a v rámci 
    * kompletní délky řetězce přidává do dat jednotlivé východy
    */
    @Override
    public void update(){
      
        String vychody = plan.getAktualniLokace().popisVychodu(); //východy do Stringu
        infoVych.clear();
        
        if(vychody!=null){  
        String[] sepVychody = vychody.split(" ");
        for (int index = 0; index < sepVychody.length; index=index+1) {
            
            infoVych.add(sepVychody[index]); //pomocí oddělovače východy separovány, nyní přidány do listu
          }
       
        }
      }

    /*
    * Metoda vrací list.
    */
    public ListView<String> getList()
      {
        return Lview;
      }

    
    /**
     * Metoda zaregistruje pozorovatele k hernímu plánu při spuštění nové hry.
     * @param plan
     */
    public void odkazHerniPlan (HerniPlan plan){
        this.plan = plan;
        plan.registerObserver(this);
        
        this.update();
    }

   


}
