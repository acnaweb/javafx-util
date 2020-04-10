package util.javafx.lov;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class LovController implements Initializable, Observer {

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
//
//	@FXML
//	private Button btnFilter;
//
//	@FXML
//	private Button btnClose;
//
//	@FXML
//	private Button btnCancel;
//
//	@FXML
//	private TextField lblText;
//
//	@FXML
//	private TableView<Lov> tblData;
//	private AbstractTableManager<Lov> tableManager;
//	private List<Lov> data;
//
//	private Lov selectecItem;
//
//	@Override
//	public void initialize(URL location, ResourceBundle resources) {
//
//		initListener();
//		createTable();
//
//	}
//
//	private void createTable() {
//		tableManager = new AbstractTableManager<Lov>(tblData, this, false);
//		tableManager.createColumn("Id", "id", 100, CellType.INTEGER);
//		tableManager.createColumn("Valor", "value", 250, CellType.INTEGER);
//	}
//
//	private void initListener() {
//		btnClose.setOnAction((evt) -> {
//			close();
//		});
//
//		btnCancel.setOnAction((evt) -> {
//			selectecItem = null;
//			close();
//		});
//
//		btnFilter.setOnAction((evt) -> {
//			System.out.println("Filtrando");
//			List<Lov> filteredData = this.data.stream().filter(item -> item.getValue().contains(lblText.getText()))
//					.collect(Collectors.toList());
//
//			refreshData(filteredData);
//		});
//
//	}
//
//	private void close() {
//		Stage stage = (Stage) btnClose.getScene().getWindow();
//		stage.close();
//	}
//
//	private void refreshData(List<Lov> data) {
//		tableManager.populate(data);
//	}
//
//	@Override
//	public void update(Observable o, Object data) {
//		selectecItem = (Lov) data;
//	}
//
//	public void setData(List<Lov> data) {
//		this.data = data;
//		refreshData(this.data);
//	}
//
//	public Lov getSelectecItem() {
//		return selectecItem;
//	}
//
//	public void setSelectecItem(Lov selectecItem) {
//		this.selectecItem = selectecItem;
//	}

}
