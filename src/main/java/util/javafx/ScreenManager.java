package util.javafx;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import util.javafx.demo.DemoApp;

public final class ScreenManager {
	private static Stage stage;
	private static BorderPane main;

	public static void showMain(Stage stage, String fxml) throws IOException {
		ScreenManager.stage = stage;

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(DemoApp.class.getResource(fxml));
		main = loader.load();

		Scene scene = new Scene(main);
		ScreenManager.stage.setTitle("Demo Application");
		ScreenManager.stage.setScene(scene);
		ScreenManager.stage.setMaximized(true);
		ScreenManager.stage.show();
	}

}
