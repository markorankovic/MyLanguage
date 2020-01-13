package primitivedrawing.Commands;

import primitivedrawing.DrawingCommandProcessor;
import primitivedrawing.DrawingContext;
import swift.Driver;

/**
 * Command which draws a rectangle.
 * @author marko
 *
 */
public class RectCommand extends DrawCommand {
		
	public RectCommand(int width, int height) {
		super(Driver.pd.commandFrame.dcp);
		this.arguments.add(width + "");
		this.arguments.add(height + "");
		this.commandAsString = "rect";
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
			dc.drawRect(Integer.parseInt(arguments.get(0)), Integer.parseInt(arguments.get(1)));
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
