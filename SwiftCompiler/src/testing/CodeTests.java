package testing;

import org.junit.Assert;
import org.junit.Test;

import structures.Variable;
import swift.Scope;

public class CodeTests {
	
	@Test
	public void additionTest() {
		Scope scope = new Scope();

		try {
		Assert.assertTrue(scope.addition("3 + 7") == 10);
		Assert.assertTrue(scope.addition("5+ 9") == 14);
		Assert.assertTrue(scope.addition("1 +8") == 9);
		Assert.assertTrue(scope.addition("6+4") == 10);
		} catch (Exception e) {
			Assert.fail();
		}
		
		try {
			scope.addition("6+  4");
			scope.addition("6    +  4");
			Assert.fail();
		} catch (Exception e) {
		}
	}
		
	@Test
	public void variableTest() {
		Scope scope = new Scope();

		String statement = "var x = 7";
		try {
			Variable v = scope.variableDeclaration(statement);
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
			int r = (int) scope.run(statement);
			Assert.assertTrue(r == 7);
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	@Test
	public void variableReassignmentTest() {
		Scope scope = new Scope();

		String statement = "var x = 5 \n x = 10";
		try {
			int r = (int) scope.run(statement);
			Assert.assertTrue(r == 10);
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	@Test
	public void getVariableTest4() {
		Scope scope = new Scope();
		String statement = "var x = 0 \n x = x + 1";
		try {
			int r = (int) scope.run(statement);
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
			int r = (int) scope.run(statement);
			Assert.assertTrue(r == 2);
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	@Test
	public void getVariableTest6() {
		Scope scope = new Scope();
		try {
			scope.run("var i = 2");
			scope.run("var j = 3");
			String statement = "i < j";
			boolean r = (boolean) scope.run(statement);
			Assert.assertTrue(r);
		} catch (Exception e) {
			Assert.fail();
		}
	}
		
	@Test
	public void getVariableTest3() {
		Scope scope = new Scope();
		String statement = "var i = 0 \n while i < 3 { i = i + 1 }";
		try {
			int r = (int) scope.run(statement);
			System.out.println(r);
			Assert.assertTrue(r == 3);
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
}
