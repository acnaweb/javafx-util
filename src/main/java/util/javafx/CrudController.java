package util.javafx;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class CrudController<T> implements Initializable, Observer {

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}


//
//	
//

//
//	@SuppressWarnings("unchecked")
//	@Override
//	public void update(Observable o, Object data) {
//		updateControls(FormState.EDITING);
//		selectedItem = (T) data;
//		bindModelToControl();
//	}
//
//	@Override
//	public void initialize(URL location, ResourceBundle resources) {
//		defaultListeners();
//		initListeners();
//		createTableManager();
//		createControls();
//		updateControls(FormState.IDLE);
//
//	}
//
//	private void defaultListeners() {
//
//	}
//
//
//	
//
//
//
//	public void setStatus(Label label) {
//		this.status = label;
//	}
//
//	protected void setNewAction(Button btn) {
//		btnNew = btn;
//
//		btnNew.setOnAction(evt -> {
//			selectedItem = newInstanceItem();
//			updateControls(FormState.EDITING);
//		});
//	}
//
//	protected void setRemoveAction(Button btn) {
//		btnRemove = btn;
//
//		btnRemove.setOnAction(evt -> {
//			service.delete(selectedItem);
//			selectedItem = null;
//			updateControls(FormState.IDLE);
//			btnRefresh.fire();
//		});
//	}
//
//	protected void setRefreshAction(Button btn) {
//		btnRefresh = btn;
//		btnRefresh.setOnAction(evt -> {
//			Service<Void> serviceWork = new Service<Void>() {
//
//				@Override
//				protected Task<Void> createTask() {
//					return new Task<Void>() {
//
//						@Override
//						protected Void call() throws Exception {
//							updateControls(FormState.LOADING);
//
//							selectedItem = null;
//							List<T> data;
//							tableManager.removeAllItems();
//							try {
//								data = (List<T>) service.findByIsDeletedFalse();
//
//								tableManager.populate(data);
//							} catch (Exception e) {
//								e.printStackTrace();
//								showAlertError("getItems" + e.getMessage());
//							}
//
//							updateControls(FormState.IDLE);
//							return null;
//						}
//					};
//				}
//			};
//
//			if (stage != null)
//				stage.titleProperty().bind(serviceWork.titleProperty());
//
//			if (status != null)
//				status.textProperty().bind(serviceWork.messageProperty());
//
//			serviceWork.setOnSucceeded(par1 -> {
//				// System.out.println(Calendar.getInstance().toString());
//			});
//
//			serviceWork.setOnFailed(par1 -> {
//				showAlertError(par1.getEventType().getName());
//
//			});
//			serviceWork.start();
//
//		});
//	}
//


//

}
