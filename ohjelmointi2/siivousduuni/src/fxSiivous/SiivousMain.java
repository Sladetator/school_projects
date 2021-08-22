package fxSiivous;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


/**
 * @author valtteri järvinen, viljami järvinen
 * @version 13.2.2018
 *
 * Pääluokka, joka käynnistää ohjelman
 */
public class SiivousMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("SiivousGUIView.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Käynnistää käyttöliittymän
	 * @param args ei käytössä
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
