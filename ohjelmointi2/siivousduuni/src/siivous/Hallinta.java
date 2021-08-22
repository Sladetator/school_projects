package siivous;

import java.util.List;

/**
 * Hallinta-luokka huolehtii luokkien välisestä yhteistyöstä.
 * Metodit ovat pääosin vain "välittäjämetodeja"
 * @author valtteri & viljami järvinen
 * @version 1.3.2018
 *
 */
public class Hallinta {
    private final Tyontekijat tyontekijat = new Tyontekijat();
    private final Tyokohteet tyokohteet = new Tyokohteet();
    
    
    /**
     * Aliohjelma joka lisää työntekijöitä
     * @param tyontekija lisättävä työntekijä
     * @throws SailoException jos tietorakenne jo täynnä
     * @example
     * <pre name="test">
     * #THROWS SailoException
     *  #import fi.jyu.mit.ohj2.Suuntaaja;
     *  Hallinta hallinta = new Hallinta();
     *  Tyontekija datasta2 = new Tyontekija("2 |Susi Sepe  |020347-123T|Kauppatori 2  |40100  |Jyväskylä      |12-03030|siivooja        |osa-aikainen    |15.03.2017|0045321      |Opiskelija          |");
     *  hallinta.lisaa(datasta2);
     *  Tyontekija valle = new Tyontekija();
     *  hallinta.getTyontekijoita() === 1;
     *  hallinta.lisaa(valle);
     *  hallinta.getTyontekijoita() === 2;
     * </pre>
     */
    public void lisaa(Tyontekija tyontekija) throws SailoException {
        tyontekijat.lisaa(tyontekija);
    }
    
    
    /**
     * Aliohjelma joka lisää työkohteita
     * @param tyokohde lisättävä työkohde
     * @example
     * <pre name="test">
     * #THROWS SailoException
     *  Hallinta hallinta = new Hallinta();
     *  Tyokohde kohdedatasta2 = new Tyokohde(" 2 |Toimisto     |Puistokatu 1|40100|Jyväskylä      |0022222 |Paperi Maija |12-41413 |321-3211|1212   |3          |2|huone 2 paperiroskis     |");
     *  Tyokohde t = new Tyokohde();
     *  hallinta.lisaa(kohdedatasta2);
     *  hallinta.lisaa(t);
     *  hallinta.getTyokohteita() === 2;
     * </pre>
     */
    public void lisaa(Tyokohde tyokohde) {
        tyokohteet.lisaa(tyokohde);
    }


    /**
     * Palauttaa i:nnen jäsenen
     * @param i paikka josta haetaan työntekijä
     * @return tyontekijä paikassa i
     * @example
     * <pre name="test">
     *  #THROWS SailoException
     *  Hallinta hallinta = new Hallinta();
     *  Tyontekija valle = new Tyontekija(), valle2 = new Tyontekija(), valle3 = new Tyontekija();
     *  hallinta.lisaa(valle);
     *  hallinta.lisaa(valle2);
     *  hallinta.lisaa(valle3);
     *  hallinta.annaTyontekija(2) === valle3; 
     *  hallinta.annaTyontekija(1) === valle2;
     * </pre>
     */
    public Tyontekija annaTyontekija(int i) {
        return tyontekijat.anna(i);
    }


    /**
     * Palauttaa työntekijöiden määrän
     * @return työntekijöiden määrä
     * @example
     * <pre name="test">
     *  #THROWS SailoException
     *  Hallinta hallinta = new Hallinta();
     *  Tyontekija valle = new Tyontekija(), valle2 = new Tyontekija(), valle3 = new Tyontekija();
     *  hallinta.lisaa(valle);
     *  hallinta.lisaa(valle2);
     *  hallinta.lisaa(valle3);
     *  hallinta.getTyontekijoita() === 3;
     * </pre>
     */
    public int getTyontekijoita() {
        //voiko olla public?
        return tyontekijat.getMaara();
    }
    
    /**
     * hakee työkohteiden määrän
     * @return tyokohteiden määrä
     * @example
     * <pre name="test">
     * #THROWS SailoException
     *  Hallinta hallinta = new Hallinta();
     *  Tyokohde kohdedatasta2 = new Tyokohde(" 2 |Toimisto     |Puistokatu 1|40100|Jyväskylä      |0022222 |Paperi Maija |12-41413 |321-3211|1212   |3          |2|huone 2 paperiroskis     |");
     *  Tyokohde t = new Tyokohde();
     *  hallinta.lisaa(kohdedatasta2);
     *  hallinta.lisaa(t);
     *  hallinta.getTyokohteita() === 2;
     * </pre>
     */
    public int getTyokohteita(){
        return tyokohteet.getKoko2();
    }

