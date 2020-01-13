package syntax;
import java.util.regex.Matcher;
import swift.Scope;

/*
 * Abstract class in which subclasses evaluate code for its particular purpose.
 */

public abstract class Syntax {
	
	public Scope scope;
	
	public static String block = "\\{((?:(?:[^\\{\\}])|(?:\\{(?:(?:(?:[^\\{\\}])|(?:\\{[^\\}]*\\}))*)\\}))*)\\}";

	/*
	 * Evaluates the given code.
	 * @param code	the code to be run.
	 */
	public abstract Matcher evaluate(String code) throws Exception;
}