package swift;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import primitivedrawing.Commands.CircleCommand;
import primitivedrawing.Commands.Command;
import primitivedrawing.Commands.DrawToCommand;
import primitivedrawing.Commands.PositionCommand;
import primitivedrawing.Commands.RectCommand;
import structures.Variable;

abstract class Syntax {
	
	Scope scope;

	abstract Matcher evaluate(String code) throws Exception;
}

class VariableDeclarationSyntax extends Syntax {
	
	static Pattern pattern = Pattern.compile("^\\s*(\\w+)[ ]+=[ ]+(.*)");
	
	VariableDeclarationSyntax(Scope scope) {
		this.scope = scope;
	}

	Matcher evaluate(String code) throws Exception {
		Matcher m = pattern.matcher(code);
		if (m.find()) {
			String varName = m.group(1);
			String valueExpr = m.group(2);
			Object value = scope.run(valueExpr);
			scope.map.put(varName, new Variable(varName, value));
			return m;
		} else {
			return null;
		}
	}
}

class VariableValueSyntax extends Syntax {
	
	static Pattern pattern = Pattern.compile("^\\s*(\\b[a-zA-Z]\\w*)");
	
	VariableValueSyntax(Scope scope) {
		this.scope = scope;
	}

	Matcher evaluate(String code) throws Exception {
		Matcher m = pattern.matcher(code);
		if (m.find()) {
			scope.result = scope.getVariable(m.group(1)).getValue();
			return m;
		} else {
			return null;
		}
	}
}

class LiteralValueSyntax extends Syntax {
	
	static Pattern pattern = Pattern.compile("^\\s*((?:\\d+)|(?:\\\"[^\"]*\\\")|(?:true)|(?:false))");
	
	LiteralValueSyntax(Scope scope) {
		this.scope = scope;
	}

	Matcher evaluate(String code) throws Exception {
		Matcher m = pattern.matcher(code);
		if (m.find()) {
			scope.result = m.group(1);
			return m;
		} else {
			return null;
		}
	}
}

class OperatorSyntax extends Syntax {
	
	static Pattern pattern = Pattern.compile("^\\s*(.+)[ ]+([\\+\\-\\*\\/\\<\\>])[ ]+(.+)");
	
	OperatorSyntax(Scope scope) {
		this.scope = scope;
	}

	Matcher evaluate(String code) throws Exception {
		Matcher m = pattern.matcher(code);
		if (m.find()) {
			int lhs = Integer.parseInt(scope.run(m.group(1)).toString());
			String op = m.group(2);
			int rhs = Integer.parseInt(scope.run(m.group(3)).toString());
			switch (op) {
			case "+": scope.result = lhs + rhs; break;
			case "-": scope.result = lhs - rhs; break;
			case "*": scope.result = lhs * rhs; break;
			case "/": scope.result = lhs / rhs; break;
			case "<": scope.result = lhs < rhs; break;
			case ">": scope.result = lhs > rhs; break;
			default: throw new Exception("Unknown operator: " + op);
			}
			return m;
		} else {
			return null;
		}
	}
}

class WhileBlockSyntax extends Syntax {
	
	static Pattern pattern = Pattern.compile("^\\s*while[ ]+(.+)[ ]+\\{\\s*([^\\}]+)\\s*\\}");
	
	WhileBlockSyntax(Scope scope) {
		this.scope = scope;
	}

	Matcher evaluate(String code) throws Exception {
		Matcher m = pattern.matcher(code);
		if (m.find()) {
			String condition = m.group(1);
			String block = m.group(2);
			int i = 0;
			while (i < 100 && scope.run(condition).toString() == "true") {
				scope.result = scope.run(block);
				i++;
			}
			return m;
		} else {
			return null;
		}
	}
}

class IfBlockSyntax extends Syntax {
	
	static Pattern pattern = Pattern.compile("^\\s*if[ ]+(.+)[ ]+\\{\\s*([^\\}]+)\\s*\\}(?:\\s*else[ ]+\\{\\s*([^\\}]+)\\s*\\})?");
	
	IfBlockSyntax(Scope scope) {
		this.scope = scope;
	}

	Matcher evaluate(String code) throws Exception {
		Matcher m = pattern.matcher(code);
		if (m.find()) {
			String condition = m.group(1);
			String ifBlock = m.group(2);
			String elseBlock = m.group(3);
			if (scope.run(condition).toString() == "true") {
				scope.result = scope.run(ifBlock);
			} else if (elseBlock != null) {
				scope.result = scope.run(elseBlock);				
			}
			return m;
		} else {
			return null;
		}
	}
}

