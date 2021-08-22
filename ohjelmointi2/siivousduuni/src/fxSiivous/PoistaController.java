package fxSiivous;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import siivous.GUIAliohjelmat;


/**
 * @author valtteri järvinen, viljami järvinen
 * @version 13.2.2018
 * 
 * Kontrolleri poista-ikkunalle
 */
public class PoistaController {

    @FXML private Button btnKylla;
    @FXML private Button btnEi;
    
    private GUIAliohjelmat ali = new GUIAliohjelmat();

    /**
     * Sulkee ikkunan
     */
    @FXML
    private void poista() {
        ali.naytaEiToimi();
    }



    /**
     * Sulkee ikkunan
     */
    @FXML
    private void sulje() {
        ali.suljeIkkuna(btnKylla);
    }
}
