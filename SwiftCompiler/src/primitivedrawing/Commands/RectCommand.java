package primitivedrawing.Commands;

import primitivedrawing.CommandProcessor;
import primitivedrawing.DrawingCommandProcessor;
import primitivedrawing.DrawingContext;

/**
 * Command which draws a rectangle.
 * @author marko
 *
 */
public class RectCommand extends DrawCommand {

	public RectCommand(CommandProcessor processor) {
		super(processor);
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
