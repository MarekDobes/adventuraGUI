package logika;



/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */

 import java.util.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import utils.Observer;

/*******************************************************************************
 * Batoh představuje samostatnou třídu ve hře. 
 * Umožňuje přidávat předměty(Predmet) do batohu (Map).
 *
 * @author    Marek Dobeš 
 * @version   ZS 2017/2018
 */
public class Batoh
{
    private static final int Kapacita = 4;    // Maximální počet věcí v batohu
    //private List<Predmet> seznamPredmetu;            // Seznam věcí v batohu
    private Map <String, Predmet> predmety; //Mapa předmětů
    


    /**
     *  Konstruktor třídy Batoh (seznam Předmětů --> vyvolá se jako ArrayList)
     */
    public Batoh() {
        //seznamPredmetu = new ArrayList<Predmet>();
        predmety = new HashMap<String,Predmet>();
    }
    
 
    
    /**
     * Metoda sloužící k vložení Předmětů do batohu.
     * 
     * @param   Předmět vkladany
     * @return  Předmět, vrací název Předmětu, který do batohu vkládáme, pokud není možno,
     *          vrací hodnotu null                
     */
    public Predmet vlozPredmet(Predmet vkladany) {
        if (freePlace()==true) {          //freePlace je true, volné místo tedy je
            predmety.put(vkladany.getNazev(),vkladany);
            return vkladany;
        }
        
        return null;
    }
    
    
    
    /**
     * Zjišťuje, zda batoh ještě není plný (zda byla/nebyla překročena max kapacita stanovená v hlavičce).
     * Pokud je volno, nastaví se boolean hodnota true (slouží poté k vlozPredmet).
     * Pokud volno není, vrací boolean false.
     * 
     * @return  true   pokud je dostupné místo.
     *          false  pokud dostupné místo není.
     */
     public boolean freePlace() {
        if (predmety.size() < Kapacita) {
            return true;   
        }        
        
        return false;
    }
    
    
    
    /**
     * Metoda ověřuje stav, zda v batohu je přítomen hledaný předmět nebo není.
     * Pokud je, nastaví se boolean na true, jinak false.
     *  
     * @param   hledam    který předmět hledám
     * @return  true      pokud se předmět v batohu nachází, jinak je false
     */
    public boolean obsahujePredmet(String hledam) {
        if (this.predmety.containsKey(hledam)) {
            return true;
        }
        return false;
    }    
    
    
    
    /**
     * Umožňuje vypsat aktuální seznam předmětů v batohu (slouží např. pro PrikazBatoh)
     * 
     * @return   obsah     na konzoli se vypíše aktuální seznam věcí v batohu
     */
    public String getPredmety() {
        String obsah = "";
        
        for (String nazev : predmety.keySet()) {
            obsah += " " + nazev;
        }
        if (obsah.length() == 0) {
            obsah = " nichts zu sehen"; //v batohu není nic
        }
        else {
            obsah = ""+obsah; //přidá se
        }
        return obsah;
    }
    
    
    
    /**
     * Tato metoda nachází hledaný předmět (který např chceme použít)
     * 
     * @param nazevPredmetu      název předmětu, který hledáme
     * @return hledany           výpis hledaného předmětu, pokud zde není, vrací null (nastaveno na začátku metody impl.)
     */
    public Predmet getPredmet(String nazevPredmetu) {
        Predmet hledany = null;
        return predmety.get(nazevPredmetu);
        
        
    }
    
    
    
    /**
     * Umožňuje odstranění předmětu z batohu (pokud jej např. pokládáme do lokace)
     * nepožadujeme, aby poté ještě v batohu zůstával.
     * 
     * @param   odstran     předmět, který zamýšlíme z batohu vymazat (odstranit)
     * @return  odstraneny  vrací vymazaný předmět. V případě jeho nenalezení hodnota null
     */
    public boolean smazPredmet (String remove) {
        boolean odeb = false;
        if(predmety.containsKey(remove) && predmety.get(remove).isPrenositelny()) {
            predmety.remove(remove);
            odeb = true;
        }
        return odeb;
    }
    
    
    
    /**
     * Umožňuje vrátit maximální počet předmětů v batohu
     * 
     * @return  Kapacita batohu
     */
    public int getMaxPredmetu() {
        return Kapacita;
    }
    
    /**
     * Vrací mapu předmětů s jejich názvy
     * @return predmety 
     */
    public Map <String,Predmet> vratBatoh()
    {
        return this.predmety; //mapa pro název předmětu a předmět
    }

   
}