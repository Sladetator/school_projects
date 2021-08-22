package siivous;

import java.io.*;


/**
 * Kerhon j�sen joka osaa mm. itse huolehtia tunnusNro:staan.
 *
 * @author valtteri & viljami j�rvinen
 * @version 20.3.2018
 */
public class Tyontekija {
    private int tunnusNro;
    private String nimi = "";
    private String sotu = "";
    private String katuosoite = "";
    private String postinro = "";
    private String postios = "";
    private String puhnro = "";
    private String tyonimike = "";
    private String tyosuhde = "";
    private String alkamispvm = "";
    private String tyontekijanro = "";
    private String lisatietoja = "";

    private static int seuraavaNro    = 1;

    /**
     * Alustetaan ty�ntekij� tietokannasta tulevan merkkijonon perusteella
     * @param data tietokannasta haettu merkkijono
     * @throws SailoException Jos tietokannan rivi ei ole oikeassa muodossa
     */
    public Tyontekija(String data) throws SailoException {
        String[] tiedot = data.split("\\|");
        if(tiedot.length != 12) throw new SailoException("Riviss� jotain vikaa!");
        this.nimi = tiedot[1].trim();
        this.sotu = tiedot[2].trim();
        this.katuosoite = tiedot[3].trim();
        this.postinro = tiedot[4].trim();
        this.postios = tiedot[5].trim();
        this.puhnro = tiedot[6].trim();
        this.tyonimike = tiedot[7].trim();
        this.tyosuhde = tiedot[8].trim();
        this.alkamispvm = tiedot[9].trim();
        this.tyontekijanro = tiedot[10].trim();
        this.lisatietoja = tiedot[11].trim();
        this.rekisteroi(Integer.parseInt(tiedot[0].trim()));
    }
    
    
    /**
     * Alustetaan ty�ntekij� ilman muuttujia
     */
    public Tyontekija() {
        //Ei mit��n t�h�n
    }
    
    
    /**
     * Apumetodi joka palauttaa randomin ty�ntekij�n
     * Puhelinnumero ja id nimen per��n arvotaan ett� ei olisi t�ysin samoja tietoja
     */
    public void vastaa() {
        nimi = "Ville Valle " +( (int)(Math.random() * 9000 + 1) );
        sotu = "010245-123U";
        katuosoite = "Kalevankatu 10";
        postinro = "40100";
        postios = "Jyv�skyl�";
        puhnro = "12-"+( (int)(Math.random() * 90000 + 1) );
        tyonimike = "esimies";
        tyosuhde = "toistaiseksi";
        alkamispvm = "01.01.2003";
        tyontekijanro = "0012013";
        lisatietoja = "Ei lis�tietoja";
    }


    /**
     * Rekister�i ty�ntekij�n omalle tunnukselleen
     * @param tunnus annettu tunnusnumero
     * @return ty�ntekij�n tunnusnro
     * @example
     * <pre name="test">
     *  Tyontekija t = new Tyontekija();
     *  t.getTunnusNro() === 0;
     *  t.rekisteroi();
     *  t.getTunnusNro() === 1;
     *  Tyontekija t2 = new Tyontekija();
     *  t2.rekisteroi();
     *  t2.getTunnusNro() === 2;
     * </pre>
     */
    public int rekisteroi(int tunnus) {
        tunnusNro = tunnus;
        seuraavaNro = tunnus + 1;
        return tunnusNro;
    }


    /**
     * Antaa ty�ntekij�lle seuraavan rekisterinumeron. 
     * @return ty�ntekij�n tunnusnro
     * @example
     * <pre name="test">
     * Tyontekija t3= new Tyontekija();
     * t3.rekisteroi();
     * t3.getTunnusNro() === 4;
     * </pre>
     */
    public int rekisteroi() {
        tunnusNro = seuraavaNro;
        seuraavaNro++;
        return tunnusNro;
    }
    

