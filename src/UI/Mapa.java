/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import logika.IHra;
import main.Main;
import utils.Observer;

/**
 *Třída Mapa umožňuje zobrazení hráče na image mapy
 * 
 * @author dobm03, Jan Ženíšek
 * ZS 2017/2018
 */
public class Mapa extends AnchorPane implements Observer{

    private IHra hra;
    private Circle tecka; //privátní proměnná tečka (typu Circle)
    
    
    
    /**
     * Konstruktor s interace hry, registruje Observera na herní plán a inicializuje pomocí init() metody
     * @param hra 
     */
    public Mapa(IHra hra) {
        this.hra=hra;   
        hra.getHerniPlan().registerObserver(this); //registrace Observeru
        
        
        init(); //inicializace konstruktoru
    }
    /**
     * metoda init() - uvedena též v konstruktoru, pomocí ImageView zobrazí hráče=red point na pozadí vyplněné .jpeg mapou
     * Při každé změně volá metodu update() - upozorní observera o změně
     */
    private void init(){ //metoda void init (nahraje se obrázek -->mapa z mainclass (byla původně), na ní bude poté "tečka", tedy 
        ImageView obrazek = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/mapalokaci.jpg"), 800,800, false, false));
        tecka = new Circle(10,Paint.valueOf("red"));
        this.getChildren().addAll(obrazek, tecka);
        update();
    }
    
    /**
     * Metoda update() - viz metoda výše, na AnchorPane vykreslí v 2D zobrazení pozice X a Y tečky
     * Konkértní grid (pozici na osách X a Y si bere z herního plánu)
     */
    @Override
    public void update() {
        this.setTopAnchor(tecka, hra.getHerniPlan().getAktualniLokace().getPosY());
        this.setLeftAnchor(tecka, hra.getHerniPlan().getAktualniLokace().getPosX());
      
    }
    
    
    /**
     * Při změně stavu hry (zavření hry a vytvoření nové instance hry) se nejdříve observer "staré" hry "smaže" 
     * a poté se vytvoří nový observer na nově vytvořené instanci hry (vynutíme si tak focus na novou hru, nepozorujeme tak najednou více instancí)
     * @param hra 
     */
   
    //@Override
    public void novaHra(IHra hra) {
        hra.getHerniPlan().deleteObserver(this); //odmazat staré pozorování
        this.hra= hra; //vytvořit novou instanci 
        hra.getHerniPlan().registerObserver(this); //zavolat nové pozorování (Observer se "přeorientuje" na novou instanci Hry)
        update(); //updatuj vše
    }
    
}
