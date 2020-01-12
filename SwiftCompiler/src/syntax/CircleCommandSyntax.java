package syntax;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import primitivedrawing.Commands.CircleCommand;
import primitivedrawing.Commands.Command;
import swift.Scope;

public class CircleCommandSyntax extends Syntax {
	
	public static Pattern pattern = Pattern.compile("^\\s*circle[ ]+(.+)");
	
	public CircleCommandSyntax(Scope scope) {
		this.scope = scope;
	}

	public Matcher evaluate(String code) throws Exception {
		Matcher m = pattern.matcher(code);
		if (m.find()) {
			int r = Integer.parseInt(scope.run(m.group(1)).toString());
			scope.result = null;
			Command command = new CircleCommand(r);
			command.execution();
			return m;
		} else {
			return null;
		}
	}
	
}
