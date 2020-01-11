package primitivedrawing.Commands;

import primitivedrawing.CommandProcessor;
import primitivedrawing.DrawingCommandProcessor;
import primitivedrawing.DrawingContext;

/**
 * Command which resets position in the graphics context.
 * @author marko
 *
 */
public class ResetCommand extends DrawCommand {

	public ResetCommand(CommandProcessor processor) {
		super(processor);
		this.commandAsString = "reset";
	}

	@Override
	public void execution() {
		DrawingContext dc = ((DrawingCommandProcessor) processor).dc;
		if (dc == null) {
			System.out.println("No drawing context");
			return;
		}
		try {
			dc.reset();
		} catch (Exception e) {
			System.out.println("Invalid argument(s)");
			return;
		}
	}
	
}
