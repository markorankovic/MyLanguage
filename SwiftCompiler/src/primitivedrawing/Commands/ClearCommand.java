package primitivedrawing.Commands;

import primitivedrawing.CommandProcessor;
import primitivedrawing.DrawingCommandProcessor;
import primitivedrawing.DrawingContext;

/**
 * Command which clears the underlying canvas.
 * @author marko
 *
 */
public class ClearCommand extends DrawCommand {

	public ClearCommand(CommandProcessor processor) {
		super(processor);
		this.commandAsString = "clear";
	}

	@Override
	public void execution() {
		DrawingContext dc = ((DrawingCommandProcessor) processor).dc;
		if (dc == null) {
			System.out.println("No drawing context");
			return;
		}
		try {
			dc.clear();
		} catch (Exception e) {
			System.out.println("Invalid argument(s)");
			return;
		}
	}
	
}