    /**
     * hakee listaan tyontekijän tunnusnumeron
     * @param tyontekija työntekijä jonka tunnusnumero halutaan
     * @return työntekijän tunnusnumero
     */
    public List<Tyokohde> annaTyokohteet(Tyontekija tyontekija) {
        return tyokohteet.annaTyokohteet(tyontekija.getTunnusNro());
    }
    
    /**
     * palauttaa työkohteen työntekijän rekisteröintijärjestyksen perusteella
     * @param mones monesko tyokohde annetaan
     * @return työkohteet string taulukkona
     */
    public Tyokohde annaTyokohde(int mones) {
        return tyokohteet.annaTyokohde(mones);
    }
    
    
    /**
     * Pääohjelma luokan testailuun
     * @param args ei käytössä
     * @throws SailoException jos tietorakenne jo täynnä
     */
    public static void main(String[] args) throws SailoException {
        Hallinta hallinta = new Hallinta();
        Tyontekija datasta = new Tyontekija("1|Ville Valle|010245-123U|Kalevankatu 10|40100|Jyväskylä      |12-12324|esimies        |toistaiseksi    |01.01.2003|0012013      |-          |");
        Tyontekija datasta2 = new Tyontekija("2 |Susi Sepe  |020347-123T|Kauppatori 2  |40100  |Jyväskylä      |12-03030|siivooja        |osa-aikainen    |15.03.2017|0045321      |Opiskelija          |");
        Tyontekija datasta3 = new Tyontekija("4 |Ari Arka   |030455-3333|Koulutie 2-4  |43100|Saarijärvi      |12-91919|siivooja        |määräaikainen    |03.05.2014|0032992      |-          |");
        Tyontekija datasta4 = new Tyontekija("5 |Kalle Kokko|121213-555X|Kenttäkuja 2  |41400|Lievestuore      |12-66666|palveluohjaaja        |toistaiseksi    |03.03.2012|0029999      |Kärsii migreenistä          |");
        
        Tyontekija valle = new Tyontekija(), valle2 = new Tyontekija();
        

        hallinta.lisaa(datasta);
        hallinta.lisaa(datasta2);
        hallinta.lisaa(datasta3);
        hallinta.lisaa(datasta4);
        

        valle.rekisteroi();
        valle.vastaa();
        valle2.rekisteroi();
        valle2.vastaa();
        hallinta.lisaa(valle);
        hallinta.lisaa(valle2);
        
        int id1 = valle.getTunnusNro();
        int id2 = valle2.getTunnusNro();
        Tyokohde kohdedatasta = new Tyokohde(" 1 |Kauppa Kallis|Sepänkatu 2 |40100|Jyväskylä      |0022020 |Kauppias Mika|12-31313 |123-3210|0123   |2          |2|roskikset kerran viikossa|");
        Tyokohde kohdedatasta2 = new Tyokohde(" 2 |Toimisto     |Puistokatu 1|40100|Jyväskylä      |0022222 |Paperi Maija |12-41413 |321-3211|1212   |3          |2|huone 2 paperiroskis     |");
        Tyokohde kohdedatasta3 = new Tyokohde(" 3 |Terveysasema |Tapionkatu 5|40100|Jyväskylä      |0031356 |Terve Tuomo  |12-21345 |666-7777|2123   |1.5        |4|-                        |");
        Tyokohde kohdedatasta4 = new Tyokohde(" 4 |Kauppa Halpa |Torikatu 3  |43100|Saarijärvi      |0022313 |Reipas Repe  |12-54321 |787-8787|1111   |1          |5|-                        |");
        
        Tyokohde t = new Tyokohde(id1);
        t.vastaa();
        Tyokohde t2 = new Tyokohde(id2);
        t2.vastaa();

        hallinta.lisaa(kohdedatasta);
        hallinta.lisaa(kohdedatasta2);
        hallinta.lisaa(kohdedatasta3);
        hallinta.lisaa(kohdedatasta4);
        hallinta.lisaa(t);
        hallinta.lisaa(t2);

        for(int i = 0; i < hallinta.getTyontekijoita(); i++) {
            System.out.println(i);
            Tyontekija tyontekija = hallinta.annaTyontekijaTaulukosta(i);
            System.out.println(tyontekija.getNimi());
        }
        /*
        for (int i = 0; i < hallinta.getTyontekijoita(); i++) {
            Tyontekija tyontekija = hallinta.annaTyontekija(i);
            System.out.println("Työntekijän nro taulukossa: " + i);
            tyontekija.tulosta(System.out);
            List<Tyokohde> loytyneet = hallinta.annaTyokohteet(tyontekija);
            for (Tyokohde tyokohde : loytyneet)
                tyokohde.tulosta(System.out);
            System.out.println("--------------------------------------");
        }*/
    }
    
