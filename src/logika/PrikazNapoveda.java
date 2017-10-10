/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

/**
 * Třída PrikazNapoveda implementuje pro hru příkaz napoveda.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author     Jarmila Pavlickova, Luboš Pavlíček, Jan Říha, Marek Dobeš
 * @version    11 05 2017
 */
class PrikazNapoveda implements IPrikaz {

    private static final String NAZEV = "napoveda";
    private SeznamPrikazu platnePrikazy;

   /**
    * Konstruktor třídy
    *
    * @param    platnePrikazy seznam příkazů, které je možné ve hře použít, aby je nápověda mohla zobrazit uživateli.
    */    
    public PrikazNapoveda(SeznamPrikazu platnePrikazy) {
        this.platnePrikazy = platnePrikazy;
    }

    /**
     * Vrací základní nápovědu po zadání příkazu "napoveda". Nyní se vypisuje
     * vcelku primitivní zpráva a seznam dostupných příkazů.
     *
     * @return    napoveda ke hre
     */
    @Override
    public String proved(String... parametry) {
        return "Tvým úkolem je donést zásilku do radnice.\n"
        + "Zasilku najdeš v pizzerii.\n"
        + "Po cestě na tebe čeká mnoho nástrah, tak pozor!\n"
        + "Snaž se vždy mluvit s postavami (vyhneš se tak často\n"
        + "zbytečným konfliktům). Po cestě se chovej jak se patří.\n"
        + "Starším ustupuj a slabším pomáhej.\n"
        + "S podezřelými existencemi ale raději nehovoř a nechod do zvláštních lokací.\n"
        + "Dobré předměty ti pomohou, špatné uškodí.\n"
        + "Teď už ale běž, zásilka čeká\n"
        + "\n"
        + "Můžeš zadat tyto příkazy:\n"
        + platnePrikazy.vratNazvyPrikazu();
    }

     /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání).
     *  
     * @return    nazev prikazu
     */
    @Override
      public String getNazev() {
        return NAZEV;
     }

}
