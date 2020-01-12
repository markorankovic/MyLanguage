package swift;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import primitivedrawing.Commands.CircleCommand;
import primitivedrawing.Commands.ClearCommand;
import primitivedrawing.Commands.RectCommand;
import structures.Variable;

public class Scope {

	Syntax[] syntax = {
		new FunctionDefinitionSyntax(this),
		new FunctionCallSyntax(this),
		new CircleCommandSyntax(this),
		new MoveToCommandSyntax(this),
		new DrawToCommandSyntax(this),
		new RectCommandSyntax(this),
		new IfBlockSyntax(this),
		new WhileBlockSyntax(this),
		new VariableDeclarationSyntax(this),
		new OperatorSyntax(this),
		new LiteralValueSyntax(this),
		new VariableValueSyntax(this),
	};

	ArrayList<Variable> vs = new ArrayList<>();
	
	public HashMap<String, Variable> map = new HashMap<>();
	
	public Object result = null;
	
	public Object run(String code) throws Exception {
		
		String trimmedCode = code.trim();
		if (trimmedCode.isEmpty()) {
			return result;
		}
		
		for (Syntax s : syntax) {
			Matcher m = s.evaluate(trimmedCode);
			if (m != null) {
				String remainingCode = trimmedCode.substring(m.group(0).length());
				return run(remainingCode);
			}
		}
		
		throw new Exception("Could not compile: " + trimmedCode);
	}
	
	public Variable getVariable(String name) {
		return map.get(name);
	}
}
