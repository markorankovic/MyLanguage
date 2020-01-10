package testing;

import org.junit.Assert;
import org.junit.Test;

public class CodeTests {
	
	String identifier = "[a-zA-Z]([a-zA-Z0-9])*";
	String type = identifier;
	String value = "([0-9])";
	String variableDeclarationSyntax = "var " + identifier + ": " + type + " = " + value;
	
	@Test
	public void validFunctionDeclaration() {
		Assert.assertTrue(false);
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

		Assert.assertTrue(varDecEx.matches(variableDeclarationSyntax));
		Assert.assertFalse(varDecEx2.matches(variableDeclarationSyntax));
		Assert.assertTrue(varDecEx3.matches(variableDeclarationSyntax));
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
