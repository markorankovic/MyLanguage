package syntax;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import structures.Variable;
import swift.Scope;

public class FunctionCallSyntax extends Syntax {
	
	public static Pattern pattern = Pattern.compile("^\\s*do[ ]+(\\w+)(?:[ ]+(.+))?");
	
	public FunctionCallSyntax(Scope scope) {
		this.scope = scope;
	}

	public Matcher evaluate(String code) throws Exception {
		Matcher m = pattern.matcher(code);
		if (m.find()) {
			String name = m.group(1);
			String argList = m.group(2);

			String fun = scope.getVariable(name).getValue().toString();

			Matcher mDef = FunctionDefinitionSyntax.pattern.matcher(fun);
			if (!mDef.find()) throw new Exception("Function " + name + " not found");
			String propList = mDef.group(2);
			String block = mDef.group(3);

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