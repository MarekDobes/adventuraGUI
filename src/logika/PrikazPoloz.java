package logika;

/**
 * PrikazPoloz představuje samostatnou třídu. 
 * Tato třída umožňuje pomocí svých instancí pokládat předměty do lokací ve hře.
 * 
 * @author Marek Dobeš
 * @version 29 04 2017
 */

public class PrikazPoloz implements IPrikaz
{
    private static final String NAZEV = "poloz";
    private HerniPlan herniPlan;
    
    
    /**
    * Konstruktor pro třídu PrikazPoloz
    * 
    * @param herniPlan herniPlan, v herniPlan dochází k procházení lokací, do kterých lze předmět položit
    */
    public PrikazPoloz(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }
    
    
    /** 
    * Zde se provádí samotný PrikazPoloz.
    * Pokud je parametr příkazu nedefinován (metoda neví, který předmět odložit), vypíše se příslušný text.
    * Pokud předmět není v batohu (je null) a hráč jej tedy u sebe nemá, vypíše se příslušný text
    * Pokud je odložení možné (předmět je v batohu), předmět se z batohu vyjme ("smaže") a odloží se do aktuální lokace.
    * 
    * @param parametry příkazu, jedná se o název Předmětu(např. pizza, rum atd.)
    * @return výpis, který se hráči vrací (lze/nelze odložit, co se odložilo)
     */
    @Override
    public String proved(String... parametry) {
        if (parametry.length ==0) { //pokud hra neví, co odložit
            return "Nevím, který předmět mám odložit, specifikuj prosím";
        }
        
        
        
        String nazevPredmetu = parametry [0]; //nastavení názvu na délku 0
        Lokace aktualniLokace = herniPlan.getAktualniLokace(); //nastavení aktuální lokace
        Batoh batoh = herniPlan.getBatoh(); //co obsahuje batoh
        Predmet odkladany = batoh.getPredmet(nazevPredmetu); //nastavení odkládaného předmětu (pomocná proměnná)
      
        
        if(odkladany == null) { //pokud v batohu předmět není
            return "Nelze provést, takový předmět u sebe nemám";
        }
      
        else {
            odkladany = batoh.smazPredmet(nazevPredmetu); //přenastavení předmětu z batohu do pomocné proměnné odkladany + předmět se z batohu smaže
            aktualniLokace.vlozPredmet(odkladany); //odkladany předmět se přesune do aktuální lokace
            return "Odložil jsem " + nazevPredmetu; //výpis, co bylo "položeno" (které předměty jsme vytáhly z batohu a odložili)
        }
    }
    
    
    /**
    * Metoda, která identifikuje název příkazu ve hře
    * 
    * @return název příkazu ve hře
    */
    @Override
    public String getNazev() {
        return NAZEV;
    }

    
}
