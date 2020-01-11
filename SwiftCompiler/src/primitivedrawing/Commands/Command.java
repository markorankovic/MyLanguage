package primitivedrawing.Commands;

import java.util.ArrayList;

import primitivedrawing.CommandProcessor;
import primitivedrawing.Executable;

/**
 * Command which provides default features for its subclasses to be executed by the processor.
 * @author marko
 *
 */
public abstract class Command implements Executable {
	
	CommandProcessor processor;
	
	String commandAsString = "Command"; // Command as string so the command can be saved into a file 
		
	public Command(CommandProcessor processor) {
		this.processor = processor;
	}
	
	// Arguments passed as input to Command
	public ArrayList<String> arguments = new ArrayList<String>();
	
	// Number of arguments acceptable
	int numberOfArguments = 0;
		
	// Operation of the Command
	@Override
	public void execution() { }
	
	boolean validNumberOfArguments() { // Checks to see if a valid number of arguments have been passed
		if (arguments.size() != numberOfArguments) {
			System.out.println("Invalid number of arguments");
			return false;
		}
		return true;
	}
	
	// Checks if the types for the arguments are valid
	boolean validArgumentTypes() {
		return false;
	}
	
	// Checks if the arguments in general are valid
	public boolean argumentsValid() { 
		return validNumberOfArguments() && numberOfArguments == 0;
	}
	
	public String toString() { // Constructs the command and its arguments as a String
		String result = "";
		result += commandAsString + " ";
		for (int i = 0; i < arguments.size(); i++) {
			result += arguments.get(i);
			if (i < arguments.size() - 1) {
				result += ", ";
			}
		}
		return result;
	}

}
