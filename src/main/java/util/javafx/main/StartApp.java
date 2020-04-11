package util.javafx.main;

import java.io.IOException;

import util.javafx.AbstractApplication;
import util.javafx.ScreenManager;

public class StartApp extends AbstractApplication {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	protected void buildScene() throws IOException {
		ScreenManager.showMain(stage, "/util/javafx/main/MainScreen.fxml");
	}

}