    /**
     * Alustaa Hallinnan työntekijöillä
     * @return hallinta alustettu hallinta-olio
     * @throws SailoException Jos virheellinen data
     */
    public Hallinta alustaHallinta() throws SailoException {
        Hallinta hallinta = new Hallinta();
        Tyontekija datasta = new Tyontekija("1|Ville Valle|010245-123U|Kalevankatu 10|40100|Jyväskylä      |12-12324|esimies        |toistaiseksi    |01.01.2003|0012013      |-          |");
        Tyontekija datasta2 = new Tyontekija("2 |Susi Sepe  |020347-123T|Kauppatori 2  |40100  |Jyväskylä      |12-03030|siivooja        |osa-aikainen    |15.03.2017|0045321      |Opiskelija          |");
        Tyontekija datasta3 = new Tyontekija("4 |Ari Arka   |030455-3333|Koulutie 2-4  |43100|Saarijärvi      |12-91919|siivooja        |määräaikainen    |03.05.2014|0032992      |-          |");
        Tyontekija datasta4 = new Tyontekija("5 |Kalle Kokko|121213-555X|Kenttäkuja 2  |41400|Lievestuore      |12-66666|palveluohjaaja        |toistaiseksi    |03.03.2012|0029999      |Kärsii migreenistä          |");
        
        Tyontekija valle = new Tyontekija(), valle2 = new Tyontekija();
        

        hallinta.lisaa(datasta);
        hallinta.lisaa(datasta2);
        hallinta.lisaa(datasta3);
        hallinta.lisaa(datasta4);
        

        valle.rekisteroi();
        valle.vastaa();
        valle2.rekisteroi();
        valle2.vastaa();
        hallinta.lisaa(valle);
        hallinta.lisaa(valle2);
        
        int id1 = valle.getTunnusNro();
        int id2 = valle2.getTunnusNro();
        Tyokohde kohdedatasta = new Tyokohde(" 1 |Kauppa Kallis|Sepänkatu 2 |40100|Jyväskylä      |0022020 |Kauppias Mika|12-31313 |123-3210|0123   |2          |2|roskikset kerran viikossa|");
        Tyokohde kohdedatasta2 = new Tyokohde(" 2 |Toimisto     |Puistokatu 1|40100|Jyväskylä      |0022222 |Paperi Maija |12-41413 |321-3211|1212   |3          |2|huone 2 paperiroskis     |");
        Tyokohde kohdedatasta3 = new Tyokohde(" 3 |Terveysasema |Tapionkatu 5|40100|Jyväskylä      |0031356 |Terve Tuomo  |12-21345 |666-7777|2123   |1.5        |4|-                        |");
        Tyokohde kohdedatasta4 = new Tyokohde(" 4 |Kauppa Halpa |Torikatu 3  |43100|Saarijärvi      |0022313 |Reipas Repe  |12-54321 |787-8787|1111   |1          |5|-                        |");
        
        Tyokohde t = new Tyokohde(id1);
        t.vastaa();
        Tyokohde t2 = new Tyokohde(id2);
        t2.vastaa();

        hallinta.lisaa(kohdedatasta);
        hallinta.lisaa(kohdedatasta2);
        hallinta.lisaa(kohdedatasta3);
        hallinta.lisaa(kohdedatasta4);
        hallinta.lisaa(t);
        hallinta.lisaa(t2);
        
        return hallinta;
    }

    
    /**
     * Lisää uuden Työkohteen
     */
    public void lisaaTyokohde() {
        Tyokohde uusi = new Tyokohde();
        uusi.rekisteroi();
        uusi.vastaa();
        this.lisaa(uusi);
    }

    /**
     * Etsii työntekijän taulukosta indeksin perusteella
     * @param i indeksi jonka työntekijä halutaa
     * @return työntekijä jota haetaan
     */
    public Tyontekija annaTyontekijaTaulukosta(int i) {
        return tyontekijat.annaTaulukosta(i);
    }
}
