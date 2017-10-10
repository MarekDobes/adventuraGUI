/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

/**
 * Třída PrikazJdi implementuje pro hru příkaz jdi.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author     Jarmila Pavlickova, Luboš Pavlíček, Jan Říha, Marek Dobeš
 * @version    14 05 2017
 */
class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private HerniPlan plan;
    private Hra hra;
    
   /**
    * Konstruktor třídy
    *
    * @param    plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazJdi(HerniPlan plan, Hra hra) {
        this.plan = plan;
        this.hra = hra;
        
    }

    /**
     * Provádí příkaz "jdi". Zkouší se vyjít do zadané lokace. Pokud lokace
     * existuje, vstoupí se do nového lokace. Pokud zadaná sousední lokace
     * (východ) není, vypíše se chybové hlášení.
     * Pokud je splněno několik podmínek (hráč je v určité lokaci a snaží se přejít do další lokace
     * přičemž má v batohu věc požadovanou NPC, může to pro něj mít neblahé důsledky.
     *
     * @param     parametry jako parametr obsahuje jméno lokace (východu), do kterého se má jít.
     * @return    zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední lokace), tak ....
            return "Kam mám jít? Musíš zadat jméno východu";
        }

        String smer = parametry[0];
        String print = "";

        // zkoušíme přejít do sousední lokace
        Lokace sousedniLokace = plan.getAktualniLokace().vratSousedniLokaci(smer);
        Lokace aktualniLokace = plan.getAktualniLokace();
        Batoh batoh = plan.getBatoh();

        if (sousedniLokace == null) {
            return "Tam se odsud jít nedá!";
        } 
        
          if(aktualniLokace.getNazev().equals("satna") && sousedniLokace.getNazev().equals("pizzerie")&& batoh.obsahujePredmet("cigarety"))  {
            
            hra.setKonecHry(true);
            print= "Neodevzdal jsi přátelům, to, co po tobě chtěli."
             +"\nZa to tě ztrestali mnohými ranami do břicha"
             +"\na jiných částí těla. Bohužel pro tebe"
             +"\nnejsi zrovna Rambo, takže jsi výprask nevydržel."
             +"\nUmíráš po cestě do nemocnice."
             +"\nPROHRÁL JSI!!!!!!!!!!!!!!!";
            return print;
        }
        
         if(aktualniLokace.getNazev().equals("metro") && sousedniLokace.getNazev().equals("stanice") && !batoh.obsahujePredmet("dolary"))  {
             
            hra.removeZivot();
            hra.removeZivot();
            print= "Bohužel jsi u sebe zrovna neměl dolary na zaplacení jízdného"
             +"\nTo průvodčího velmi namíchlo"
             +"\nProto ti srovnal morálku pár dobře mířenými ranami na Solar Plexus"
             +"\nNaštěstí to byla jen lekce, přišel jsi jen o pár životů"
             +"\nPříště si takové jednání prosím rozmysli, další lekce by byla konečná"
             +"\nNyní se prosím vrat domů pro peníze, jinak tě dál nepustí a lekce se bude opakovat.";
             
            return print;
        }
        
        if(aktualniLokace.getNazev().equals("banka") && sousedniLokace.getNazev().equals("les") && !aktualniLokace.obsahujePredmet("vodka"))  {
             
            hra.setKonecHry(true);
            print= "Neodevzdal jsi vodku hlídači.."
             +"\nHlídač zavolal policii a ta tě uvrhla do vazby."
             +"\nVe vazbě strávíš několik dní, zásilku tak nedoručíš."
             +"\nPříště si takové jednání prosím rozmysli, vodka ti stejně k ničemu nebyla"
             +"\nPROHRÁL JSI!!!!!!!!!!!!!!!";
            return print;
        }
        
         if(aktualniLokace.getNazev().equals("ukryt") && sousedniLokace.getNazev().equals("radnice"))  {
           
            hra.setKonecHry(true);
            print= "Loupežníci tě nachytali, jak vylézáš z"
             +"\njejich úkrytu. Aniž by se tě na cokoli"
             +"\nzeptali, provrtali ti hlavu kulí z bambitky."
             +"\nPříště si takové jednání prosím rozmysli, chodit do"
             +"\ncizích příbytků se nevyplácí"
             +"\nPROHRÁL JSI!!!!!!!!!!!!!!!";
            return print;
        }
        
             
      else {
            plan.setAktualniLokace(sousedniLokace);
            return sousedniLokace.dlouhyPopis();
        }
        
    }
    

    

    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     * @return    nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}
