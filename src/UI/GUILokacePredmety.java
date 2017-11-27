/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import logika.HerniPlan;
import logika.Predmet;
import utils.Observer;

/**
 * Třída GUILokacePredmety vypisuje real time predmety nachazejici se v prostoru formou .png image. 
 * Rozšiřuje ListView a používá integrálně observera na hernimPlanu.
 * Díky integrálnímu observeru dokáže zareagovat na změny v Lokaci, 
 * tedy jméno aktualni Lokace a změny obsahu Předmětů v této lokaci (předměty z/do batohu).
 * 
 * @author dobm03
 * @version ZS 2017/2018
 * 
 */

public class GUILokacePredmety implements Observer{
    private HerniPlan plan;
    private TextArea centerText;
    ListView<Object> Lview; //https://docs.oracle.com/javafx/2/ui_controls/list-view.htm ad 11-2
    ObservableList<Object> infoPred;
    private int var;
    

    /*
    * Konstruktor
    */
    public GUILokacePredmety(HerniPlan plan, TextArea text) {
       this.plan = plan;
       plan.registerObserver(this);
       
       centerText = text;
       init();
    }

    /*
    * Metoda zavádí ListView, ve kterém se zobrazí předměty v Lokaci. Seznam reaguje na přidání/odebrání předmětů z/do Lokace.
    */
    private void init() {
        Lview = new ListView<>();
        infoPred = FXCollections.observableArrayList();
        
        Lview.setItems(infoPred);
        Lview.setPrefWidth(150);
        Lview.setPrefHeight(600);
        
        
        /*
        * Metoda umožňující přidání předmětu z lokace do batohu na "klik myši".
        */
        Lview.setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
            public void handle(MouseEvent click)
            {
                if (click.getClickCount() == 1) 
                {
                    
                    int index = Lview.getSelectionModel().getSelectedIndex();
                    
                    Map<String, Predmet> seznamPredmetu = plan.getAktualniLokace().getPredmety();
                    var=0; //počáteční hodnota
                    String nazev = null;
                    
                    for (String x : seznamPredmetu.keySet()) 
                    {
                       if(var == index) //pokud se index rovná proměnné
                       {
                           nazev = x; //poté key hodnota odpovídá a nazev se loadne
                       }
                       var=var+1; //jinak zvětšení proměnné o 1
                       
                    }
                    
                    String iPrikaz = "vezmi "+nazev;
                    String zpracuj = plan.getHra().zpracujPrikaz("vezmi "+nazev);

                
                    centerText.appendText("\n" + iPrikaz + "\n");
                    centerText.appendText("\n" + zpracuj + "\n");
               
                    plan.notifyAllObservers();
                }
            }
        });
        
        
        update();
    }
    
    /*
    * Metoda vrací list.
    */
    public ListView<Object> getList() {
        return Lview;
    }
    
    /*
    *  Metoda update() provádí aktualizaci seznamu .png image (predmety v lokaci). Je programem zavolána, kdykoli dojde k jakékoli změně v obsahu, následně také při startu nové hry (změněný stav se neutralizuje a vrací se výchozí).
    * Postup: 1) neutralizuje se výchozí seznam věcí
    *         2) dle názvu jednotlivých předmětů se najde ekvivalentní se stejným jménem ve zdrojích .png
    *         3) Příslušný .png image se poté přidá do ListView
    * Když klikneme na image, zavolá se Handler, který vykoná příkaz vezmi nazevPredmetu
    * CenterText toto poté rozšíří o textovou podobu příkazu a printne odpověď.
    */
    @Override 
    public void update() 
    {        
        Map<String, Predmet> seznamPredmetu;
        seznamPredmetu = plan.getAktualniLokace().getPredmety();
        
        infoPred.clear();
        var=0;
        
        for (String listPred : seznamPredmetu.keySet()) 
        {
        Predmet var = seznamPredmetu.get(listPred); //předmět se zást názvem var získá hodnotu příslušného předmětu v Lokaci
        ImageView obrazek = new ImageView(new Image(main.Main.class.getResourceAsStream("/zdroje/"+var.getObrazek()), 120, 120, false, false));
        
        infoPred.add(obrazek);
        }
        
        
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