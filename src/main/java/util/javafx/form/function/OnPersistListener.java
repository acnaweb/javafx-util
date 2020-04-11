package util.javafx.form.function;

@FunctionalInterface
public interface OnPersistListener<T> {
	T persist(T data);
}
