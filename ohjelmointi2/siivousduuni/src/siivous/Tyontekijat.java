/**
 * 
 */
package siivous;

/**
 * @author valtteri & viljami järvinen
 * @version 1.3.2018
 *
 */
public class Tyontekijat {
    private  Tyontekija[] tyontekijat;
    private int tyontekijoita;
    
    /**
     * Alustaa Tyontekijat-olion
     */
    public Tyontekijat() {
        this.tyontekijat = new Tyontekija[8];
        this.tyontekijoita = 0;
    }

    /**
     * Lisää uuden työntekijän taulukkoon ja kasvattaa työntekijöiden määrää yhdellä.
     * TODO: Kasvata taulukkoa jos enemmän kuin 8 työntekjää
     * @param tyontekija lisättävä työntekijä
     * @throws SailoException jos tietorakenne on täynnä
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * Tyontekijat tyontekijat = new Tyontekijat();
     * Tyontekija t = new Tyontekija(), t2 = new Tyontekija();
     * t.rekisteroi();
     * t2.rekisteroi();
     * tyontekijat.lisaa(t);
     * tyontekijat.getMaara() === 1;
     * tyontekijat.lisaa(t2);
     * tyontekijat.getMaara() === 2;
     * 
     * </pre>
     */
    public void lisaa(Tyontekija tyontekija) throws SailoException{
        if(tyontekijoita > tyontekijat.length) throw new SailoException("Liikaa alkioita"); 
        tyontekijat[tyontekijoita] = tyontekija;
        tyontekijoita++;
    }

    /**
     * Palauttaa taulukossa tällä hetkellä olevien työntekijöiden määrän
     * @return työntekijöiden määrä
     * <pre name="test">
     * #THROWS SailoException
     * Tyontekijat tyontekijat = new Tyontekijat();
     * Tyontekija t = new Tyontekija(), t2 = new Tyontekija();
     * t.rekisteroi();
     * t2.rekisteroi();
     * tyontekijat.lisaa(t);
     * tyontekijat.getMaara() === 1;
     * tyontekijat.lisaa(t2);
     * tyontekijat.getMaara() === 2;
     */
    public int getMaara() {
        return tyontekijoita;
    }

    /**
     * Palauttaa taulukon i:nnessä paikalla olevan työntekijän
     * @param i monesko paikka
     * @return työntekijä i:nnessä paikassa
     * @throws IndexOutOfBoundsException jos annetaan indeksi, jota ei ole taulukossa
     * <pre name="test">
     * #THROWS SailoException
     * Tyontekijat tyontekijat = new Tyontekijat();
     * Tyontekija t = new Tyontekija(), t2 = new Tyontekija();
     * t.rekisteroi();
     * t2.rekisteroi();
     * tyontekijat.lisaa(t);
     * tyontekijat.lisaa(t2);
     * tyontekijat.anna(0) === t;
     * tyontekijat.anna(1) === t2;
     */
    public Tyontekija anna(int i) throws IndexOutOfBoundsException {
        if(i > tyontekijat.length) throw new IndexOutOfBoundsException("Laiton indeksi " + i);
        for(Tyontekija tyontekija : tyontekijat) {
            if(tyontekija.getTunnusNro() == i) return tyontekija;
        }
        return null;
    }
    
    
    /**
     * Pääohjelma luokalle
     * @param args ei käytössä
     * @throws SailoException jos tietorakenne täynnä
     */
    public static void main(String[] args) throws SailoException {
        Tyontekijat tyontekijat = new Tyontekijat();
        /*Tyontekija t = new Tyontekija(), t2 = new Tyontekija();
        t.rekisteroi();
        t2.rekisteroi();
        t.vastaa();
        t2.vastaa();
        
        tyontekijat.lisaa(t);
        tyontekijat.lisaa(t2);

        Tyontekija datasta = new Tyontekija("1|Ville Valle|010245-123U|Kalevankatu 10|40100|Jyväskylä      |12-12324|esimies        |toistaiseksi    |01.01.2003|0012013      |-          |");
        tyontekijat.lisaa(datasta);

        for (int i = 0; i < tyontekijat.getMaara(); i++) {
            Tyontekija jasen = tyontekijat.anna(i);
            System.out.println("Paikka taulukossa: " + i);
            jasen.tulosta(System.out);
        }*/
        Tyontekija datasta = new Tyontekija("1|Ville Valle|010245-123U|Kalevankatu 10|40100|Jyväskylä      |12-12324|esimies        |toistaiseksi    |01.01.2003|0012013      |-          |");
        Tyontekija datasta2 = new Tyontekija("2 |Susi Sepe  |020347-123T|Kauppatori 2  |40100  |Jyväskylä      |12-03030|siivooja        |osa-aikainen    |15.03.2017|0045321      |Opiskelija          |");
        Tyontekija datasta3 = new Tyontekija("4 |Ari Arka   |030455-3333|Koulutie 2-4  |43100|Saarijärvi      |12-91919|siivooja        |määräaikainen    |03.05.2014|0032992      |-          |");
        Tyontekija datasta4 = new Tyontekija("5 |Kalle Kokko|121213-555X|Kenttäkuja 2  |41400|Lievestuore      |12-66666|palveluohjaaja        |toistaiseksi    |03.03.2012|0029999      |Kärsii migreenistä          |");
        
        Tyontekija valle = new Tyontekija(), valle2 = new Tyontekija();
        

        tyontekijat.lisaa(datasta);
        tyontekijat.lisaa(datasta2);
        tyontekijat.lisaa(datasta3);
        tyontekijat.lisaa(datasta4);
        

        valle.rekisteroi();
        valle.vastaa();
        valle2.rekisteroi();
        valle2.vastaa();
        tyontekijat.lisaa(valle);
        tyontekijat.lisaa(valle2);
        
        for (int i = 0; i < tyontekijat.getMaara(); i++) {
            Tyontekija jasen = tyontekijat.anna(i);
            System.out.println("Paikka taulukossa: " + i);
            jasen.tulosta(System.out);
        }
    
    }

    /**
     * hakee työntekijän indeksin perusteella
     * @param i indeksi jolla haetaan
     * @return työntekijä taulukosta indeksin kohdalta
     */
    public Tyontekija annaTaulukosta(int i) {
        return tyontekijat[i];
    }
}
