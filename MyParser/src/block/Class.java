package block;

public class Class extends Block {
	
	private String name;
	
	public Class(String name) {
		super(null);
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public void run() {
		
	}
	
}
