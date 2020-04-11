package util.javafx.listener;

import java.util.Map;

import javafx.scene.Node;

public interface OnValueToControlListener<T> {
	void bind(Map<String, Node> controls, T data);
}
