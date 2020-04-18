package util.javafx.function;

public interface OnPersistListener<T> {
	T persist(T data);

	void delete(T data);
}
