package fxSiivous;

import fi.jyu.mit.fxgui.ListChooser;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import siivous.GUIAliohjelmat;
import siivous.Hallinta;
import siivous.SailoException;
import siivous.Tyokohde;
import siivous.Tyontekija;


/**
 * @author valtteri järvinen, viljami järvinen
 * @version 13.2.2018
 * 
 * Kontrolleriluokka Työkohteet-ikkunalle
 */
public class TyokohteetController {
    private GUIAliohjelmat ali = new GUIAliohjelmat();

    @FXML private Button btnLisaa;

    @FXML private Button btnPoista;

    @FXML private Button btnMuokkaa;

    @FXML private Button btnTallenna;

    @FXML private MenuItem menuVaihda;

    @FXML private MenuItem menuTallenna;

    @FXML private MenuItem menuSulje;

    @FXML private MenuItem menuLisaa;

    @FXML private MenuItem menuPoista;

    @FXML private MenuItem menuApua;

    @FXML private MenuItem menuTietoja;
    

    @FXML
    private TextField txtNimi;

    @FXML
    private TextField txtKatuosoite;

    @FXML
    private TextField txtPostinro;

    @FXML
    private TextField txtKohdenro;

    @FXML
    private TextField txtYhthenkilo;

    @FXML
    private TextField txtPuhnro;

    @FXML
    private TextField txtAvainnro;

    @FXML
    private TextField txtHalytys;

    @FXML
    private TextField txtTyontekija;

    @FXML
    private TextField txtTavoiteaika;

    @FXML
    private TextField txtPostiosoite;

    @FXML
    private TextField txtLisatietoja;
    
    @FXML
    private ListChooser<Tyokohde> lcHaku;
    
    private Hallinta hallinta;

    /**
     * Tehdään käynnistäessä, alustaa ikkunan
     */
    @FXML
    public void initialize() {
        hallinta = new Hallinta();
        try {
            hallinta = hallinta.alustaHallinta();
        } catch (SailoException e) {
            e.printStackTrace();
        }
        alustaChooser();
    }
    /**
     * Tekee muut tarvittavat alustukset, asettaa ensimmäisen työkohteen oletukseksi
     */
    private void alustaChooser() {
        lcHaku.clear();
        for(int i = 0; i < hallinta.getTyokohteita(); i++) {
            Tyokohde tyokohde = hallinta.annaTyokohde(i);
            lcHaku.add(tyokohde.getNimi(), tyokohde);
        }
        lcHaku.setSelectedIndex(0);
        lcHaku.addSelectionListener(e -> asetaTekstit(e));
        asetaTekstit(hallinta.annaTyokohde(lcHaku.getSelectedIndex()));
    }
    
    /**
     * Asettaa työkohteen tiedot työkohde-ruutuun
     * @param tyokohde
     */
    private void asetaTekstit(Tyokohde tyokohde) {
        txtNimi.setText(tyokohde.getNimi());
        txtKatuosoite.setText(tyokohde.getKatuosoite());
        txtPostinro.setText(tyokohde.getPostinro());
        txtKohdenro.setText(tyokohde.getKohdenro());
        txtYhthenkilo.setText(tyokohde.getYhthenkilo());
        txtPuhnro.setText(tyokohde.getPuhnro());
        txtAvainnro.setText(tyokohde.getAvainnro());
        txtHalytys.setText(tyokohde.getHalytys());
        
        try {
            Tyontekija tyontekija = hallinta.annaTyontekija(tyokohde.getTyontekijaNro());
            txtTyontekija.setText(tyontekija.getNimi());
        } catch (NullPointerException e) {
            txtTyontekija.setText("Ei löytynyt työntekijää");
        } catch (IndexOutOfBoundsException e) {
            txtTyontekija.setText("Ei vielä asetettu työntekijää" );
        }
        
        txtTavoiteaika.setText(Double.toString(tyokohde.getTavoiteaika()));
        txtPostiosoite.setText(tyokohde.getPostiosoite());
        txtLisatietoja.setText(tyokohde.getLisatietoja());
    }

    /**
     * Avaa Apua-ikkunan
     */
    @FXML
    private void avaaApua() {
        ali.naytaEiToimi();
    }


    /**
     * Avaa Lisäysnäkymän. Tyhjentää näkyvillä olevat tiedot ja avaa ne muokattaviksi
     */
    @FXML
    private void avaaLisaa() {
        hallinta.lisaaTyokohde();
        alustaChooser();
    }


    /**
     * Avaa Muokkausnäkymän. Avaa näkyvillä olevat tiedot muokattaviksi
     */
    @FXML
    private void avaaMuokkaus() {
        ali.naytaEiToimi();

    }


    /**
     * Avaa ikkunan, jossa kysytään halutaanko poistaa nyt avattu
     */
    @FXML
    private void avaaPoista() {
        ali.avaaIkkuna("Poista");
    }


    /**
     * Avaa tallennusikkunan, jossa kysytään halutaanko tallentaa
     */
    @FXML
    private void avaaTallenna() {
        ali.naytaEiToimi();
    }


    /**
     * Avaa Tietoja-ikkunan
     */
    @FXML
    private void avaaTietoja() {
        ali.avaaIkkuna("Tietoja");

    }


    /**
     * Avaa Työntekijät-näkymän ja sulkee tämän ikkunan alta pois
     */
    @FXML
    private void avaaTyontekijat() {
        ali.avaaIkkuna("Tyontekijat");
        ali.suljeIkkuna(btnMuokkaa);
    }


    /**
     * Sulkee ohjelman
     */
    @FXML
    private void sulje() {
        ali.sulje();
    }

}