class FunctionDefinitionSyntax extends Syntax {
	
	static Pattern pattern = Pattern.compile("^\\s*fun[ ]+(\\w+)[ ]+((?:\\w+[ ]+)+)?\\{\\s*([^}]*)\\s*\\}");
	
	FunctionDefinitionSyntax(Scope scope) {
		this.scope = scope;
	}

	Matcher evaluate(String code) throws Exception {
		Matcher m = pattern.matcher(code);
		if (m.find()) {
			String name = m.group(1);
			String fun = m.group(0);
			scope.map.put(name, new Variable(name, fun));
			scope.result = null;
			return m;
		} else {
			return null;
		}
	}
	
}

class FunctionCallSyntax extends Syntax {
	
	static Pattern pattern = Pattern.compile("^\\s*do[ ]+(\\w+)[ ]+(.+)?");
	
	FunctionCallSyntax(Scope scope) {
		this.scope = scope;
	}

	Matcher evaluate(String code) throws Exception {
		Matcher m = pattern.matcher(code);
		if (m.find()) {
			String name = m.group(1);
			String argList = m.group(2);
			String fun = scope.getVariable(name).getValue().toString();

			Matcher mDef = FunctionDefinitionSyntax.pattern.matcher(fun);
			if (!mDef.find()) throw new Exception("Function " + name + " not found");
			String propList = mDef.group(2);
			String block = mDef.group(3);
			
			System.out.println(fun);

			if (propList == null) {
				if (argList != null) throw new Exception("Function " + name + " does not expect arguments");
			} else {
				String[] props = propList.split("[ ]+");
				if (argList == null) throw new Exception("Function " + name + " expects arguments: " + propList);
				String[] args = argList.split(",[ ]*");
				if (props.length != args.length) throw new Exception("Function " + name + " expects " + props.length + " arguments");
				for (int i = 0; i < props.length; i++) {
					String prop = props[i];
					String arg = scope.run(args[i]).toString();
					scope.map.put(prop, new Variable(name, arg));
				}
			}
			
			scope.result = scope.run(block);
			return m;
		} else {
			return null;
		}
	}
	
}

class RectCommandSyntax extends Syntax {
	
	static Pattern pattern = Pattern.compile("^\\s*rect[ ]+(.+)[ ]*,[ ]*(.+)");
	
	RectCommandSyntax(Scope scope) {
		this.scope = scope;
	}

	Matcher evaluate(String code) throws Exception {
		Matcher m = pattern.matcher(code);
		if (m.find()) {
			int width = Integer.parseInt(scope.run(m.group(1)).toString());
			scope.result = null;
			int height = Integer.parseInt(scope.run(m.group(2)).toString());
			scope.result = null;
			Command command = new RectCommand(width, height);
			command.execution();
			return m;
		} else {
			return null;
		}
	}
	
}

class MoveToCommandSyntax extends Syntax {
	
	static Pattern pattern = Pattern.compile("^\\s*move to[ ]+(.+)[ ]*,[ ]*(.+)");
	
	MoveToCommandSyntax(Scope scope) {
		this.scope = scope;
	}

	Matcher evaluate(String code) throws Exception {
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

class DrawToCommandSyntax extends Syntax {
	
	static Pattern pattern = Pattern.compile("^\\s*draw to[ ]+(.+)[ ]*,[ ]*(.+)");
	
	DrawToCommandSyntax(Scope scope) {
		this.scope = scope;
	}

	Matcher evaluate(String code) throws Exception {
		Matcher m = pattern.matcher(code);
		if (m.find()) {
			int x = Integer.parseInt(scope.run(m.group(1)).toString());
			scope.result = null;
			int y = Integer.parseInt(scope.run(m.group(2)).toString());
			scope.result = null;
			Command command = new DrawToCommand(x, y);
			command.execution();
			return m;
		} else {
			return null;
		}
	}
	
}


class CircleCommandSyntax extends Syntax {
	
	static Pattern pattern = Pattern.compile("^\\s*circle[ ]+(.+)");
	
	CircleCommandSyntax(Scope scope) {
		this.scope = scope;
	}

	Matcher evaluate(String code) throws Exception {
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





