/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import UI.GUIbatoh;
import UI.Mapa;
import UI.MenuPole;
import UI.GUILokacePredmety;
import UI.GUIVychody;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logika.HerniPlan;
import logika.Hra;
import logika.IHra;
import uiText.TextoveRozhrani;
/**
 *
 * @author dobm03
 * ZS 2017/2018
 */

/**
 * Třída main zahajuje hru, kvůli GUI extenduje obecného předka "Application"
 * @author MRCS-PC
 */
public class startmainclass extends Application {
    
    private Mapa mapa;
    private MenuPole menu;
    private IHra hra;
    private TextArea centerText;
    private Stage primaryStage;
    private TextField zadejPrikazTextField;
    
    private GUIbatoh GUIbatoh;
    private GUIVychody panelVychodu;
    private GUILokacePredmety panelPredmetu;
    
   
    
    

    /**
     * konstruktor hry, vytváří komplexní podloží pro UI prvky (stage)
     * @param primaryStage 
     */
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage=primaryStage;
        hra = new Hra();
        mapa = new Mapa(hra);
        menu = new MenuPole(hra, this);
        
        
         
        BorderPane borderPane = new BorderPane();
        BorderPane borderPaneItemOut = new BorderPane();
        BorderPane borderPaneBackp = new BorderPane();
        
        centerText = new TextArea();
        centerText.setText(hra.vratUvitani());
        centerText.setEditable(false); //nejde enterovat - commit příkaz
        
        borderPane.setCenter(centerText);
        Label zadejPrikazLabel = new Label ("Zadej prikaz");
        zadejPrikazLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        
        TextField zadejPrikazTextField = new TextField ("Zde zadejte prikaz");
        zadejPrikazTextField.setOnAction(new EventHandler<ActionEvent>() {
        
            /**
             * Metoda zabezpečující komunikaci s textovým rozhraním hry, pomocí textového vstupu dojde k zadání informací, které se graficky zobrazí v příslušném poli
             * @param event 
             */
        @Override
        public void handle (ActionEvent event) {
            String zadanyPrikaz = zadejPrikazTextField.getText();
            String odpoved = hra.zpracujPrikaz(zadanyPrikaz);
            
            centerText.appendText("\n\n" + zadanyPrikaz + "\n\n");
            centerText.appendText("\n\n" + odpoved + "\n\n");
            zadejPrikazTextField.setText("");
            
            if(hra.konecHry()) {
                zadejPrikazTextField.setEditable(false);
            }
        }
        });
        
    
        
        
        FlowPane dolniPanel = new FlowPane();
        dolniPanel.setAlignment(Pos.CENTER);
        dolniPanel.getChildren().addAll(zadejPrikazLabel, zadejPrikazTextField);
        
        //spodek panel horejsek menu 
        borderPane.setBottom(dolniPanel);
        borderPane.setTop(menu);
        
        
         // panel Předmětů  
         panelPredmetu = new GUILokacePredmety(hra.getHerniPlan(),centerText);
         borderPaneItemOut.setLeft(panelPredmetu.getList());
         
        // panel východů
         panelVychodu = new GUIVychody(hra.getHerniPlan(),centerText,zadejPrikazTextField);       
         borderPaneItemOut.setRight(panelVychodu.getList());
         borderPane.setRight(borderPaneItemOut);
         
         // panel batohu
         GUIbatoh = new GUIbatoh(hra.getHerniPlan(),centerText);
         borderPaneBackp.setRight(GUIbatoh.getList());
           
         // panel s mapou
         borderPaneBackp.setTop(mapa);
         borderPane.setLeft(borderPaneBackp);
        
        
        
        /*Button btn = new Button();
        btn.setText("Start adventura'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
               IHra hra = new Hra();
               TextoveRozhrani textoveRozhrani = new TextoveRozhrani(hra);
               textoveRozhrani.hraj();
            }
        });
        */
        //StackPane root = new StackPane();
        //mohu vkládat objekt do objektu (např. VBOX do border pane TOP)
        //root.getChildren().add(btn);
        
        Scene scene = new Scene(borderPane, 1600, 950); 
        
        primaryStage.setTitle("Moje adventura");
        primaryStage.setScene(scene);
        primaryStage.show();
        zadejPrikazTextField.requestFocus();
    }
    
    /**
     * Metoda pomocí zadání příslušného parametru zahájí hru v textovém či grafickém modu
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(args.length == 0){ //play in GUI
            launch(args);
        }
        else {
            if(args[0].equals("-text")) {
                IHra hra = new Hra(); //play in text UI
                TextoveRozhrani textoveRozhrani = new TextoveRozhrani(hra);
                textoveRozhrani.hraj();
            } else {
                System.out.println("Neplatny parametr");
                System.exit(1);
            }
        }
        /**
         * metoda zavolání nové hry
         */
        }
        public void novaHra() {
            hra = new Hra();
            centerText.setText(hra.vratUvitani());
            mapa.novaHra(hra); 
        }

    /**
     * Getter pro primary stage (vykreslují se UI prvky)
     * @return the primaryStage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
        
    }
    

