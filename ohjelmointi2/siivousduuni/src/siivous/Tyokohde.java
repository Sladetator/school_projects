package siivous;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * @author valtteri & viljami järvinen
 * @version 10.3.2018
 * Luokka työkohteen tekoon
 */
public class Tyokohde {
    private int tunnusNro;
    private int tyontekijaNro = -1;
    private String nimi = "";
    private String katuosoite = "";
    private String postinro = "";
    private String postios = "";
    private String kohdenro = "";
    private String yhthenkilo = "";
    private String yhtpuhnro= "";
    private String avainnro = "";
    private String halytys = "";
    private double tavoiteaika= 0;
    private String lisatietoja= "";

    private static int seuraavaNro    = 1;
    
    
    /**
     * Alustetaan työntekijä tietokannasta tulevan merkkijonon perusteella
     * @param data tietokannasta haettu merkkijono
     * @throws SailoException Jos tietokannan rivi ei ole oikeassa muodossa
     */
    public Tyokohde(String data) throws SailoException {
        String[] tiedot = data.split("\\|");
        if(tiedot.length != 13) throw new SailoException("Rivissä jotain vikaa!");
        this.nimi = tiedot[1].trim();
        this.katuosoite = tiedot[2].trim();
        this.postinro = tiedot[3].trim();
        this.postios = tiedot[4].trim();
        this.kohdenro = tiedot[5].trim();
        this.yhthenkilo = tiedot[6].trim();
        this.yhtpuhnro = tiedot[7].trim();
        this.avainnro = tiedot[8].trim();
        this.halytys = tiedot[9].trim();
        this.tavoiteaika = Double.parseDouble(tiedot[10].trim());
        this.tyontekijaNro = Integer.parseInt(tiedot[11].trim());
        this.lisatietoja = tiedot[12].trim();
        this.rekisteroi(Integer.parseInt(tiedot[0].trim()));
    }
    /**
     * Alustetaan työkohde
     */
    public Tyokohde() {
        //Ei vielä mitään
    }
    
    /**
     * Alustetaan kohde, jolla on työntekijä
     * @param nro kohteen työntekijän numero
     */
    public Tyokohde(int nro) {
        this.tyontekijaNro = nro;
    }
    
    /**
     * Apumetodi joka palauttaa randomin työntekijän
     * Puhelinnumero ja id nimen perään arvotaan että ei olisi täysin samoja tietoja
     */
    public void vastaa() {
        nimi = "Kauppa Kallis " +( (int)(Math.random() * 9000 + 1) );
        katuosoite = "Sepänkatu 2";
        postinro = "40100";
        postios = "Jyväskylä";
        kohdenro = String.format("%07d", (int)(Math.random() * 900000 + 1) );
        yhthenkilo = "Kauppias Mika";
        yhtpuhnro = "12-31313";
        avainnro = "123-3210";
        halytys = "0123";
        tavoiteaika= 2;
        lisatietoja = "Ei lisätietoja";
    }
    
    
    /**
     * Rekisteröi työkohteen omalle tunnukselleen
     * @param tunnus annettu tunnusnumero
     * @return työkohteen tunnusnro
     * #import fi.jyu.mit.ohj2.Suuntaaja;
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * Tyokohde t = new Tyokohde(), t2 = new Tyokohde();
     * Tyokohde kohdedatasta = new Tyokohde(" 1 |Kauppa Kallis|Sepänkatu 2 |40100|Jyväskylä      |0022020 |Kauppias Mika|12-31313 |123-3210|0123   |2          |2|roskikset kerran viikossa|");
     * t.rekisteroi();
     * t2.rekisteroi();
     * kohdedatasta.getTyontekijaNro() === 2;
     * </pre>
     */
    public int rekisteroi(int tunnus) {
        tunnusNro = tunnus;
        seuraavaNro = tunnus + 1;
        return tunnusNro;
    }

    /**
     * Antaa työntekijälle seuraavan rekisterinumeron. 
     * @return työntekijän tunnusnro
     */
    public int rekisteroi() {
        tunnusNro = seuraavaNro;
        seuraavaNro++;
        return tunnusNro;
    }
    

