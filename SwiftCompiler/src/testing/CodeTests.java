package testing;

import org.junit.Assert;
import org.junit.Test;

public class CodeTests {
	
	String identifier = "[a-zA-Z]([a-zA-Z0-9])*";
	String type = identifier;
	String value = "([0-9])";
	String identifierAndTypeSyntax = identifier + ":( )?" + type;
	String variableDeclarationSyntax = "var " + identifierAndTypeSyntax + "(( )?)=(( )?)" + value;
	String functionParametersSyntax = "\\(" + "(" + identifierAndTypeSyntax + "(" + ", " + identifierAndTypeSyntax + ")*" + ")*" + "\\)";
	String functionDeclarationSyntax = "func " + identifier + "(( )?)" + functionParametersSyntax + "(" + "((( )?)->(( )?))" + type + ")?" + "(( )?)" + "\\{" + "(( |\n)*)" + "\\}";
	
	@Test
	public void validFunctionDeclaration() {
		String funcDecEx = "func f() {}";
		String funcDecEx2 = "func f(){}";
		String funcDecEx3 = "func f(){     }";
		String funcDecEx4 = "func f(){\n\n }";
		String funcDecEx5 = "func f(x: Int){\n\n }";
		String funcDecEx6 = "func f(x: Int, y: Int){\n\n }";
		String funcDecEx7 = "func f(x: Int, y: Int)->Int{\n\n }";
		String funcDecEx8 = "func f(x: Int, y: Int) -> Int {\n\n }";
		Assert.assertTrue(funcDecEx.matches(functionDeclarationSyntax));
		Assert.assertTrue(funcDecEx2.matches(functionDeclarationSyntax));
		Assert.assertTrue(funcDecEx3.matches(functionDeclarationSyntax));
		Assert.assertTrue(funcDecEx4.matches(functionDeclarationSyntax));
		Assert.assertTrue(funcDecEx5.matches(functionDeclarationSyntax));
		Assert.assertTrue(funcDecEx6.matches(functionDeclarationSyntax));
		Assert.assertTrue(funcDecEx7.matches(functionDeclarationSyntax));
		Assert.assertTrue(funcDecEx8.matches(functionDeclarationSyntax));
	}
	
	@Test
	public void validFunctionCall() {
		Assert.assertTrue(false);
	}
	
	@Test
	public void validVariableDeclaration() {
		String varDecEx = "var v: Int = 1";
		String varDecEx2 = "var 9: Int = 1";
		String varDecEx3 = "var V: Int = 1";
		String varDecEx4 = "var V:Int = 1";
		String varDecEx5 = "var V: Int= 1";
		String varDecEx6 = "var V:Int=1";

		Assert.assertTrue(varDecEx.matches(variableDeclarationSyntax));
		Assert.assertFalse(varDecEx2.matches(variableDeclarationSyntax));
		Assert.assertTrue(varDecEx3.matches(variableDeclarationSyntax));
		Assert.assertTrue(varDecEx4.matches(variableDeclarationSyntax));
		Assert.assertTrue(varDecEx5.matches(variableDeclarationSyntax));
		Assert.assertTrue(varDecEx6.matches(variableDeclarationSyntax));
	}
		
	@Test
	public void validVariable() {
		Assert.assertTrue(false);
	}
	
	@Test
	public void validIdentifier() {
		String identifierEx = "v";
		String identifierEx2 = "n";
		String identifierEx3 = "tD7";
		String identifierEx4 = "T";
		Assert.assertTrue(identifierEx.matches(identifier));
		Assert.assertTrue(identifierEx2.matches(identifier));
		Assert.assertTrue(identifierEx3.matches(identifier));
		Assert.assertTrue(identifierEx4.matches(identifier));
	}
	
}
