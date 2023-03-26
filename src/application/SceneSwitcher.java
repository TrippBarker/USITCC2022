package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneSwitcher {
	
	public Stage stage;
	
	public SceneSwitcher(Stage stage) {
		this.stage = stage;
	}
	
	public void switchScene(String scenePath) throws IOException {
		Scene scene = new Scene(FXMLLoader.load(getClass().getResource(scenePath)));
		stage.setScene(scene);
		stage.show();
	}
}
