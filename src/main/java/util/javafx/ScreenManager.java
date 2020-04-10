package util.javafx;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.javafx.demo.DemoApp;

public final class ScreenManager {
	private static Parent main;

	public static void showMain(Stage stage, String fxml) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(DemoApp.class.getResource(fxml));
		main = loader.load();

		Scene scene = new Scene(main);
		stage.setTitle("Demo Application");
		stage.setScene(scene);
		stage.setMaximized(true);
		stage.show();
	}

}
