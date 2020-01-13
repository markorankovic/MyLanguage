package primitivedrawing.Commands;

import primitivedrawing.DrawingCommandProcessor;
import primitivedrawing.DrawingContext;
import swift.Driver;

/**
 * Command which draws a line to a point.
 * @author marko
 *
 */
public class DrawToCommand extends DrawCommand {

	public DrawToCommand(int x, int y) {
		super(Driver.pd.commandFrame.dcp);
		this.arguments.add(x + "");
		this.arguments.add(y + "");
		this.commandAsString = "drawTo";
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
			dc.drawTo(Integer.parseInt(arguments.get(0)), Integer.parseInt(arguments.get(1)));
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
