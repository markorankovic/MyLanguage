package testing;

import org.junit.Assert;
import org.junit.Test;

import structures.Variable;
import swift.Scope;

public class CodeTests {

	Scope globalScope = new Scope();
	
	@Test
	public void additionTest() {
		try {
		Assert.assertTrue(globalScope.addition("3 + 7") == 10);
		Assert.assertTrue(globalScope.addition("5+ 9") == 14);
		Assert.assertTrue(globalScope.addition("1 +8") == 9);
		Assert.assertTrue(globalScope.addition("6+4") == 10);
		} catch (Exception e) {
			Assert.fail();
		}
		
		try {
			globalScope.addition("6+  4");
			globalScope.addition("6    +  4");
			Assert.fail();
		} catch (Exception e) {
		}
	}
	
	@Test
	public void booleanTest() {
		Assert.fail();
	}
	
	@Test
	public void variableTest() {
		String statement = "var x = 7";
		try {
			Variable v = globalScope.variableDeclaration(statement);
			Assert.assertTrue(v.getValue().equals("7"));
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	@Test
	public void getVariableTest() {
		Scope scope = new Scope();
		String statement = "var x = 7 \n x";
		try {
			scope.run(statement);
			Assert.assertTrue(Integer.parseInt(scope.getVariable("x").getValue()) == 7);
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	@Test
	public void getVariableTest2() {
		Scope scope = new Scope();
		String statement = "var x = 3 \n x = 7 \n x";
		try {
//			r = scope.run(statement);
//			r == 7
			int r = scope.run(statement);
			Assert.assertTrue(r == 7);
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	@Test
	public void variableReassignmentTest() {
		String statement = "var x = 5";
		Variable v;
		try {
			v = globalScope.variableDeclaration(statement);
		} catch (Exception e) {
		}
		
		statement = "x = 10";
		try {
			v = globalScope.variableReassignment(statement);
			Assert.assertTrue(v.getValue().equals("10"));
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	@Test
	public void getVariableTest4() {
		Scope scope = new Scope();
		String statement = "var x = 0 \n x = x + 1";
		try {
			int r = scope.run(statement);
			Assert.assertTrue(r == 1);
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	@Test
	public void getVariableTest5() {
		Scope scope = new Scope();
		String statement = "var x = 0 \n x = x + 1 \n x = x + 1";
		try {
			int r = scope.run(statement);
			Assert.assertTrue(r == 2);
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	@Test
	public void getVariableTest3() {
		Scope scope = new Scope();
		String statement = "var i = 0 \n while i < 3 { i = i + 1 }";
		try {
			int r = scope.run(statement);
			Assert.assertTrue(r == 3);
			Assert.assertTrue(Integer.parseInt(scope.getVariable("x").getValue()) == 3);
		} catch (Exception e) {
			Assert.fail();
		}
	}

}
