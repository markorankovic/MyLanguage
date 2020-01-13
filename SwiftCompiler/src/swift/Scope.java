package swift;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import structures.Variable;
import syntax.CircleCommandSyntax;
import syntax.DrawToCommandSyntax;
import syntax.FunctionCallSyntax;
import syntax.FunctionDefinitionSyntax;
import syntax.IfBlockSyntax;
import syntax.LiteralValueSyntax;
import syntax.MoveToCommandSyntax;
import syntax.OperatorSyntax;
import syntax.RectCommandSyntax;
import syntax.Syntax;
import syntax.VariableDeclarationSyntax;
import syntax.VariableValueSyntax;
import syntax.WhileBlockSyntax;

public class Scope {

	Syntax[] syntax = {
		new FunctionDefinitionSyntax(this),
		new FunctionCallSyntax(this),
		new CircleCommandSyntax(this),
		new RectCommandSyntax(this),
		new MoveToCommandSyntax(this),
		new DrawToCommandSyntax(this),
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
	
	
	/* Attempts to run any input code. If the code consists of multiple executable code, 
	 * this function calls itself such that all the code recursively is run.
	 * 
	 * @return Returns an Object as the evaluation of the code is ambiguous.
	 * 
	 * @param code	The code to be run.
	 */
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
	
	/* Gets a Variable given the corresponding name.
	 * 
	 * @return Returns a Variable.
	 * 
	 * @param name	Name of the variable.
	 */
	public Variable getVariable(String name) {
		return map.get(name);
	}
}
