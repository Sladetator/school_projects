package fxSiivous;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import siivous.GUIAliohjelmat;


/**
 * @author valtteri järvinen, viljami järvinen
 * @version 13.2.2018
 * 
 * Kontrolleri tietoja-ikkunalle
 */
public class TietojaController {

    @FXML private Button btnOK;
    
    private GUIAliohjelmat ali = new GUIAliohjelmat();

    /**
     * Sulkee ikkunan
     */
    @FXML
    private void suljeIkkuna() {
        ali.suljeIkkuna(btnOK);
    }


}
