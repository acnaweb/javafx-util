package util.javafx.function;

import java.util.Map;

import javafx.scene.Node;

@FunctionalInterface
public interface OnModelToControlListener<T> {
	void bind(Map<String, Node> controls, T data);
}
