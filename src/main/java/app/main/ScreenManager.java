package app.main;

import java.io.IOException;

import app.StartApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public final class ScreenManager {
	private static FXMLLoader loader;
	private static Stage primaryStage;
	private static BorderPane root;
	private static MainController mainController;

	public static void setLoader(FXMLLoader loader) {
		ScreenManager.loader = loader;
	}

	public static void showMain(Stage stage, String title, String fxml) throws IOException {
		ScreenManager.primaryStage = stage;

		setLoader(new FXMLLoader());
		loader.setLocation(StartApp.class.getResource(fxml));
		root = loader.load();
		mainController = loader.getController();

		Scene scene = new Scene(root);
		ScreenManager.primaryStage.setTitle(title);
		ScreenManager.primaryStage.setScene(scene);
		ScreenManager.primaryStage.setMaximized(true);
		ScreenManager.primaryStage.show();
	}

	public static void showCustomer() {
		final String SCREEN_CONTENT = "/app/main/customer/CustomerScreen.fxml";
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
