package util.javafx.function;

@FunctionalInterface
public interface OnSelectListener<T> {
	void notify(T data);
}
