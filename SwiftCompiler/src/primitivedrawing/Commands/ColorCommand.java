package primitivedrawing.Commands;

import primitivedrawing.CommandProcessor;
import primitivedrawing.DrawingCommandProcessor;
import primitivedrawing.DrawingContext;

/**
 * Command which sets the color value for the given context.
 * @author marko
 *
 */
public class ColorCommand extends DrawCommand {

	public ColorCommand(CommandProcessor processor) {
		super(processor);
		numberOfArguments = 3;
		this.commandAsString = "color";
	}
	
	@Override
	public void execution() {
		DrawingContext dc = ((DrawingCommandProcessor) processor).dc;
		if (dc == null) {
			System.out.println("No drawing context");
			return;
		}
		try {
			dc.setColor(Integer.parseInt(arguments.get(0)), Integer.parseInt(arguments.get(1)), Integer.parseInt(arguments.get(2)));
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
			Integer.parseInt(arguments.get(2));
		} catch(Exception e) {
			return false;
		}
		return true;
	}

}
