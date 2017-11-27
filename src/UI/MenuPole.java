/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logika.Hra;
import logika.IHra;
import main.Main;


/**
 * Třída MenuPole umožňuje zobrazení podpůrných prvků adventury. 
 * 
 *
 * @author dobm03, Jan Ženíšek
 * ZS 2017/2018
 */
public class MenuPole extends MenuBar{
    
    private Main main;
    
    private IHra hra;
    private Stage stage;
    
    /**
     * konstruktor hry s parametrem main - staruje novou hru
     * @param main 
     */
    public MenuPole(IHra hra, Main main){
        this.hra = hra;
        this.main = main;
        this.stage = main.getPrimaryStage();
        
        this.main=main;
        init();
    }
    
    /**
     * metoda init vytváří celkové "podloží" pro všechny UI prvky
     * v této metodě se postupně inicializují prvky GUI
     */
    private void init(){
        Menu menuSoubor = new Menu("Advenura");
        
        MenuItem itemNovaHra = new MenuItem("Nová hra");
//        MenuItem itemNovaHra = new MenuItem(new Image("Nová hra", new ImageView(Main.class.getResourceAsStream("/zdroje/ikona.png"))));
        itemNovaHra.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        
        MenuItem itemKonec = new MenuItem("Konec");
        
        
        
        
        
        Menu menuHelp = new Menu("Help");
        MenuItem itemOProgramu = new MenuItem("O programu");
        MenuItem itemNapoveda = new MenuItem("Napoveda");
        
        menuSoubor.getItems().addAll(itemNovaHra, itemKonec);
        menuHelp.getItems().addAll(itemOProgramu, itemNapoveda);
        this.getMenus().addAll(menuSoubor, menuHelp);
        
        /**
         * Metoda handleru pro vypsaní info o adventuře
         */
        itemOProgramu.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("O adventure");
               alert.setHeaderText("Dobrodružství poslíčka Luigiho");
               alert.setContentText("V této hře jste poslíček jménem Luigi, který má za úkol donést zásilku do vzdálené radnice. "
                       + "Zásilku si vyzvedněte v nedaleké pizzerii, již tam na Vás čeká. Po cestě se snažte mluvit s postavami (NPC), mohou Vám v mnohém poradit. Vyhýbejte se ale viditelně nekalým živlům a nebezpečným místům. "
                       + "Můžete také sbírat předměty do batohu, některé Vám pomohou, některé ne. Některé si s sebou z logiky věci ani vzít nemůžete (např. stůl). Pokud Vás některá z NPC požádá o nějaký předmět, jednoduše jej z batohu odložte do lokace. "
                       + "Doufejte, že jí to bude stačit. K dokončení hry potřebujete také mít minimálně 1 život, proto se svým zdravím pokud možno šetřete. Pokud do cíle dorazíte jako oškubané kuře, zákazník pravděpodobně zásilku nepřijme. "
                       + "Až dorazíte do cíle (radnice), nezapomeňte hezky pozdravit (otazka Radni), budou Vám podány klíčové instrukce k dokončení hry.");
               alert.initOwner(main.getPrimaryStage());
               alert.showAndWait();
            }
        });
        /**
         * eventhandler zaručující načtení nápovědy ze souboru formátu .html
         * načítá se na  tlačítko "Napoveda" a vytváří stage okno o rozměrech 500x500px
         */
        itemNapoveda.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                stage.setTitle("Napoveda");
                WebView webview = new WebView();
                
                webview.getEngine().load(Main.class.getResource("/zdroje/napoveda.html").toExternalForm());
                
                stage.setScene(new Scene(webview, 500, 500));
                stage.show();
            }
        });
        
        /*
         *  metoda pro ukonečení hry
         */
        itemKonec.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        
        /**
         * metoda zavolá novou instanci hry
         */
        itemNovaHra.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //main.novaHra(); 
                main.start(stage);
                
            }
        });
        
    }
    
}
