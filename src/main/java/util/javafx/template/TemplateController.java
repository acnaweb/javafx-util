package util.javafx.template;

import org.springframework.stereotype.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import net.footstats.javafx.controller.CrudController;
import net.footstats.javafx.form.FormController;
import net.footstats.javafx.table.AbstractTableManager;
import net.footstats.javafx.table.TableController;
import net.footstats.model.EntityBase;
import net.footstats.model.Match;

@Controller
public class TemplateController extends CrudController<EntityBase> {
	// @Autowired
	private Object templateService;

	@FXML
	private FormController formController;

	@FXML
	private TableController<EntityBase> tableController;

	@FXML
	private ScrollPane contentDetail;

	@FXML
	private Button btnCrud;

	@Override
	protected Match newInstanceItem() {
		return new Match();
	}

	@Override
	protected void createTableManager() {
		tableManager = new AbstractTableManager<EntityBase>(tableController.getTableMaster(), this, false);

	}

	@Override
	protected void initListeners() {
		// CRUD
		setContentCrud(contentDetail.getContent());

		setService(service);
		setGridControls(formController.getGridControls());

		// buttons
		setRefreshAction(tableController.getBtnRefresh());

		setNewAction(formController.getBtnNew());
		setSaveAction(formController.getBtnSave());
		setCancelAction(formController.getBtnCancel());
		setRemoveAction(formController.getBtnRemove());

		btnCrud.setOnAction(evt -> {
			contentDetail.setContent(getContentCrud());
		});
	}

	@Override
	protected void createControls() {
//		addControl("Id", "id", 70, CellType.INTEGER, true, null);
//		addControl("Campeonato", "championship", 250, CellType.TEXTFIELD, true, null);
//		addControl("Copa", "cupName", 250, CellType.TEXTFIELD, true, null);
//		addControl("Temporada", "season", 120, CellType.TEXTFIELD, true, null);
//		addControl("Categoria", "category", 120, CellType.ENUM, true, CategoryType.class);
//		addControl("Tipo Coleta", "collectType", 150, CellType.ENUM, false, CollectType.class);
//		addControl("Equipes", "numberTeams", 70, CellType.INTEGER, false, null);
//		addControl("Logo", "urlLogo", 120, CellType.TEXTFIELD, false, null);
//		addControl("SDE Campeonato", "sdeCampeonatoId", 150, CellType.INTEGER, true, null);
	}

	@Override
	protected void bindModelToControl() {
//		setValueToControl("id", selectedItem.getId());
//		setValueToControl("championship", selectedItem.getChampionship());

	}

	@Override
	protected void bindControlToModel() {
//		selectedItem.setParameter(getValueFromControl("parameter"));
//		selectedItem.setValue(getValueFromControl("value"));
//		selectedItem.setDescription(getValueFromControl("description"));
	}

}
