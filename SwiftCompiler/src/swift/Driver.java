package swift;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Driver {

	static Pattern p = Pattern.compile(SyntaxDefinitions.additionSyntax);
		
	static String[] tokenizeStatement(String statement) {
		return statement.split(SyntaxDefinitions.additionSyntax);
	}
	
	void patternStuff(String statement) {
		Matcher m = p.matcher(statement);
		
		if (m.find()) {
			System.out.println(m.group(0));
		}
	}
			
	public static void main(String[] args) {
//		try {
//			String statement = "var x = 7";
//			System.out.println("Statement: " + statement);
//			CodeAction.variableDeclaration(statement);
//			//System.out.println("Result: " + CodeAction.variableDeclaration(statement));
//		} catch(Exception e) {
//			System.out.println("Invalid line");
//		}
//		
//		String code = "var x = 7\nvar y = 14";
//		CodeAction.runCode(code);
	}

}
