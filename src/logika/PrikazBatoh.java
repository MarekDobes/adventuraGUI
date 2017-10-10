package logika;

/**
 * PrikazBatoh představuje samostatnou třídu. 
 * Tato třída umožňuje pomocí svých instancí procházet Batoh.
 * Zobrazuje tak předměty, které Batoh obsahuje.
 * 
 * @author Marek Dobeš
 * @version 01 05 2017
*/
public class PrikazBatoh implements IPrikaz
{
    private static final String NAZEV = "batoh";
    private HerniPlan herniPlan;

/**
 * Konstruktor pro třídu PrikazBatoh
 * 
 * @param herniPlan herniPlan, ve kterém se hra odehrává.
 */    
public PrikazBatoh (HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
}

/**
 * Zde se provádí samotný PrikazBatoh.
 * Pokud je batoh prázdný, vypíše se text, že batoh nic neobsahuje.
 * Pokud není batoh prázdný, vypíší se obsažené předměty.
 * 
 * @return výpis zprávy, které uživatele informuje o obsahu batohu
 * 
 */    
@Override
public String proved (String... parametry) {
    if (herniPlan.getBatoh().getPredmety().isEmpty()) {
     return "Zatím jsi nic nesbral, v batohu nic nemáš";
    }
    
    else {
     return "V batohu mám" + herniPlan.getBatoh().getPredmety();
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
