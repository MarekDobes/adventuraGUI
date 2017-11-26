/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.*; 


/**
 * Trida Lokace - popisuje jednotlivé lokace (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Lokace" reprezentuje jedno místo (místnost, prostor, ...) ve scénáři hry.
 * Lokace může mít sousední lokace připojené přes východy. Pro každý východ
 * si lokace ukládá odkaz na sousedící lokace.
 * Přidává také 2D koordináty pro umístění red circle na border panu (pos x a pos y)
 *
 * @author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha, Marek Dobeš
 * @version    ZS 2017/2018
 */
public class Lokace {

    private String nazev;
    private String popis;
    private Set<Lokace> vychody;   // obsahuje sousední lokace
    private Map<String, Predmet> predmety; //seznam předmětů
    private List<NPC> listNPC; //seznam postav
    private NPC npc;
    
    private double posX; //nastavení posX
    private double posY; //nastavení posY

    /**
     * Konstruktor
     * Vytvoření lokace se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param    nazev nazev lokace, jednoznačný identifikátor, jedno slovo nebo víceslovný název bez mezer
     * @param    popis Popis lokace
     * @param    posX koordinát osy X
     * @param    posY koordinát osy Y
     */
    public Lokace(String nazev, String popis, double posX, double posY) { //nastavení PosX a PosY na mapě s pozicemi bodu na mapě (červené tečky).
        this.nazev = nazev;
        this.popis = popis;
        this.posX = posX; //pozice horizontální mapy
        this.posY=posY; //pozice vertikální mapy
        vychody = new HashSet<>();
        predmety = new HashMap<>();
        listNPC = new ArrayList<NPC>();
        
    }

    /**
     * Definuje východ z lokace (sousední/vedlejsi lokace). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední lokace uvedena
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední lokace).
     * Druhé zadání stejné lokace tiše přepíše předchozí zadání (neobjeví
     * se žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param    vedlejsi lokace, která sousedi s aktualní lokací.
     *
     */
    public void setVychod(Lokace vedlejsi) {
        vychody.add(vedlejsi);
    }

