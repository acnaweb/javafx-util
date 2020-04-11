package util.javafx.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DialogUtils {

	public static void showAlert(String msg) {
		Alert alert = new Alert(AlertType.INFORMATION);
//		alert.setTitle("Titulo");
//		alert.setHeaderText("Cabeçalho");
		alert.setContentText(msg);
		alert.showAndWait();
	}

	public static void showAlertError(String msg) {
		Alert alert = new Alert(AlertType.ERROR);
//		alert.setTitle("Titulo");
//		alert.setHeaderText("Cabeçalho");
		alert.setContentText(msg);
		alert.show();
//		alert.showAndWait().ifPresent(rs -> {
//			if (rs == ButtonType.OK) {
//				System.out.println("Pressed OK.");
//			}
//		});
	}
}
