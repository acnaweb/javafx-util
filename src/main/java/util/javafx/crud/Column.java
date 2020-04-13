package util.javafx.crud;

public class Column {

	private String header;
	private String attribute;
	private int percentWidth;
	private ControlType controlType;
	@SuppressWarnings("rawtypes")
	private Class enumData;
	private Scope scope;

	@SuppressWarnings("rawtypes")
	public Column(String header, String attribute, int percentWidth, ControlType controlType, Scope scope,
			Class enumData) {
		this.header = header;
		this.attribute = attribute;
		this.percentWidth = percentWidth;
		this.controlType = controlType;
		this.enumData = enumData;
		this.scope = scope;
	}

	public String getHeader() {
		return header;
	}

	public String getAttribute() {
		return attribute;
	}

	public int getPercentWidth() {
		return percentWidth;
	}

	public ControlType getControlType() {
		return controlType;
	}

	@SuppressWarnings("rawtypes")
	public Class getEnumData() {
		return enumData;
	}

	public Scope getScope() {
		return scope;
	}

	public static enum Scope {
		TABLE, FORM, BOTH
	}

}
