package swift;

public class SyntaxDefinitions {
	
	public static String identifier = "([a-zA-Z]([a-zA-Z0-9])*)";
	public static String type = identifier;
	public static String integer = "(([1-9]([0-9]*))|0)";
	public static String literal = "(" + integer + "|" + identifier + ")";
	
	public static String additionSymbol = "\\+";
	public static String additionSyntax = literal + "( ?)" + additionSymbol + "( ?)" + literal;
	public static String operAnd = "(" + literal + "|" + additionSyntax + ")";
	
	public static String lessThanSymbol = "\\<";
	public static String greaterThanSymbol = "\\>";
	public static String lessThan = literal + "( ?)" + lessThanSymbol + "( ?)" + literal;
	public static String greaterThan = literal + "( ?)" + greaterThanSymbol + "( ?)" + literal;
	public static String comparison = literal + "( ?)" + "(" + lessThan + "|" + greaterThan + ")" + "( ?)" + literal;
	
	public static String identifierAndTypeSyntax = identifier + ":( )?" + type;
	public static String variableDeclarationSyntax = "var " + "(" + identifierAndTypeSyntax + "|" + identifier + ")" + "(( )?)=(( )?)" + integer;
	public static String variableReassignmentSyntax = identifier + "( ?)" + "=" + "( ?)" + operAnd;
	
	public static String functionParametersSyntax = "\\(" + "(" + identifierAndTypeSyntax + "(" + ", " + identifierAndTypeSyntax + ")*" + ")*" + "\\)";
	public static String functionDeclarationSyntax = "func " + identifier + "(( )?)" + functionParametersSyntax + "(" + "((( )?)->(( )?))" + type + ")?" + "(( )?)" + "\\{" + "(( |\n)*)" + "\\}";
	
	public static String statementSyntax = "(" + variableDeclarationSyntax + "|" + variableReassignmentSyntax + ")";
	public static String whileLoopSyntax = "while\\s+(.+)\\s+\\{\\s*(.+)\\s*\\}";
}
