package primitivedrawing.Commands;

import primitivedrawing.CommandProcessor;
import primitivedrawing.DrawingCommandProcessor;
import primitivedrawing.DrawingContext;


/**
 * Command which creates a circle to be drawn in the context.
 * @author marko
 *
 */
public class CircleCommand extends DrawCommand {

	public CircleCommand(CommandProcessor processor) {
		super(processor);
		numberOfArguments = 1;
		this.commandAsString = "circle";
	}
	
	@Override
	public void execution() {
		DrawingContext dc = ((DrawingCommandProcessor) processor).dc;
		if (dc == null) {
			System.out.println("No drawing context");
			return;
		}
		try {
			dc.drawCircle(Integer.parseInt(arguments.get(0)));
		} catch (Exception e) {
			System.out.println("Invalid argument(s)");
			return;
		}
	}
	
	@Override
	boolean validArgumentTypes() {
		try {
			Integer.parseInt(arguments.get(0));
		} catch(Exception e) {
			return false;
		}
		return true;
	}
	
	@Override
	public
	boolean argumentsValid() {
		return validNumberOfArguments() && validArgumentTypes();
	}
	
}
