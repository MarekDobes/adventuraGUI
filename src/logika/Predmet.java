package logika;

import java.util.HashMap;
import java.util.Map;

/**
 * Třída předmět, umožňuje manipulovat s předmět a nastavuje jejich parametry
 * 
 * @author Marek Dobeš
 * @version ZS 2017/2018
 */

public class Predmet
{
    private String nazev;
    private String popis;
    private boolean prenositelny;
    private Map<String, Predmet> predmety; //mapa předmětů s jejich názvy
    private String obrazek; //název obrázku
   
    /**
     * Konstruktor (nastavuje se název předmětu, popis předmětu, boolean přenositelnosti a název obrázku přísl. předmětu)
     * 
     * @param nazev, popis, prenositelny, obrazek
     */
    public Predmet(String nazev, String popis, boolean prenositelny, String obrazek)
    {
        this.nazev = nazev;
        this.popis = popis;
        this.prenositelny = prenositelny;
        predmety = new HashMap<String,Predmet>();
        this.obrazek = obrazek;
    }
    
    /**
     * 2.konstruktor no reason to leave
     */
    /*
    public Predmet(String nazev, String popis)
    {
        this(nazev, popis, true);
    }
    */
    /**
     * getter pro název předmětu
     * @return nazev nazevPredmetu
     */
    public String getNazev()
    {
        return nazev;
    }
    
    /**
     * getter pro obrázky
     * @return obrazek
     */
     public String getObrazek() {
        return obrazek;
    }
    
    /**
     * getter pro popis předmětu
     * @return popis popisPredmetu
     */
    public String getPopis()
    {
        return popis;
    }
    
    /**
     * boolean pro přenositelnost
     * 
     * @return prenostitelny
     * 
     */
    public boolean isPrenositelny()
    {
        return prenositelny;
    }
    
    /**
     * nastavuje se popis předmětu
     * 
     * @param popis popis předmětu
     */
    public void setPopis(String popis)
    {
        this.popis = popis;
    }
    
    /**
     * nastavuje se přenositelnost
     * 
     * @param prenositelny Je/není přenositelný
     */
    public void setPrenositelny(boolean prenositelny)
    {
        this.prenositelny = prenositelny;
    }
    
    /**
     * Předmět se jmenuje xxx
     * 
     * @return Název předmetu v určeném formátu
     */
    @Override
    public String toString()
    {
        return "Predmet: " + nazev;
    }

}
