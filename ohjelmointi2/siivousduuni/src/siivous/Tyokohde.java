package siivous;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * @author valtteri & viljami j�rvinen
 * @version 10.3.2018
 * Luokka ty�kohteen tekoon
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
     * Alustetaan ty�ntekij� tietokannasta tulevan merkkijonon perusteella
     * @param data tietokannasta haettu merkkijono
     * @throws SailoException Jos tietokannan rivi ei ole oikeassa muodossa
     */
    public Tyokohde(String data) throws SailoException {
        String[] tiedot = data.split("\\|");
        if(tiedot.length != 13) throw new SailoException("Riviss� jotain vikaa!");
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
     * Alustetaan ty�kohde
     */
    public Tyokohde() {
        //Ei viel� mit��n
    }
    
    /**
     * Alustetaan kohde, jolla on ty�ntekij�
     * @param nro kohteen ty�ntekij�n numero
     */
    public Tyokohde(int nro) {
        this.tyontekijaNro = nro;
    }
    
    /**
     * Apumetodi joka palauttaa randomin ty�ntekij�n
     * Puhelinnumero ja id nimen per��n arvotaan ett� ei olisi t�ysin samoja tietoja
     */
    public void vastaa() {
        nimi = "Kauppa Kallis " +( (int)(Math.random() * 9000 + 1) );
        katuosoite = "Sep�nkatu 2";
        postinro = "40100";
        postios = "Jyv�skyl�";
        kohdenro = String.format("%07d", (int)(Math.random() * 900000 + 1) );
        yhthenkilo = "Kauppias Mika";
        yhtpuhnro = "12-31313";
        avainnro = "123-3210";
        halytys = "0123";
        tavoiteaika= 2;
        lisatietoja = "Ei lis�tietoja";
    }
    
    
    /**
     * Rekister�i ty�kohteen omalle tunnukselleen
     * @param tunnus annettu tunnusnumero
     * @return ty�kohteen tunnusnro
     * #import fi.jyu.mit.ohj2.Suuntaaja;
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * Tyokohde t = new Tyokohde(), t2 = new Tyokohde();
     * Tyokohde kohdedatasta = new Tyokohde(" 1 |Kauppa Kallis|Sep�nkatu 2 |40100|Jyv�skyl�      |0022020 |Kauppias Mika|12-31313 |123-3210|0123   |2          |2|roskikset kerran viikossa|");
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
     * Antaa ty�ntekij�lle seuraavan rekisterinumeron. 
     * @return ty�ntekij�n tunnusnro
     */
    public int rekisteroi() {
        tunnusNro = seuraavaNro;
        seuraavaNro++;
        return tunnusNro;
    }
    

    /**
     * Tulostetaan ty�ntekij�n tiedot
     * @param os tietovirta johon tulostetaan
     * <pre name="test">
     * #THROWS SailoException
     * Tyokohde kohdedatasta = new Tyokohde(" 1 |Kauppa Kallis|Sep�nkatu 2 |40100|Jyv�skyl�      |0022020 |Kauppias Mika|12-31313 |123-3210|0123   |2          |2|roskikset kerran viikossa|");
     * Suuntaaja.StringOutput so = new Suuntaaja.StringOutput();
     * kohdedatasta.tulosta(System.out);
     * String tulos = "Ty�kohde\nKauppa Kallis\nOsoite: Sep�nkatu 2, 40100 Jyv�skyl�\nKohteen numero:0022020\nYhteyshenkil�:Kauppias Mika puh: 12-31313\nAvaimen nro: 123-3210 h�lytyskoodi: 0123\nTavoiteaika: 2.0\nLis�tietoja: roskikset kerran viikossa\nTy�ntekij�n numero: 2\n\n";
     * so.ero(tulos) === null;
     * so.palauta();
     */
    private void tulosta(PrintStream out) {
        if("".equals(nimi)) {
            out.println("Tyhj� ty�kohde!\n");
            return;
        }
        out.println("Ty�kohde\n" + nimi);
        out.println(
                "Osoite: " + katuosoite + ", "+ 
                postinro+ " " +
                postios + "\n"+
                "Kohteen numero:" + kohdenro + "\n"+
                "Yhteyshenkil�:" + yhthenkilo + " puh: "+
                yhtpuhnro + "\n"+
                "Avaimen nro: " + avainnro + " h�lytyskoodi: "+
                halytys + "\n"+
                "Tavoiteaika: " + tavoiteaika + "\n"+
                "Lis�tietoja: "+ lisatietoja+ "\n" +
                "Ty�ntekij�n numero: " + tyontekijaNro + "\n"
                );
    }

    
    /**
     * Tulostetaan ty�ntekij�n tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        
        tulosta(new PrintStream(os));
    }
    
    /**
     * hakee ty�ntekij�n numeron
     * @return ty�ntekij�n numero
     */
    public int getTyontekijaNro() {
        return tyontekijaNro;
    }

    
    /**
     * Palauttaa ty�kohteen nimen
     * @return nimi
     */
    public String getNimi() {
        return nimi;
        
    }
    
    /**
     * Testip��ohjelma Ty�ntekij�lle
     * @param args ei k�yt�ss�
     * @throws SailoException jos rivi ei ole oikein
     */
    public static void main(String args[]) throws SailoException {
        Tyokohde t = new Tyokohde(), t2 = new Tyokohde();
        Tyokohde kohdedatasta = new Tyokohde(" 1 |Kauppa Kallis|Sep�nkatu 2 |40100|Jyv�skyl�      |0022020 |Kauppias Mika|12-31313 |123-3210|0123   |2          |2|roskikset kerran viikossa|");
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
     * @return ty�kohteen katuosoite
     */
    public String getKatuosoite() {
        return katuosoite;
    }
    /**
     *hakee postinumeron 
     * @return ty�kohteen postinumero
     */
    public String getPostinro() {
        return postinro;
    }
    
    /**
     *hakee kohdenumeron 
     * @return ty�kohteen kohdenumero
     */
    public String getKohdenro() {
        return kohdenro;
    }
    
    /**
     * hakee yhteyshenkil�n
     * @return ty�kohteen yhteyshenkil�
     */
    public String getYhthenkilo() {

        return yhthenkilo;
    }
    
    /**
     * hakee puhelinnumeron
     * @return yhteyshenkil�n puhelinnumero
     */
    public String getPuhnro() {
         
        return yhtpuhnro;
    }
    
    /**
     * hakee avainnumeron
     * @return ty�kohteen avainnumero
     */
    public String getAvainnro() {
         
        return avainnro;
    }
    
    /**
     * hakee kohteen h�lytysnumeron
     * @return ty�kohteen h�lytysnumero
     */
    public String getHalytys() {
         
        return halytys;
    }
    
    /**
     * hakee tavoiteajan
     * @return ty�kohteen tavoiteaika
     */
    public double getTavoiteaika() {
         
        return tavoiteaika;
    }
    
    /**
     * hakee postiosoitteen
     * @return ty�kohteen postiosoite
     */
    public String getPostiosoite() {
         
        return postios;
    }
    
    /**
     * hakee lis�tiedot
     * @return ty�kohteen lis�tiedot
     */
    public String getLisatietoja() {
         
        return lisatietoja;
    }

}
