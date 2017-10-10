package logika;


/**
 * Třída PrikazProzkoumej implementuje pro hru příkaz prozkoumej.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author     Marek Dobeš
 * @version    12 05 2017
 */
public class PrikazProzkoumej implements IPrikaz
{
    private static final String NAZEV = "prozkoumej";
    private HerniPlan hPlan;
    
/**
 * Konstruktor třídy PrikazProzkoumej
 * 
 * @param hPlan
 */    
public PrikazProzkoumej(HerniPlan hPlan)
{
    this.hPlan = hPlan;
}
    
/**
 * Zde se provádí PrikazProzkoumej.
 * Pokud se v lokaci nacházejí předměty a NPC, příkaz o nich podá informaci.
 * Sekundárně se také zobrazí popis lokace.
 * 
 * @param parametry příkazu, jedná se o název předmětu (voda, cigarety apod.)
 * @return výpis odpovědi, kterou příkaz vrací
 */
@Override
public String proved(String... parametry)
{
    if(parametry.length>1)
    {
        return "Nechapu, co po me chces";
    }
    
    if(parametry.length>0)
    {
        String nazevPredmetu = parametry[0];
        Lokace aktLokace = hPlan.getAktualniLokace();
        
        if(aktLokace.obsahujePredmet(nazevPredmetu)) {
        
            return aktLokace.najdiPredmet(nazevPredmetu).getPopis();
            
        }
        
        return "Predmet "+ nazevPredmetu + " tady není";
        
     
    }
    else {
    Lokace aktLokace = hPlan.getAktualniLokace(); //uložení do pomocné proměnné
    return aktLokace.dlouhyPopis();
    }
}

    /**
    * Metoda, která identifikuje název příkazu ve hře
    * 
    * @return název příkazu ve hře
    */
@Override
public String getNazev()
{
    return NAZEV;
}
}