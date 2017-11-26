/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

import java.util.ArrayList;
import java.util.List;
import utils.Observer;
import utils.Subject;

/**
 * Class HerniPlan - třída představující mapu a stav adventury.
 * 
 * Tato třída inicializuje prvky ze kterých se hra skládá:
 * vytváří všechny lokace, propojuje je vzájemně pomocí východů 
 * a pamatuje si aktuální lokaci, ve které se hráč právě nachází.
 * Kromě toho nastavuje array list observerů na této třídě
 *
 * @author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha, Marek Dobeš
 * @version    ZS 2017/2018
 */
public class HerniPlan implements Subject{
   
    private Lokace aktualniLokace;
    private Batoh batoh;
    private List<Observer> listObserveru = new ArrayList<Observer>();
    private Hra hra;
    
    
    
    /**
     * Konstruktor který vytváří jednotlivé lokace a propojuje je pomocí východů.
     */
    public HerniPlan(Hra hra) {
        zalozLokaceHry();
        batoh = new Batoh();
        this.hra = hra;
    }
    public Hra getHra(){
        return this.hra;
    }
    

    /**
     * Vytváří jednotlivé lokace a propojuje je pomocí východů.
     * Jako výchozí aktuální lokaci nastaví domov.
     */
    private void zalozLokaceHry() {
        // vytvářejí se jednotlivé lokace
        Lokace domov = new Lokace("domov","dům, ve kterém bydlí Luigi",105,275);
        Lokace ulice = new Lokace("ulice", "ulice, vede okolo Luigiho domu",278,275); 
        Lokace obchod = new Lokace("obchod","klasická večerka, je to tam jen pár metrů od domova",105,100);
        Lokace pestirna = new Lokace("pestirna", "pěstírna, co od majitele večerky jiného čekat",280,100);
        Lokace hlavni = new Lokace("hlavni","nejrušnější ulice ve městě",455,275); 
        Lokace pizzerie = new Lokace("pizzerie","Pizzerie, ve které pracuješ",105,440);
        Lokace satna = new Lokace("satna","Obyčejná šatna, sem si dáváš věci",280,440);
        Lokace metro = new Lokace("metro","Staré komančské metro, odpadky kam šlápneš\nkdyž ale odjedeš metrem na trase C\nUŽ SE NELZE VRÁTIT ZPĚT!!!\nzpáteční trasa je zaplavena.",458,595);
        Lokace stanice = new Lokace("stanice", "Stanice, Po půl hodině jsi dojel na konečnou stanici",103,673);
        Lokace banka = new Lokace("banka","Banka, Vlastní ji Krejčíř, měj se na pozoru! ",255,737);
        Lokace nemocnice = new Lokace("nemocnice","Motol, Ošklivý zápach všude kolem, ale snad ti tu pomůžou",420,678);
        Lokace ukryt = new Lokace("ukryt","Bunker, Zajímavá stavba u silnice, třeba tam něco najdeš",737,770);
        Lokace azyl = new Lokace("azyl","Asylum, Něco jako psychiatrická léčebna, něco by jsi tam ale mohl ukrást\nvedou jej jeptišky, nejsou podezíravé",580,675);
        Lokace les = new Lokace("les","Falndyr, Temný hvozd, pozor na dravá zvířata a loupežníky",420,770);
        Lokace radnice = new Lokace("radnice","Radnice, Klasická byrokratická stavba",580,460);
        
        aktualniLokace = domov;  // hra začíná v domově lugiho 
        
        
        // přiřazují se průchody mezi lokacemi (sousedící lokace)
        domov.setVychod(ulice);
        domov.setVychod(obchod);
        
        ulice.setVychod(domov);
        ulice.setVychod(hlavni);
        
        
        obchod.setVychod(domov);
        obchod.setVychod(pestirna);
        obchod.setVychod(hlavni);
        
        pestirna.setVychod(obchod);
        
        hlavni.setVychod(metro);
        hlavni.setVychod(obchod);
        hlavni.setVychod(pizzerie);
        hlavni.setVychod(ulice);
          
        
        pizzerie.setVychod(metro);
        pizzerie.setVychod(satna);
        pizzerie.setVychod(hlavni);
        
        satna.setVychod(pizzerie);
        
        metro.setVychod(stanice);
        metro.setVychod(hlavni);
        
        stanice.setVychod(banka);
        stanice.setVychod(nemocnice);
        
        banka.setVychod(stanice);
        banka.setVychod(les);
        
        azyl.setVychod(radnice);
        azyl.setVychod(nemocnice);
        
        nemocnice.setVychod(stanice);
        nemocnice.setVychod(azyl);

        ukryt.setVychod(les);
        ukryt.setVychod(radnice);

        les.setVychod(banka);
        les.setVychod(azyl);
        les.setVychod(ukryt);

        
        radnice.setVychod(azyl);
        
        
        
        //NPC
        NPC Eva = monolog("Eva");
        NPC Vasil = monolog("Vasil");
        NPC Carlos = monolog ("Carlos");
        NPC Chemik = monolog ("Chemik");
        NPC Capone = monolog ("Capone");
        NPC Taliani = monolog ("Taliani");
        NPC Pruvodci = monolog ("Pruvodci");
        NPC Homeless = monolog ("Homeless");
        NPC Hlidac = monolog ("Hlidac");
        NPC Jeptiska = monolog ("Jeptiska");
        NPC BabaJaga = monolog ("BabaJaga");
        NPC Loupeznici = monolog ("Loupeznici");
        NPC Radni = monolog ("Radni");
        
        
        //vkládání NPC do lokace
        domov.vlozNPC(Eva);
        ulice.vlozNPC(Vasil);
        obchod.vlozNPC(Carlos);
        pestirna.vlozNPC(Chemik);
        pizzerie.vlozNPC(Capone);
        satna.vlozNPC(Taliani);
        metro.vlozNPC(Pruvodci);
        metro.vlozNPC(Homeless);
        banka.vlozNPC(Hlidac);
        azyl.vlozNPC(Jeptiska);
        les.vlozNPC(BabaJaga);
        ukryt.vlozNPC(Loupeznici);
        radnice.vlozNPC(Radni);
        
        
        
        //vkládání předmětů do lokace
        
        domov.vlozPredmet(new Predmet("voda", "láhev sodovky", true, "voda.png"));
        domov.vlozPredmet(new Predmet("dolary", "bankovka 100$", true, "dolary.png"));
        domov.vlozPredmet(new Predmet("eura", "bankovka 200EUR", true,"eura.png"));
        domov.vlozPredmet(new Predmet("stul", "Stůl z Bauhausu", false, "stul.png"));
        domov.vlozPredmet(new Predmet("skrin", "skříň z obchodu Ikea", false, "skrin.png"));
        
        ulice.vlozPredmet(new Predmet("odpadky", "pytel odpadků", false,"odpadky.png"));
        ulice.vlozPredmet(new Predmet("koš", "koš s odpadky, je docela těžký", false,"koš.png"));
        
        obchod.vlozPredmet(new Predmet("pepsi", "plechovka Pepsi", true,"pepsi.png"));
        obchod.vlozPredmet(new Predmet("regál", "regál, ten asi nezvednu", false,"regál.png"));
        obchod.vlozPredmet(new Predmet("calvados", "láhev calvadosu", true,"calvados.png"));
        
        pestirna.vlozPredmet(new Predmet("konopi", "hezká rostlinka, ženě by se mohla líbit", true,"konopí.png"));
        pestirna.vlozPredmet(new Predmet("světlo", "UV světlo, na co to asi je?", false,"světlo.png"));
        pestirna.vlozPredmet(new Predmet("kolona", "Tady se něco vaří", false,"kolona.png"));
        
        hlavni.vlozPredmet(new Predmet("dlaždice", "Klasické dlaždice, je tu moc lidí, asi je neseberu", false,"dlaždice.png"));
       
        pizzerie.vlozPredmet(new Predmet("zásilka", "zásilka pro pana radního", true,"zásilka.png"));
        pizzerie.vlozPredmet(new Predmet("pizza", "pizza pro pana radního", true,"pizza.png"));
        pizzerie.vlozPredmet(new Predmet("sporák", "sporák na vaření", false,"sporák.png"));
        pizzerie.vlozPredmet(new Predmet("gril", "klasický gril na grilování", false,"gril.png"));
        pizzerie.vlozPredmet(new Predmet("pastix", "láhev pastixu", true,"pastix.png"));
        
        satna.vlozPredmet(new Predmet("lavice", "stará ošoupaná lavice", false,"lavice.png"));
        satna.vlozPredmet(new Predmet("lekarnicka", "vojenská lékárnička", true,"lekarnicka.png"));
        satna.vlozPredmet(new Predmet("vodka", "Ruský Standard", true,"vodka.png"));
        satna.vlozPredmet(new Predmet("pivo", "Lahev oroseného piva", true,"pivo.png"));
        satna.vlozPredmet(new Predmet("cigarety", "Startky", true,"cigarety.png"));
        
        banka.vlozPredmet(new Predmet("medkit", "armádní medkit", true,"medkit.png"));
        banka.vlozPredmet(new Predmet("trezor", "ocelový trezor, moc těžké", false,"trezor.png"));
        
        metro.vlozPredmet(new Predmet("poutac", "reklamní poutač", false,"poutac.png"));
        
        
        stanice.vlozPredmet(new Predmet("cedule", "Ukazatel", false,"cedule.png"));
        
        nemocnice.vlozPredmet(new Predmet("luzko", "Lůžko pro pacienty", false,"luzko.png"));
        
        azyl.vlozPredmet(new Predmet("morfin", "Ampule s morfiem", true,"morfin.png"));
        azyl.vlozPredmet(new Predmet("postel", "Postel pro pacienty", false,"postel.png"));

        
        les.vlozPredmet(new Predmet("houby", "Zvláštní houbičky, takové lysohlávkové", true,"houby.png"));
        les.vlozPredmet(new Predmet("strom", "Dub či buk, je mi to fuk", false,"strom.png"));
        
        ukryt.vlozPredmet(new Predmet("bedna", "Masivní bedna, nečekej, že se s ní potáhnu", false,"bedna.png"));

        
        radnice.vlozPredmet(new Predmet("obraz", "Krásný obraz od Daliho, ten bych asi krást neměl", false,"obraz.png"));
        

           
    }

