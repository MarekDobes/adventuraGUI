package logika;

/**
 * PrikazOtazka představuje samostatnou třídu. 
 * Tato třída umožňuje pomocí svých instancí "komunikovat" s jednotlivými NPC ve hře.
 * 
 * @author Marek Dobeš
 * @version 02 05 2017
 */

public class PrikazOtazka implements IPrikaz
{
    private static final String NAZEV = "otazka";
    private HerniPlan herniPlan;
    private Hra hra;

/**
 * Konstruktor pro třídu PrikazOtazka
 * 
 * @param herniPlan herniPlan, hra v herniPlan dochází k procházení prostor a následné komunikaci s NPC
 */    
public PrikazOtazka (Hra hra)
 {
     this.herniPlan = hra.getHerniPlan();
     this.hra = hra;
 }

 
/**
 * Zde se provádí samotný PrikazOtazka.
 * Pokud je parametr příkazu nedefinován (metoda neví, koho se zeptat v lokaci), vypíše se příslušný text.
 * Pokud je NPC přítomná (nachází se v aktuální lokaci) a ptám se poprvé, vrací metoda odpověď NPC.
 * Pokud se ptám podruhé a více, NPC mi již neodpoví nebo spustí příslušnou akci.
 * 
 * @param    parametry příkazu, jedná se o jméno NPC (např. Vasil, Taliani atd.)
 * @return   výpis odpovědi, kterou NPC vrací hráči
 */ 
  public String proved(String... parametry){
     if(parametry.length ==0){
       return "Není na se koho zeptat, ztatil jsi řeč?";
     }
        
    String jmeno = parametry[0]; 
    Lokace aktualniLokace = herniPlan.getAktualniLokace(); //stanovení aktuální lokace do aktualniLokace
    NPC npc = aktualniLokace.isNPC(jmeno); //pokud je v lokaci přítomná NPC, "nahraje" se do npc
    
     if(npc == null) { //NPC v prostoru není
       return "Nikdo takový tu není";
      }   
      
       if(npc.getJmeno().equals("Radni")&&hra.getZivoty()<1) { //výjimka pro radního
            hra.setKonecHry(true);
            return "K radnímu jsi přišel jako trhan. Nezáleží, zda jsi"
            +"\nzásilku přinesl nebo ne. Bodyguardi tě pro jistotu"
            +"\noddělali hned, jak jsi si sáhl do kapsy."
            +"\nProhrál jsi";
        }
        
     if (npc != null && npc.getOtazka()==0) { //NPC je v lokaci a ptám se poprvé
       return npc.getOdpoved();
            
     }
    
      else {
          switch (jmeno) {
              case "Vasil":
              hra.removeZivot();
              return "Co si o sobě myslíš, chceš jednu do zubů?";
              
              case "Jeptiska":
              hra.setKonecHry(true);
              return "Při rozhovoru s jeptiškou ti uklouzlo rouhavé slovo. Jeptiška na"
              +"\ntebe seslala hrozivou kletbu. Přišel jsi do pekla.";

              
              case "Chemik":
              hra.setKonecHry(true);
              return "Ptát se zločince podruhé není dobrý nápad, stálo tě to život";
              
              case "BabaJaga":
              hra.setKonecHry(true);
              return "Jaguška nemá s takovými jako ty trpělivost";
              
             
             default:
             return "Víc ti už neřeknu, unavuješ mě";
             
             
              
            }

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