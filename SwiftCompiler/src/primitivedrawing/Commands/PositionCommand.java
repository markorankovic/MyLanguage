package primitivedrawing.Commands;

import primitivedrawing.CommandProcessor;
import primitivedrawing.DrawingCommandProcessor;
import primitivedrawing.DrawingContext;
import swift.Driver;

/**
 * Command which alters the drawing contexts current position.
 * @author marko
 *
 */
public class PositionCommand extends DrawCommand {
	
	public PositionCommand(int x, int y) {
		super(Driver.pd.commandFrame.dcp);
		this.arguments.add(x + "");
		this.arguments.add(y + "");
		this.commandAsString = "position";
		this.numberOfArguments = 2;
	}

	@Override
	public void execution() {
		DrawingContext dc = ((DrawingCommandProcessor) processor).dc;
		if (dc == null) {
			System.out.println("No drawing context");
			return;
		}
		try {
			dc.position(Integer.parseInt(arguments.get(0)), Integer.parseInt(arguments.get(1)));
		} catch (Exception e) {
			System.out.println("Invalid argument(s)");
			return;
		}
	}
	
	@Override
	public
	boolean argumentsValid() {
		return validNumberOfArguments() && validArgumentTypes();
	}
	
	@Override
	boolean validArgumentTypes() {
		try {
			Integer.parseInt(arguments.get(0));
			Integer.parseInt(arguments.get(1));
		} catch(Exception e) {
			return false;
		}
		return true;
	}
	
}