    /**
     * Tulostetaan työntekijän tiedot
     * @param os tietovirta johon tulostetaan
     * <pre name="test">
     * #THROWS SailoException
     * Tyokohde kohdedatasta = new Tyokohde(" 1 |Kauppa Kallis|Sepänkatu 2 |40100|Jyväskylä      |0022020 |Kauppias Mika|12-31313 |123-3210|0123   |2          |2|roskikset kerran viikossa|");
     * Suuntaaja.StringOutput so = new Suuntaaja.StringOutput();
     * kohdedatasta.tulosta(System.out);
     * String tulos = "Työkohde\nKauppa Kallis\nOsoite: Sepänkatu 2, 40100 Jyväskylä\nKohteen numero:0022020\nYhteyshenkilö:Kauppias Mika puh: 12-31313\nAvaimen nro: 123-3210 hälytyskoodi: 0123\nTavoiteaika: 2.0\nLisätietoja: roskikset kerran viikossa\nTyöntekijän numero: 2\n\n";
     * so.ero(tulos) === null;
     * so.palauta();
     */
    private void tulosta(PrintStream out) {
        if("".equals(nimi)) {
            out.println("Tyhjä työkohde!\n");
            return;
        }
        out.println("Työkohde\n" + nimi);
        out.println(
                "Osoite: " + katuosoite + ", "+ 
                postinro+ " " +
                postios + "\n"+
                "Kohteen numero:" + kohdenro + "\n"+
                "Yhteyshenkilö:" + yhthenkilo + " puh: "+
                yhtpuhnro + "\n"+
                "Avaimen nro: " + avainnro + " hälytyskoodi: "+
                halytys + "\n"+
                "Tavoiteaika: " + tavoiteaika + "\n"+
                "Lisätietoja: "+ lisatietoja+ "\n" +
                "Työntekijän numero: " + tyontekijaNro + "\n"
                );
    }

    
    /**
     * Tulostetaan työntekijän tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        
        tulosta(new PrintStream(os));
    }
    
    /**
     * hakee työntekijän numeron
     * @return työntekijän numero
     */
    public int getTyontekijaNro() {
        return tyontekijaNro;
    }

    
    /**
     * Palauttaa työkohteen nimen
     * @return nimi
     */
    public String getNimi() {
        return nimi;
        
    }
    
    /**
     * Testipääohjelma Työntekijälle
     * @param args ei käytössä
     * @throws SailoException jos rivi ei ole oikein
     */
    public static void main(String args[]) throws SailoException {
        Tyokohde t = new Tyokohde(), t2 = new Tyokohde();
        Tyokohde kohdedatasta = new Tyokohde(" 1 |Kauppa Kallis|Sepänkatu 2 |40100|Jyväskylä      |0022020 |Kauppias Mika|12-31313 |123-3210|0123   |2          |2|roskikset kerran viikossa|");
        t.rekisteroi();
        t2.rekisteroi();

        kohdedatasta.tulosta(System.out);
        t.tulosta(System.out);
        t.vastaa();
        t.tulosta(System.out);
        
        t2.vastaa();
        t2.tulosta(System.out);
        
        System.out.println("");
        t.tulosta(System.out);
        t2.vastaa();
        t2.tulosta(System.out);
    }
    
    /**
     * hakee katuosoitteen
     * @return työkohteen katuosoite
     */
    public String getKatuosoite() {
        return katuosoite;
    }
    /**
     *hakee postinumeron 
     * @return työkohteen postinumero
     */
    public String getPostinro() {
        return postinro;
    }
    
    /**
     *hakee kohdenumeron 
     * @return työkohteen kohdenumero
     */
    public String getKohdenro() {
        return kohdenro;
    }
    
    /**
     * hakee yhteyshenkilön
     * @return työkohteen yhteyshenkilö
     */
    public String getYhthenkilo() {

        return yhthenkilo;
    }
    
    /**
     * hakee puhelinnumeron
     * @return yhteyshenkilön puhelinnumero
     */
    public String getPuhnro() {
         
        return yhtpuhnro;
    }
    
    /**
     * hakee avainnumeron
     * @return työkohteen avainnumero
     */
    public String getAvainnro() {
         
        return avainnro;
    }
    
    /**
     * hakee kohteen hälytysnumeron
     * @return työkohteen hälytysnumero
     */
    public String getHalytys() {
         
        return halytys;
    }
    
    /**
     * hakee tavoiteajan
     * @return työkohteen tavoiteaika
     */
    public double getTavoiteaika() {
         
        return tavoiteaika;
    }
    
    /**
     * hakee postiosoitteen
     * @return työkohteen postiosoite
     */
    public String getPostiosoite() {
         
        return postios;
    }
    
    /**
     * hakee lisätiedot
     * @return työkohteen lisätiedot
     */
    public String getLisatietoja() {
         
        return lisatietoja;
    }

}
