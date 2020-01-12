package structures;

public class Variable {

	private String name;
	private Object value;
	
	public Variable() { }
	
	public Variable(String name, Object value) {
		this.name = name;
		this.value = value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public Object getValue() {
		return value;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return value.toString();
	}
	
}
