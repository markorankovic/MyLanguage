package block;

import swift.Parameter;
import swift.Type;
import swift.Value;
import swift.Variable;

public class Method extends Block {

	private String name;
	private Type type;
	private Parameter[] params;
	private Value returnValue;
	
	public Method(Block superBlock, String name, Type type, Parameter[] params) {
		super(superBlock);
		
		this.name = name;
		this.type = type;
		this.params = params;
	}

	@Override
	public void run() {
		invoke();
	}
		
	public Value invoke(Value... values) {
		if (values.length != params.length) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < values.length && i < params.length; i++) {
			Parameter p = params[i];
			Value v = values[i];
			if (p.getType() != v.getType()) {
				throw new IllegalStateException();
			}
			
			addVariable(new Variable(this, p.getType(), p.getName(), v.getValue()));
		}
		for (Block b : getSubBlocks()) {
			b.run();
			if (returnValue != null) {
				break;
			}
		}
		
		if (returnValue == null && type != Type.VOID) {
			throw new IllegalStateException();
		}
		
		Value localReturnValue = returnValue;
		returnValue = null;
		return localReturnValue;
	}
	
}
