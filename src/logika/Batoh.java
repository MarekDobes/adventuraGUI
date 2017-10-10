package logika;



/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */

 import java.util.*;

/*******************************************************************************
 * Batoh představuje samostatnou třídu ve hře. 
 * Umožňuje přidávat předměty(Predmet) do batohu (ArrayList).
 *
 * @author    Marek Dobeš 
 * @version   23 04 2017
 */
public class Batoh
{
    private static final int Kapacita = 4;    // Maximální počet věcí v batohu
    private List<Predmet> seznamPredmetu;            // Seznam věcí v batohu


    /**
     *  Konstruktor třídy Batoh (seznam Předmětů --> vyvolá se jako ArrayList)
     */
    public Batoh() {
        seznamPredmetu = new ArrayList<Predmet>();
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
            seznamPredmetu.add(vkladany);
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
        if (seznamPredmetu.size() < Kapacita) {
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
        for (Predmet aktualni: seznamPredmetu) {
            if (aktualni.getNazev().equals(hledam)) {
                 return true;
            }
        }
        
        return false;
    }
    
    
    
    /**
     * Umožňuje vypsat aktuální seznam předmětů v batohu (slouží např. pro PrikazBatoh)
     * 
     * @return   obsah     na konzoli se vypíše aktuální seznam věcí v batohu
     */
    public String getPredmety() {
        String obsah = ""; //prozatím zde nic není (vypíše se "") 
        
        for (Predmet aktualni: seznamPredmetu) {
            if (!obsah.equals("")) {
                
                obsah += " "; //vynucená mezera (" ") slouží pro oddělení výpisu jednotlivých předmětů (např. pivo rum vodka)
            }
            obsah += " " + aktualni.getNazev();
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
        
        for (Predmet aktualni: seznamPredmetu) {
            if(aktualni.getNazev().equals(nazevPredmetu)) { //hledáme předmět, který máme v batohu
                hledany = aktualni;
                break;
            }
        }
        
        return hledany;
    }
    
    
    
    /**
     * Umožňuje odstranění předmětu z batohu (pokud jej např. pokládáme do lokace)
     * nepožadujeme, aby poté ještě v batohu zůstával.
     * 
     * @param   odstran     předmět, který zamýšlíme z batohu vymazat (odstranit)
     * @return  odstraneny  vrací vymazaný předmět. V případě jeho nenalezení hodnota null
     */
    public Predmet smazPredmet (String odstran) {
        Predmet odstraneny = null;
        
        for(Predmet prit: seznamPredmetu) { //natažení seznamu předmětů do pomoc proměnné
            if(prit.getNazev().equals(odstran)) { //pokud je předmět v batohu stejný jako ten, se kterým zamýšlíme provést akci
                odstraneny = prit; //vložení předmětu z batohu do proměnné
                seznamPredmetu.remove(prit); //odstranění prit
                break;
            }
        }
        
        return odstraneny; //vrací daný předmět
    }
    
    
    
    /**
     * Umožňuje vrátit maximální počet předmětů v batohu
     * 
     * @return  Kapacita batohu
     */
    public int getMaxPredmetu() {
        return Kapacita;
    }
    
    

}