package syntax;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import primitivedrawing.Commands.Command;
import primitivedrawing.Commands.PositionCommand;
import swift.Scope;

public class MoveToCommandSyntax extends Syntax {
	
	public static Pattern pattern = Pattern.compile("^\\s*move to[ ]+(.+)[ ]*,[ ]*(.+)");
	
	public MoveToCommandSyntax(Scope scope) {
		this.scope = scope;
	}

	public Matcher evaluate(String code) throws Exception {
		Matcher m = pattern.matcher(code);
		if (m.find()) {
			int x = Integer.parseInt(scope.run(m.group(1)).toString());
			scope.result = null;
			int y = Integer.parseInt(scope.run(m.group(2)).toString());
			scope.result = null;
			Command command = new PositionCommand(x, y);
			command.execution();
			return m;
		} else {
			return null;
		}
	}
	
}