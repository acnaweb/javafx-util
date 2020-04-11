package util.javafx.form.function;

@FunctionalInterface
public interface OnSelectListener<T> {
	void notify(T data);
}
