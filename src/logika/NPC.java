package logika;

/**
 * Instance NPC jsou jednotlivé npc (postavy které hráč přímo neovládá).
 * S těmito npc může hráč pouze komunikovat (ptát se jich otázkami).
 * 
 * @author Marek Dobeš
 * @version 14 05 2017
 */

public class NPC
{
    private String jmeno;
    private String odpoved;
    private int otazka;
    
/**
 * Konstruktor třídy NPC
 * 
 * @param jmeno     Jméno NPC
 * @param odpoved   Co nám NPC odpoví, pokud se jí zeptáme
 */ 
    
public NPC (String jmeno, String odpoved){
    this.jmeno = jmeno;
    this.odpoved = odpoved;
    this.otazka = 0;
}

/**
 * Getter. Vrací jméno postavy
 * @return jmeno
 */
public String getJmeno() {
    return jmeno;
}

/**
 * Kolikrát jsme se NPC zeptali
 * 
 * @return počet otázek
 */
public int getOtazka() { 
    return this.otazka;
}

/**
 * Podmínka pro odpověď, pokud se ptáme poprvé NPC odpoví.
 * Pokud podruhé, nic dál neřekne (nebo spustí příslušnou akci).
 * 
 * @return počet odpovědí
 */
public String getOdpoved() {
    if(otazka == 0)
    {
        otazka++;
        return odpoved;
    }
    else {
        return "Nic ti už neřeknu";
        }
}
}
