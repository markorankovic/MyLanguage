package testing;

import org.junit.Assert;
import org.junit.Test;
import swift.Scope;

public class CodeTests {
	
	@Test
	public void trimTest() {
		String str = "  d d  ";
		Assert.assertEquals(str.trim(), "d d");
	}
	
	@Test
	public void additionTest() {
		Scope scope = new Scope();

		try {
			Assert.assertEquals(scope.run("3 + 7"), 10);
			Assert.assertEquals(scope.run("5 + 9"), 14);
			Assert.assertEquals(scope.run("1 +   8"), 9);
			Assert.assertEquals(scope.run("6 + 4"), 10);
			Assert.assertEquals(scope.run("6 +  4"), 10);
			Assert.assertEquals(scope.run("6    +  4"), 10);

		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
		
	@Test
	public void additionTest2() throws Exception {
		Scope scope = new Scope();
		int r = (int) scope.run("7 + 20");
		Assert.assertEquals(r, 27);
	}
	
	@Test
	public void variableTest2() throws Exception {
		Scope scope = new Scope();
		scope.run("x = 7 + 20");
		Assert.assertEquals(scope.getVariable("x").getValue(), 27);
	}
	
	@Test
	public void getVariableTest() {
		Scope scope = new Scope();
		String statement = "var x = 7 \n x";
		try {
			scope.run(statement);
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	@Test
	public void getVariableTest2() {
		Scope scope = new Scope();
		String statement = "var x = 3 \n x = 7 \n x";
		try {
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
	public void variableMutation() {
		Scope scope = new Scope();
		try {
			scope.run("x = 3 \n x = x + 1");
			Assert.assertEquals(scope.getVariable("x").getValue(), 4);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void getVariableTest7() {
		Scope scope = new Scope();
		String statement = "var r = 1 + 1";
		try {
			int r = (int) scope.run(statement);
			Assert.assertTrue(r == 2);
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
	public void rectTest() {
		Scope scope = new Scope();
		String statement = "rect 100, 100";
		try {
			Assert.assertTrue(scope.run(statement) != null);
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	@Test
	public void circleTest() {
		Scope scope = new Scope();
		String statement = "circle 100";
		try {
			Assert.assertTrue(scope.run(statement) != null);
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	@Test
	public void clearTest() {
		Scope scope = new Scope();
		String statement = "clear";
		try {
			Assert.assertTrue(scope.run(statement) != null);
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
			Assert.assertTrue(r == 3);
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	@Test
	public void multiline_while_block() {
		Scope scope = new Scope();
		String statement = "var i = 0 \n while i < 3 {\n  i = i + 1\n}";
		try {
			scope.run(statement);
			Assert.assertEquals(scope.getVariable("i").getValue(), 3);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
}
