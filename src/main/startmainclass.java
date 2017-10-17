/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
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
        Button btn = new Button();
        btn.setText("Start adventura'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
               IHra hra = new Hra();
               TextoveRozhrani textoveRozhrani = new TextoveRozhrani(hra);
               textoveRozhrani.hraj();
            }
        });
        
        StackPane root = new StackPane();
        //mohu vkládat objekt do objektu (např. VBOX do border pane TOP)
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
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
    

