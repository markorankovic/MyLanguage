package swift;

public class SyntaxDefinitions {
	
	public static String identifier = "([a-zA-Z]([a-zA-Z0-9])*)";
	public static String type = identifier;
	public static String integer = "(([1-9]([0-9]*))|0)";
	public static String literal = "(" + integer + "|" + identifier + ")";
	
	public static String additionSymbol = "\\+";
	public static String additionSyntax = literal + "( ?)" + additionSymbol + "( ?)" + literal;
	
	public static String identifierAndTypeSyntax = identifier + ":( )?" + type;
	public static String variableDeclarationSyntax = "var " + "(" + identifierAndTypeSyntax + "|" + identifier + ")" + "(( )?)=(( )?)" + integer;
	public static String variableReassignmentSyntax = identifier + "( ?)" + "=" + "( ?)" + literal;
	
	public static String functionParametersSyntax = "\\(" + "(" + identifierAndTypeSyntax + "(" + ", " + identifierAndTypeSyntax + ")*" + ")*" + "\\)";
	public static String functionDeclarationSyntax = "func " + identifier + "(( )?)" + functionParametersSyntax + "(" + "((( )?)->(( )?))" + type + ")?" + "(( )?)" + "\\{" + "(( |\n)*)" + "\\}";
	
}
