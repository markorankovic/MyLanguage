package primitivedrawing;

import primitivedrawing.Commands.CircleCommand;
import primitivedrawing.Commands.ClearCommand;
import primitivedrawing.Commands.ColorCommand;
import primitivedrawing.Commands.Command;
import primitivedrawing.Commands.DrawToCommand;
import primitivedrawing.Commands.PositionCommand;
import primitivedrawing.Commands.RectCommand;
import primitivedrawing.Commands.ResetCommand;
import primitivedrawing.Commands.RunCommand;
import primitivedrawing.Commands.TriangleCommand;

/**
 * CommandProcessor intended for processing and executing commands for drawing graphics
 * @author marko
 *
 */
public class DrawingCommandProcessor extends CommandProcessor {
	
	public DrawingContext dc; // Interface for drawing

	// Associates input command strings with Command classes
	@Override
	void initializeCommandReference() { 
		commandReference.put("rect", (Class<? extends Command>) RectCommand.class);
		commandReference.put("circle", (Class<? extends Command>) CircleCommand.class);
		commandReference.put("triangle", (Class<? extends Command>) TriangleCommand.class);
		commandReference.put("color", (Class<? extends Command>) ColorCommand.class);
		commandReference.put("drawto", (Class<? extends Command>) DrawToCommand.class);
		commandReference.put("position", (Class<? extends Command>) PositionCommand.class);
		commandReference.put("clear", (Class<? extends Command>) ClearCommand.class);
		commandReference.put("reset", (Class<? extends Command>) ResetCommand.class);
		commandReference.put("run", (Class<? extends Command>) RunCommand.class);
	}
		
	public DrawingCommandProcessor() {
		super();
		initializeCommandReference();											
	}
	
	public DrawingCommandProcessor(DrawingContext dc) {
		this();
		this.dc = dc;
	}
	
	void setFocusToGraphics() { dc.getFocus(); } // Puts focus back on the drawing context
	
	// Does the same as its superclass but puts focus on the drawing context if the Run Command is executed
	@Override
	Command parseCommandString(String commandString) { 
		Command command = super.parseCommandString(commandString);
		if (command == null) {
			return null;
		}
		if (command.getClass() == RunCommand.class) {
			setFocusToGraphics();
		}
		return command;
	}
	
}
