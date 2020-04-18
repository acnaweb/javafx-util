package app;

import app.main.ScreenManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class StartApp extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		ScreenManager.showMain(stage, "Main", "/app/main/MainScreen.fxml");

	}

}
