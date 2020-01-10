package testing;

import org.junit.Assert;
import org.junit.Test;

import swift.SyntaxDefinitions;

public class CodeTests {
		
	@Test
	public void literalTest() {
		String intEx = "5";
		String intEx2 = "0";
		String intEx3 = "50";
		String intEx4 = "00";
		Assert.assertTrue(intEx.matches(SyntaxDefinitions.literal));
		Assert.assertTrue(intEx2.matches(SyntaxDefinitions.literal));
		Assert.assertTrue(intEx3.matches(SyntaxDefinitions.literal));
		Assert.assertFalse(intEx4.matches(SyntaxDefinitions.literal));
	}
	
	@Test
	public void integerTest() {
		String intEx = "5";
		String intEx2 = "0";
		String intEx3 = "50";
		String intEx4 = "00";
		Assert.assertTrue(intEx.matches(SyntaxDefinitions.integer));
		Assert.assertTrue(intEx2.matches(SyntaxDefinitions.integer));
		Assert.assertTrue(intEx3.matches(SyntaxDefinitions.integer));
		Assert.assertFalse(intEx4.matches(SyntaxDefinitions.integer));
	}
	
	@Test
	public void additionSymbolTest() {
		Assert.assertTrue("+".matches(SyntaxDefinitions.additionSymbol));
	}
	
	@Test
	public void additionTest() {
		String additionEx = "5 + 5";
		Assert.assertTrue(additionEx.matches(SyntaxDefinitions.additionSyntax));
	}
	
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
		Assert.assertTrue(funcDecEx.matches(SyntaxDefinitions.functionDeclarationSyntax));
		Assert.assertTrue(funcDecEx2.matches(SyntaxDefinitions.functionDeclarationSyntax));
		Assert.assertTrue(funcDecEx3.matches(SyntaxDefinitions.functionDeclarationSyntax));
		Assert.assertTrue(funcDecEx4.matches(SyntaxDefinitions.functionDeclarationSyntax));
		Assert.assertTrue(funcDecEx5.matches(SyntaxDefinitions.functionDeclarationSyntax));
		Assert.assertTrue(funcDecEx6.matches(SyntaxDefinitions.functionDeclarationSyntax));
		Assert.assertTrue(funcDecEx7.matches(SyntaxDefinitions.functionDeclarationSyntax));
		Assert.assertTrue(funcDecEx8.matches(SyntaxDefinitions.functionDeclarationSyntax));
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

		Assert.assertTrue(varDecEx.matches(SyntaxDefinitions.variableDeclarationSyntax));
		Assert.assertFalse(varDecEx2.matches(SyntaxDefinitions.variableDeclarationSyntax));
		Assert.assertTrue(varDecEx3.matches(SyntaxDefinitions.variableDeclarationSyntax));
		Assert.assertTrue(varDecEx4.matches(SyntaxDefinitions.variableDeclarationSyntax));
		Assert.assertTrue(varDecEx5.matches(SyntaxDefinitions.variableDeclarationSyntax));
		Assert.assertTrue(varDecEx6.matches(SyntaxDefinitions.variableDeclarationSyntax));
	}
		
	@Test
	public void validIdentifier() {
		String identifierEx = "v";
		String identifierEx2 = "n";
		String identifierEx3 = "tD7";
		String identifierEx4 = "T";
		Assert.assertTrue(identifierEx.matches(SyntaxDefinitions.identifier));
		Assert.assertTrue(identifierEx2.matches(SyntaxDefinitions.identifier));
		Assert.assertTrue(identifierEx3.matches(SyntaxDefinitions.identifier));
		Assert.assertTrue(identifierEx4.matches(SyntaxDefinitions.identifier));
	}
	
}
