package util.javafx.function;

@FunctionalInterface
public interface OnPersistListener<T> {
	T persist(T data);
}
