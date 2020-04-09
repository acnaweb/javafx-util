package util.javafx;

import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public final class LayoutUtil {

	public static void showProgressIndicator() {
		VBox vbox = new VBox();
		ProgressIndicator progressIndicator = new ProgressIndicator();

		vbox.getChildren().add(progressIndicator);

		Scene scene = new Scene(vbox);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.show();
	}

}
