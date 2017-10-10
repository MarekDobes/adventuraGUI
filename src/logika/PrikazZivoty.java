package logika;

/**
 * PrikazZivoty představuje samostatnou třídu. 
 * Tato třída umožňuje pomocí svých instancí zobrazovat v průběhu hry počet životů hráče.
 * Pokud počet životů klesne pod 1, hráč nemůže vyhrát (i kdyby zásilku donesl).
 * 
 * @author Marek Dobeš
 * @version 02 05 2017
*/

public class PrikazZivoty implements IPrikaz
{
    private static final String NAZEV = "zivoty";
    private Hra hra;
    
    /**
    * Konstruktor pro třídu PrikazZivoty
    * 
    * @param hra, ve které se aktualizuje počet životů.
    */    
    
    public PrikazZivoty (Hra hra) {
      this.hra = hra;
    }
    
    
    /**
     * Samotný příkaz PrikazZivoty
     * Pokud provedu daný příkaz (je bez paramtrů, tedy stačí napsat v průběhu hry >zivoty<).
     * Zobrazí se mi aktuální počet životů hráče.
     * 
     * @param bez parametrů (není potřeba)
     * @return vrací příslušný počet dostupných životů hráče a upozornění
     */
    @Override
    public String proved (String... parametry) {
       {
            return "Momentálně mám " + hra.getZivoty() + " životy" //natahuje počet životů ze hry
            +"\nmusím si dávat pozor, abych neklesl na 0";
       }
     }

    
    /**
    * Metoda, která identifikuje název příkazu ve hře
    * 
    * @return název příkazu ve hře
    */
    @Override
    public String getNazev(){
        return NAZEV;
    }
}