    /**
     * Nastavení odpovědí NPC při položení otázky
     */
    private NPC monolog(String jmeno){
        NPC npc = null;
        
        switch(jmeno) {
         case "Eva":
            npc = new NPC ("Eva",
            "\nUž běž a vrať se brzy domů");
            break;
            
         case "Vasil":
            npc = new NPC ("Vasil",
            "\nZdravstvujte, som naky namazany, neotravuj tady");
            break;
            
         case "Carlos":
            npc = new NPC("Carlos",
            "\nViťam váš, co vy pšát?"
            +"\nTady ne špatný, žádná levírna"
            +"\nMoc ale nekukaj kodem");
            break;
            
         case "Chemik":
            npc = new NPC("Chemik",
            "\nTy k nám nepatříš, co tu hledáš?"
            +"\nJestli ještě cekneš, uvidíš ten mazec!");
            break;
         
         case "Capone":
            npc = new NPC("Capone",
            "\nNazdar mladej, mám tady pro tebe zásilku."
            +"\nOdneseš to na radnici."
            +"\nA běda jestli ji cestou ztratíš nebo sežereš."
            +"\nTo by byl tvůj konec darebáku, ať tě tu už nevidím");
            break;
            
            
         case "Taliani":
            npc = new NPC("Taliani",
            "\nZdar mladej, Capone právě odešel, máš doručit Pizzeti"
            +"\nNa krabííci máš adréésu, snad tě mamá naučila číst perfecto"
            +"\nMáápu snad máš, směrovat tě nemusííííííme"
            +"\nDáávej ale bácha, takový nuno jak ty se ve městě rychle ztratííí"
            +"\nHahaha"
            +"\nA nezapomen na veci z satny, kdyz nevezmes ty"
            +"\nvezmeme my Hahaha"
            +"\nA dej sem cigára, jinak uvidíš ten mazec!!!!");
            break;
            
          case "Pruvodci":
            npc = new NPC("Pruvodci",
            "\nDobrý den, lístek prosím."
            +"\nDostanu zaplaceno, bereme dolary."
            +"\nHlavně si ty peníze nechej v batohu."
            +"\nNikam je neodkládej, nebo je ztratíš."
            +"\nJestli ale nezaplatíš, rozbiju ti ciferník, jestli tě chytím bez lístku.");
            break;
            
         case "Hlidac":
            npc = new NPC("Hlidac",
            "\nNaždááááár mladíšku"
            +"\nByla by vodeška?"
            +"\nNevejrej tak na mě, nebo ti rozbiju ten tfůj kjášnej nošánek."
            +"\nTo bys pšece nechtěl ne?");
            break;
            
         case "Jeptiska":
            npc = new NPC("Jeptiska",
            "\nBůh ti žehnej synu."
            +"\nDo nemocnice je to tak daleko a nám dochází zásoby."
            +"\nNeměl bys nějaké obvazy, potřebuje to jeden z našich chovanců."
            +"\nZa balíček bych ztratila několik modliteb za spásu tvé duše."
            +"\nPokud chceš s něčím pomoci, zeptej se mě znovu");
            break;
            
            
         case "BabaJaga":
            npc = new NPC("BabaJaga",
            "\nHa, Luigi, přišel nečekán, nezván"
            +"\nchaloupku otočil a mě tím probudil"
            +"\nZachtělo se mi houbiček, honem mi je přines, nebo tě stihne krutá kletba!");
            break;
            
         case "Loupeznici":
            npc = new NPC("Loupeznici",
            "\nStůj bídný červe, připrav se na smrt!!"
            +"\nPeníze nebo život!");
            break;
            
            
         case "Homeless":
            npc = new NPC("Homeless",
            "\nDobrý den chlapče, nebyly by drobásky???"
            +"\nUctivě děkuji mladý pane, za to se vám musím odměnit"
            +"\nJestli jdete přes les varujte se temných zákoutí,"
            +"\nřádí tu loupežníci, mějte se na pozoru!"
            +"\nSnad vám to nějak pomůže"
            +"\nJsem již stár, děkuji za obolos"
            +"\nŠťastnou cestu");
            break;
            
          case "Radni":
            npc = new NPC("Radni",
            "\nDobrý den chlapče, přinesl jste mou zásilku?"
            +"\nPokud ano, prosím, použijte příkaz <pouzij zásilka>."
            +"\nPokud ne, rychle mi ji přineste, nemám"
            +"\nna to celý den!"
            +"\nDěkuji");
            break;
            
         default:
            break;
        }
        return npc;
}
   
