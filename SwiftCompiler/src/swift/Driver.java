package swift;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Driver {

	static Pattern p = Pattern.compile(SyntaxDefinitions.additionSyntax);
		
	static String[] tokenizeStatement(String statement) {
		return statement.split(SyntaxDefinitions.additionSyntax);
	}
	
	public static void main(String[] args) {
		String statement = "5+5";
		System.out.println("Statement: " + statement);
		
		Matcher m = p.matcher(statement);
		
		if (m.find()) {
			System.out.println(m.group(0));
		}
	}

}
