package util.javafx;

import java.io.IOException;

public class StartApp extends AbstractApplication {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	protected void buildScene() throws IOException {
		ScreenManager.showMain(stage, "Main", "/util/javafx/main/MainScreen.fxml");
	}

}