    /**
     * Metoda equals pro porovnání dvou lokací. Překrývá se metoda equals ze
     * třídy Object. Dvě lokace jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param     o object, který se má porovnávat s aktuálním
     * @return    hodnotu true, pokud má zadaná lokace stejný název, jinak false
     */  
      @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Lokace)) {
            return false;    // pokud parametr není typu Lokace, vrátíme false
        }
        // přetypujeme parametr na typ Lokace 
        Lokace druha = (Lokace) o;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

       return (java.util.Objects.equals(this.nazev, druha.nazev));       
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object.
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }
      
    /**
     * registruje stav, zda je NPC do prostoru vložen
     * 
     * @param     vlozen hodnota
     * @return    true, pokud se podařilo
     */
    public boolean vlozNPC (NPC vlozen){
       listNPC.add(vlozen);
       return true;
    }
    
   /**
    * Ověřuje, zda se opravdu ptáme NPC, která v lokaci reálně je
    * 
    * @param  jmeno Jméno NPC
    * @return tazan Jméno tázané NPC, pokud zde není, tak null
    */
   public NPC isNPC (String jmeno) {
     NPC tazan = null;
     
     for (NPC pritomen: listNPC){ //natažení NPC do proměnné
         if(pritomen.getJmeno().equals(jmeno)){ //pokud se rovnají
             tazan = pritomen; //táži se přítomné NPC
             break;
            }
     }
     
     return tazan;
    }
    
    /**
     * Vrací název lokace (byl zadán při vytváření lokace jako parametr
     * konstruktoru)
     *
     * @return    název lokace
     */
    public String getNazev() {
        return nazev;       
    }

    /**
     * Vrací "dlouhý" popis lokace, který může vypadat následovně: Jsi v
     * mistnosti/lokaci vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return    dlouhý popis lokace
     */
    public String dlouhyPopis() {
        String popisNPC = "";
        if(npc!=null) //addon k NPC, pokud není npc null, vrátí se seznam NPC v lokaci
        {
            popisNPC = "V lokaci je "+npc.getJmeno();
        }
        
        return "Jsi v mistnosti/lokaci " + popis + ".\n"
                + popisVychodu() + "\n"
                + seznamPredmetu() +"\n"
                + popisNPC(); //kdo je v lokaci
    }
    
  
    /**
     * Umožnuje vypsat seznam předmětů v dlouhyPopis
     * 
     * @return seznam Seznam předmětů v lokaci
     */
    
    private String seznamPredmetu()
    {
        String seznam = "predmety:";
        
        for (String nazevPredmetu : predmety.keySet())
        {
            seznam += " " + nazevPredmetu;
        }
        
        return seznam;
    }

    /**
     * slouží pro dlouhyPopis
     * @return   popis Vrací jména NPC v lokaci
     */
    private String popisNPC() {
        String popis= ""; //default, žádná NPC nejsou 
        
        if(!listNPC.isEmpty()) { //pokud není list NPC prázdný
            popis = "NPC:";
            
            for(NPC zde : listNPC) { //natažení NPC do prom zde
                if(!popis.equals("NPC:")) {
                    popis+=" "; //odděluji jednotlivé NPC (např Vasil BabaJaga Eva)
                }
                
                popis += " " + zde.getJmeno();
            }
        }
        
        return popis;
    }
    
    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: ulice ".
     *
     * @return    popis východů - názvů sousedních lokací
     */
    public String popisVychodu() {
        String vracenyText = "vychody:";
        for (Lokace sousedni : vychody) {
            vracenyText += " " + sousedni.getNazev();
        }
        return vracenyText;
    }

    /**
     * Vrací lokaci, která sousedí s aktuální lokací a jejíž název je zadán
     * jako parametr. Pokud lokace s udaným jménem nesousedí s aktuální
     * lokací, vrací se hodnota null.
     *
     * @param     nazevSouseda Jméno sousední lokace (východu)
     * @return    lokace, která se nachází za příslušným východem, nebo hodnota null, pokud lokace zadaného jména není sousedem.
     */
    public Lokace vratSousedniLokaci(String nazevSouseda) {
        List<Lokace>hledaneLokace = 
            vychody.stream()
                   .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
                   .collect(Collectors.toList());
        if(hledaneLokace.isEmpty()){
            return null;
        }
        else {
            return hledaneLokace.get(0);
        }
    }

    /**
     * Vrací kolekci obsahující lokace, se kterými ta lokace sousedí.
     * Takto získaný seznam sousedních lokací nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Lokace.
     *
     * @return    nemodifikovatelná kolekce lokací (východů), se kterými tato lokace sousedí.
     */
    public Collection<Lokace> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }
    
    /**
     * umožňuje vkládat předmět do lokace (realizováno v herniPlan)
     * 
     * @param Predmet predmet O jaký předmět se jedná
     */
    public void vlozPredmet(Predmet predmet)
    {
        predmety.put(predmet.getNazev(), predmet);
    }
    
    /**
     * slouží pro prikazVezmi
     * 
     * @param   nazevPredmetu
     * @return  odstraní předmět z předmětů lokace
     */
    public Predmet vezmiPredmet(String nazevPredmetu)
    {
        return predmety.remove(nazevPredmetu);
    }
    
    
    /**
     * slouží ke třídě prozkoumej. Hledám předměty, které jsou dostupné (v případě
     * Prozkoumej v dané lokaci). Vrací se mi poté názvy předmětů.
     * 
     * @param    nazevPredmetu
     * @return   předměty
     */
    public Predmet najdiPredmet(String nazevPredmetu)//k třídě prozkoumej
    {
        return predmety.get(nazevPredmetu);
    }
    
    /**
     * Slouží pro příkaz Prozkoumej
     * boolean, zda lokace obsahuje daný předmět nebo ne
     * 
     * @param   nazevPredmetu
     * @return  předmět
     */
    public boolean obsahujePredmet(String nazevPredmetu)
    {
        return predmety.containsKey(nazevPredmetu);
    }

    /**
     * getter pro PosX
     * @return the posX
     */
    public double getPosX() {
        return posX;
    }

    /**
     * getter pro PosY
     * @return the posY
     */
    public double getPosY() {
        return posY;
    }
    
    /**
     * Seznam výhodů z lokace ve formě jednolitého stringu. 
     * vrací seznam východů ve formátu "východy: "vychody (nutno poté v příslušné třídě GUIvychody oddělit oddělovači a rozdělit splitem)
     * @return 
     */
    public String seznamVychodu() 
    {
        String vracenyText = "vychody:";
        for (Lokace sousedni : vychody) {
             vracenyText += " " + sousedni.getNazev();
        }
        return vracenyText;
    }
    /**
     * getter pro Predmety ve formě mapy
     * @return predmety
     */ 
    
     public Map<String,Predmet> getPredmety()
     {
         return this.predmety;
     }
   
    
   
}
