package util.javafx.form.function;

import util.javafx.form.FormState;

public interface Form<T> {

	T getSelectedItem();

	void setSelectedItem(T selectedItem);

	void createForm(FormBuilder builder);

	void setOnRefreshListener(OnRefreshListener onRefreshListener);

	void setOnControlToModelListener(OnControlToModelListener<T> onControlToModelListener);

	void setOnModelToControlListener(OnModelToControlListener<T> onModelToControlListener);

	void setOnSelectListener(OnSelectListener<T> onSelectListener);

	void setOnPersistListener(OnPersistListener<T> onPersistListener);

	void initListeners();

	void updateControls(FormState formState);

	boolean validateListeners();

	default void init(FormBuilder builder) {
		updateControls(FormState.SETTING_UP);
		createForm(builder);
		initListeners();
		
		updateControls(FormState.IDLE);
	}

}
