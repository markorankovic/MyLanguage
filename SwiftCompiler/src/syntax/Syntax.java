package syntax;
import java.util.regex.Matcher;

import swift.Scope;

public abstract class Syntax {
	
	public Scope scope;
	
	public static String block = "\\{((?:(?:[^\\{\\}])|(?:\\{[^\\}]*\\}))*)\\}";

	public abstract Matcher evaluate(String code) throws Exception;
}







