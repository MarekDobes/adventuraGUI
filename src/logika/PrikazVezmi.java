package logika;


/**
 * Třída představuje příkaz pro sebrání předmětu z aktuální lokace
 * a jeho vložení do batohu (inventáře) postavy.
 * 
 * @author   Marek Dobeš
 * @version  16 05 2017
 */
public class PrikazVezmi implements IPrikaz
{
    private static final String NAZEV = "vezmi";
    private HerniPlan hPlan;
    private Batoh batoh;
    private Hra hra;

  
    /**
     * Konstruktor
     * 
     * @param hPlan, hra
     */
    public PrikazVezmi(HerniPlan hPlan, Hra hra)
    {
        this.hPlan = hPlan;
        this.batoh = hPlan.getBatoh();
        this.hra = hra;
    }
    
    /**
     * Metoda představuje zpracování příkazu pro sebrání předmětu.
     * Nejprve zkontroluje, zda byl zadán právě jeden název jako
     * parametr, ověří, zda v aktuální lokaci je předmět s tímto
     * názvem, zda je přenositelný, zda je v batohu místo a
     * následně předmět odebere z lokace a vloží ho do batohu.
     * 
     * @param parametry pole parametrů zadaných na příkazové řádce
     * @return výsledek zpracování, tj. text, který se vypíše na konzoli
     */
    public String proved(String... parametry)
    {        
        if (parametry.length < 1)
        {
            return "Nevim, co mam sebrat";
        }
        
        if (parametry.length > 1)
        {
            return "Tomu nerozumim, nedokazu sebrat vice veci najednou";
        }
        
        String print;
        String nazevPredmetu = parametry[0]; 
        Lokace aktLokace = hPlan.getAktualniLokace(); 
        
        if (!aktLokace.obsahujePredmet(nazevPredmetu)) {//pokud aktuální lokace neobsahuje daný předmět
        
            return "Predmet " + nazevPredmetu + " tady neni"; 
        }
        
        if(nazevPredmetu.equals("pepsi")) { //výjimka pro pepsi
            print = "Ty zloději, ukradl mi plechofšišku!"
            +"\nBohužel se v tobě projevily zlodějské sklony"
            +"\nMajitel obchodu na tebe zavolal policii a ta tě zatkla"
            +"\nProhrál jsi, zásilku už nestihneš doručit";
            hra.setKonecHry(true);
           
        }
        
        else {
            Predmet pred = aktLokace.vezmiPredmet(nazevPredmetu); //natažení zamýšleného předmětu do proměnné "pred"
            
            if (!pred.isPrenositelny()) { //pokud nelze přenést
            aktLokace.vlozPredmet(pred); //nechceme předmět smazat, proto jej vložíme zpět do lokace
            return "Predmet " + nazevPredmetu + " fakt neuneses";
            }
 
            if (batoh.vlozPredmet(pred) == null) { //překročení limitu batohu
                aktLokace.vlozPredmet(pred); //nechceme předmět smazat, proto jej vložíme zpět do lokace
                return "Překročil jsem batoh limit";
            }
           
            else {
                return "Sebral(a) jsi predmet " + nazevPredmetu; //pokud projde podmínkami, předmět se sebere
                
            }
        
    
    }
  
    return print;
    }
    
    /**
     * Metoda vrací název příkazu. Příkaz můžeme vyvolat
     * zadáním názvu na konzoli.
     * 
     * @return název příkazu
     */
	public String getNazev()
	{
	    return NAZEV;
	}
    
}
