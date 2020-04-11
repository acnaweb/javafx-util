package util.javafx.function;

import java.util.Map;

import javafx.scene.Node;

@FunctionalInterface
public interface OnControlToModelListener<T> {
	void bind(Map<String, Node> controls, T data);
}
