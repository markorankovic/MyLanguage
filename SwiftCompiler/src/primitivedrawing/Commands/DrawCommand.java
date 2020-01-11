package primitivedrawing.Commands;

import primitivedrawing.CommandProcessor;

/**
 * Command intended for performing graphics operations.
 * @author marko
 *
 */
public abstract class DrawCommand extends Command {
	
	public DrawCommand(CommandProcessor processor) {
		super(processor);
	}
	
}
