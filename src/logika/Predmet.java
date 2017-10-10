package logika;

/**
 * Třída předmět, umožňuje manipulovat s předmět a nastavuje jejich parametry
 * 
 * @author Marek Dobeš
 * @version 15 05 2017
 */

public class Predmet
{
    private String nazev;
    private String popis;
    private boolean prenositelny;
   
    /**
     * Konstruktor (nastavuje se název předmětu, popis předmětu a boolean přenositelnosti)
     * 
     * @param nazev, popis, prenositelny
     */
    public Predmet(String nazev, String popis, boolean prenositelny)
    {
        this.nazev = nazev;
        this.popis = popis;
        this.prenositelny = prenositelny;
    }
    
    /**
     * 2.konstruktor
     */
    public Predmet(String nazev, String popis)
    {
        this(nazev, popis, true);
    }
    
    /**
     * getter pro název předmětu
     */
    public String getNazev()
    {
        return nazev;
    }
    
    /**
     * getter pro popis předmětu
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