    /**
     * Metoda vrací odkaz na aktuální lokaci, ve které se hráč právě nachází.
     *
     * @return    aktuální lokace
     */
    
    public Lokace getAktualniLokace() {
        return aktualniLokace;
    }

    /**
     * Metoda nastaví aktuální lokaci, používá se nejčastěji při přechodu mezi lokacemi
     *
     * @param    lokace nová aktuální lokace
     */
    public void setAktualniLokace(Lokace lokace) {
       aktualniLokace = lokace;
       notifyAllObservers();
    }
    
/**
 * Metoda vrací batoh
 * @return batoh
 */
    public Batoh getBatoh() {
    return this.batoh;
    }

    /**
     * metoda zaregistruje návrhový vzor (Observera) na instanci herniho planu, observer se přidá do předdef. listu
     * @param observer 
     */
    @Override
    public void registerObserver(Observer observer) {
        listObserveru.add(observer);
    }

    /**
     * V případě zavolání metody se Observer odhlásí z listu (např. vytvoření nové hry - na staré instanci je již pozorování nežádoucí)
     * @param observer 
     */
    @Override
    public void deleteObserver(Observer observer) {
        listObserveru.remove(observer);
    }

    /**
     * Metoda zajišťující real time aktuálnost informací
     * Pokud dojde ke změně, je nutné observera o změně informovat
     */
    @Override
    public void notifyAllObservers() {
        for (Observer listObserveruItem :listObserveru){
            listObserveruItem.update();
        }
    }

    public Object getAktualniProstor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
