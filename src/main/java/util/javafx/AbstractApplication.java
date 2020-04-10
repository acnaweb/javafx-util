package util.javafx;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public abstract class AbstractApplication extends Application {
	protected static Stage stage;

	public static Stage getStage() {
		return stage;
	}

	protected abstract void initListeners();

	protected abstract void buildScene() throws IOException;

	@Override
	public void start(Stage primaryStage) throws Exception {

		initListeners();

		stage = primaryStage;

		buildScene();

	}

}