    /**
     * Tulostetaan ty�ntekij�n tiedot
     * @param os tietovirta johon tulostetaan
     * #import fi.jyu.mit.ohj2.Suuntaaja;
     * @example
     * <pre name="test">
     * #THROWS SailoException
     *  Tyontekija datasta = new Tyontekija("1|Ville Valle|010245-123U|Kalevankatu 10|40100|Jyv�skyl�      |12-12324|esimies        |toistaiseksi    |01.01.2003|0012013      |-          |");
     *  Suuntaaja.StringOutput so = new Suuntaaja.StringOutput();
     *  datasta.tulosta(System.out);
     *  String tulos = "Ty�ntekij�\nVille Valle 0012013\nSOTU: 010245-123U\nOSOITE: Kalevankatu 10, 40100 Jyv�skyl�\nPuh:12-12324\nNimike: esimies toistaiseksi aloitti: 01.01.2003\nLis�tietoja:\n-\nTunnusnro: 1\n\n"; 
     *  so.ero(tulos) === null;
     *  so.palauta();
     * </pre>
     */
    private void tulosta(PrintStream out) {
        if("".equals(nimi)) {
            out.println("Tyhj� ty�ntekij�!\n");
            return;
        }
        out.println("Ty�ntekij�\n" + nimi + " " + tyontekijanro);
        out.println(
                "SOTU: " + sotu+"\n"+
                "OSOITE: " +katuosoite+", "+
                postinro+" "+
                postios+"\n"+
                "Puh:" + puhnro+"\n"+
                "Nimike: " +tyonimike+" "+
                tyosuhde+" aloitti: "+
                alkamispvm+"\n"+
                "Lis�tietoja:\n" + lisatietoja + "\nTunnusnro: " + tunnusNro + "\n" );
    }

    
    /**
     * Tulostetaan ty�ntekij�n tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }

    /**
     * Palauttaa ty�ntekij�n tunnusnumeron
     * @return tunnusnumero
     * @example
     * <pre name="test">
     *  Tyontekija t = new Tyontekija();
     *  t.getTunnusNro() === 0;
     *  t.rekisteroi();
     *  t.getTunnusNro() === 2;
     *  Tyontekija t2 = new Tyontekija();
     *  t2.rekisteroi();
     *  t2.getTunnusNro() === 3;
     * </pre>
     */
    public int getTunnusNro() {
        return tunnusNro;
    }
    
    /**
     * Testip��ohjelma Ty�ntekij�lle
     * @param args ei k�yt�ss�
     * @throws SailoException jos data on v��rin
     */
    public static void main(String args[]) throws SailoException {
        Tyontekija t = new Tyontekija(), t2 = new Tyontekija();
        Tyontekija datasta = new Tyontekija("1|Ville Valle|010245-123U|Kalevankatu 10|40100|Jyv�skyl�      |12-12324|esimies        |toistaiseksi    |01.01.2003|0012013      |-          |");
        t.rekisteroi();
        t2.rekisteroi();
        
        t.tulosta(System.out);
        t.vastaa();
        t.tulosta(System.out);
        
        t2.vastaa();
        t2.tulosta(System.out);
        
        System.out.println("");
        t.tulosta(System.out);
        t2.vastaa();
        t2.tulosta(System.out);
        
        System.out.println("===================== MERKKIJONOSTA TEHTY =====================");
        datasta.tulosta(System.out);
    }

    /**
     * hakee nimen
     * @return ty�ntekij�n nimen
     */
    public String getNimi() {
        return nimi;
    }

    /**
     * hakee hakuosoite
     * @return ty�ntekij�n katuosoite
     */
    public String getKatuosoite() {
        return katuosoite;
    }


    /**
     * hakee postinumeron
     * @return ty�ntekij�n postinumero
     */
    public String getPostinro() {
        return postinro;
    }


    /**
     * hakee puhelinnumeron
     * @return ty�ntekij�n puhelinnumero
     */
    public String getPuhnro() {
        return puhnro;
    }


    /**
     * hakee ty�suhteen alkamisp�iv�n
     * @return ty�ntekij�n ty�suhteen alkamisp�iv�n
     */
    public String getAlkamispvm() {
        return alkamispvm;
    }


    /**
     * hakee ty�ntekij�n sosiaaliturvatunnuksen
     * @return ty�ntekij�n sotu
     */
    public String getSotu() {
        return sotu;
    }


    /**
     * hakee ty�nimikkeen
     * @return ty�ntekij�n ty�nimike
     */
    public String getTyonimike() {
        return tyonimike;
    }


    /**
     * hakee ty�suhteen laadin
     * @return ty�ntekij�n ty�suhteen laatu
     */
    public String getTyosuhde() {
        return tyosuhde;
    }


    /**
     * hakee ty�ntekij�n postiosoitteen
     * @return ty�ntekij�n postiosoite
     */
    public String getPostiosoite() {
        return postios;
    }


    /**
     * hakee ty�ntekij�n lis�tiedot
     * @return ty�ntekij�n lis�tiedot
     */
    public String getLisatietoja() {
        return lisatietoja;
    }


    /**
     * hakee ty�ntekij�n ty�ntekij�numeron
     * @return ty�ntekij�n ty�ntekij�numero
     */
    public String getTyontekijanro() {
        return tyontekijanro;
    }

}