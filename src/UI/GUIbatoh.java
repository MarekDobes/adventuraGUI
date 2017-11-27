/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import logika.Batoh;
import logika.HerniPlan;
import logika.IHra;
import logika.Predmet;
import utils.Observer;


/**
 * Třída GUIBatoh vypisuje real time predmety nachazejici se v batohu formou .png image. 
 * Rozšiřuje ListView a používá integrálně observera na hernimPlanu.
 * Díky integrálnímu observeru dokáže zareagovat na změny v Batohu, 
 * tedy změny obsahu Předmětů v tomto Batohu (předměty z/do batohu z/do lokace).
 * @author dobm03
 * ZS 2017/2018
 */

public class GUIbatoh implements Observer{
    
    private HerniPlan plan;
    ListView<Object> Lview;
    ObservableList<Object> infoPred;
    private Batoh batoh;
    private int var;
    
    private TextArea centerText;

    /*
    * Konstruktor pro batoh.
    */
    public GUIbatoh(HerniPlan plan, TextArea text) {
       this.plan = plan;
       plan.registerObserver(this);
       centerText = text;
       init();
    }

    /*
    * Metoda zavádí ListView, ve kterém se zobrazí předměty v Batohu. Seznam reaguje na přidání/odebrání předmětů z/do batohu.
    */
    private void init() {
         
        
        infoPred = FXCollections.observableArrayList();
        
        Lview = new ListView<>();
        Lview.setItems(infoPred);
        Lview.setPrefWidth(150);
        
        /**
         * Metoda umožňující přidání předmětu z batohu do lokace na "klik myši".
         */
        
        Lview.setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent click)
            {
                if (click.getClickCount() == 1) 
                {
                 
                    Map<String, Predmet> seznamPredmetu = seznamPredmetu = plan.getBatoh().vratBatoh();
                    
                    int selindex = Lview.getSelectionModel().getSelectedIndex();
                    var = 0;
                    String nazev = null;

                    for (String prom : seznamPredmetu.keySet()) 
                    {
                       if(var == selindex){
                           
                           nazev = prom;
                       }
                       var=var+1;
                       
                    }
                    
                    String iPrikaz = "poloz "+nazev;
                    String zpracuj = plan.getHra().zpracujPrikaz("poloz "+nazev);

                
                    centerText.appendText("\n" + iPrikaz + "\n");
                    centerText.appendText("\n" + zpracuj + "\n");
                    
               
                    plan.notifyAllObservers();
                }
            }
        });
        update();
    }
    
    /*
    * Metoda update() provádí aktualizaci seznamu .png image (predmety v batohu). Je programem zavolána, kdykoli dojde k jakékoli změně v obsahu, následně také při startu nové hry (změněný stav se neutralizuje a vrací se výchozí).
    * Postup: 1) neutralizuje se výchozí seznam věcí
    *         2) dle názvu jednotlivých předmětů se najde ekvivalentní se stejným jménem ve zdrojích .png
    *         3) Příslušný .png image se poté přidá do ListView
    * Když klikneme na image, zavolá se Handler, který vykoná příkaz poloz nazevPredmetu
    * CenterText toto poté rozšíří o textovou podobu příkazu a printne odpověď.
    */
       @Override 
    public void update() 
    {        
        Map<String, Predmet> seznamPredmetu; //zavedení seznamuPredmetu
        seznamPredmetu = plan.getBatoh().vratBatoh(); //load dat
        
        infoPred.clear();
        
        for (String identPred : seznamPredmetu.keySet()) 
        {
            
        Predmet var = seznamPredmetu.get(identPred); //dosazení hodnoty názvu x do proměnné -->níze poté získání identu zdroje
        ImageView obrazek = new ImageView(new Image(main.Main.class.getResourceAsStream("/zdroje/"+var.getObrazek()), 100, 100, false, false));
        
        infoPred.add(obrazek);
        }
    }
    
    
    /*
    * Metoda vrací list.
    */
    public ListView<Object> getList() {
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


