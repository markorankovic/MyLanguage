package primitivedrawing.Commands;

import primitivedrawing.CommandProcessor;
import primitivedrawing.DrawingCommandProcessor;
import primitivedrawing.DrawingContext;
import swift.Driver;


/**
 * Command which creates a circle to be drawn in the context.
 * @author marko
 *
 */
public class CircleCommand extends DrawCommand {
	
	public CircleCommand(int r) {
		super(Driver.pd.commandFrame.dcp);
		this.arguments.add(r + "");
		this.commandAsString = "circle";
		this.numberOfArguments = 1;
	}
	
	@Override
	public void execution() {
		System.out.println(5000);
		DrawingContext dc = ((DrawingCommandProcessor) processor).dc;
		if (dc == null) {
			System.out.println("No drawing context");
			return;
		}
		try {
			System.out.println(1000);
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
