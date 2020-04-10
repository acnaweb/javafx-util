package util.javafx.demo;

import java.io.IOException;

import util.javafx.AbstractApplication;
import util.javafx.ScreenManager;

public class DemoApp extends AbstractApplication {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	protected void buildScene() throws IOException {
		ScreenManager.showMain(stage, "/util/javafx/demo/DemoScreen.fxml");
	}

}
