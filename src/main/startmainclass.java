/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logika.Hra;
import logika.IHra;
import uiText.TextoveRozhrani;
/**
 *
 * @author dobm03
 */
public class startmainclass extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        IHra hra = new Hra();
         //TextoveRozhrani textoveRozhrani = new TextoveRozhrani(hra);
        // textoveRozhrani.hraj();
        BorderPane borderPane = new BorderPane();
        
        TextArea centerText = new TextArea();
        //Text centerText = new Text();
        centerText.setText(hra.vratUvitani());
        centerText.setEditable(false); //nejde enterovat - commit příkaz
        
        borderPane.setCenter(centerText);
        Label zadejPrikazLabel = new Label ("Zadej prikaz");
        zadejPrikazLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        
        TextField zadejPrikazTextField = new TextField ("Sem zadej prikaz");
        zadejPrikazTextField.setOnAction(new EventHandler<ActionEvent>() {
        
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
        borderPane.setBottom(dolniPanel);
        
        
    
        
       
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
        
        Scene scene = new Scene(borderPane, 400, 350);
        
        primaryStage.setTitle("Moje adventura");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(args.length == 0){
            launch(args);
        }
        else {
            if(args[0].equals("-text")) {
                IHra hra = new Hra();
                TextoveRozhrani textoveRozhrani = new TextoveRozhrani(hra);
                textoveRozhrani.hraj();
            } else {
                System.out.println("Neplatny parametr");
                System.exit(1);
            }
        }
        }
        
        
    }
    

