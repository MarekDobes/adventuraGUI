package logika;

/**PrikazPouzij představuje samostatnou třídu. 
 * Tato třída umožňuje pomocí svých instancí používat předměty ve hře.
 * 
 * @author Marek Dobeš
 * @version 27 04 2017
*/
public class PrikazPouzij implements IPrikaz
{
    private static final String NAZEV = "pouzij";
    private HerniPlan herniPlan;
    private Hra hra;

    
    /**
     * Konstuktor třídy PrikazPouzij
     * 
     * @param hra hra, která umožňuje celkové procházení hrou
     */
    public PrikazPouzij (Hra hra)
    {
        this.herniPlan = hra.getHerniPlan();
        this.hra = hra;
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
    
    /**
    * Zde se provádí samotný PrikazPouzij.
    * Pokud je parametr příkazu nedefinován (metoda neví, co použít), vypíše se příslušný text.
    * Pokud není předmět v batohu (je null), nelze jej logicky použít a vypíše se příslušný text.
    * Nastavují se zde také podmínky pro ukončení hry.
    * Pokud použiji příslušný předmět v předem dané lokaci a mám více životů než 0, hra končí a vyhrávám.
    * Pokud ale mám méně než 1 život, hra končí a prohrávám
    * Navíc zde také sleduji interakce s jednotlivými předměty (pokud předmět použiji, co se stane - např. přidá/ubere život)
    * Tato funkcionalita řešena přes switch - case 
    * 
    * @param   parametry příkazu, jedná se o jméno NPC (např. Vasil, Taliani atd.)
    * @return  výpis odpovědi, kterou NPC vrací hráči
    */ 
    public String proved(String... parametry){
        if(parametry.length ==0) { //nejsou dané parametry, nevíme co použít
            return "Nevím co mám použít";
        }
        
        String uzivanyPredmet = parametry[0]; //nastavení parametrů na délku 0
        Lokace aktualniLokace = herniPlan.getAktualniLokace(); //nastavení aktuální lokace
        Batoh batoh = herniPlan.getBatoh(); //nastavení batohu (obsahu batohu)
        String upozorneni; //výpis na obrazovku
        
        if(batoh.getPredmet(uzivanyPredmet) == null) { //zamýšlený předmět není v batohu
            upozorneni = "Takový předmět v batohu nemám, zkus něco jiného";
            
        }
         if(aktualniLokace.getNazev().equals("radnice") && uzivanyPredmet.equals("zásilka")&&batoh.obsahujePredmet("zásilka")&&hra.getZivoty()>0)  {
            hra.setKonecHry(true);
           upozorneni= "Donesl jsi zásilku panu radnímu."
             +"\nTen je tak potěšen, že ti dává několik"
             +"\nbalíků 500EURovek. Je to prý z dotací"
             +"\ntak se nemám bát. Teď to jen rychle utratit"
             +"\nnež nám euro padne."
             +"\nVYHRÁL JSI!!!!!!!!!!!!!!!";
            return upozorneni;
        }
        
      
        else {
            //co se stane, pokud použiji konkrétní předmět
            switch (uzivanyPredmet) {
            //takhle, doplnit předměty
                case "voda":
                case "pizza":
                case "lekarnicka":
                case "medkit":
                case "pivo":
                hra.addZivot();
                upozorneni = "To mi bodlo";
                batoh.smazPredmet(uzivanyPredmet);
                break;
                case "konopi":
                case "morfin":
                case "houby":
                upozorneni = "Zemřel jsi a prohrál hru, ona te ta chut na psychotropní látky pro příště přejde";
                hra.setKonecHry(true);
                batoh.smazPredmet(uzivanyPredmet);
                break; 
                case "calvados":
                case "pastix":
                hra.removeZivot();
                hra.removeZivot();
                hra.removeZivot();
                upozorneni = "To už mi nedělej";
                batoh.smazPredmet(uzivanyPredmet);
                break;
                
             default:
             upozorneni = "Neco nam tu nehraje";
             break;
            }
        }
        
        return upozorneni;
    }
    
}

