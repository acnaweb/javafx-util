package util.javafx;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import util.javafx.main.MainController;

public final class ScreenManager {
	private static FXMLLoader loader;
	private static Stage stage;
	private static BorderPane main;
	private static MainController mainController;

	public static void setLoader(FXMLLoader loader) {
		ScreenManager.loader = loader;
	}

	public static void showMain(Stage stage, String title, String fxml) throws IOException {
		ScreenManager.stage = stage;

		setLoader(new FXMLLoader());
		loader.setLocation(StartApp.class.getResource(fxml));
		main = loader.load();
		mainController = loader.getController();

		Scene scene = new Scene(main);
		ScreenManager.stage.setTitle(title);
		ScreenManager.stage.setScene(scene);
		ScreenManager.stage.setMaximized(true);
		ScreenManager.stage.show();
	}

	public static void showCustomer() {
		final String SCREEN_CONTENT = "/util/javafx/customer/CustomerScreen.fxml";
		try {
			Parent layout;
			FXMLLoader localLoader = new FXMLLoader();
			localLoader.setControllerFactory(loader.getControllerFactory());
			localLoader.setLocation(ScreenManager.class.getResource(SCREEN_CONTENT));
			layout = localLoader.load();

			mainController.setMainContent(layout);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
