package testing;

import org.junit.Assert;
import org.junit.Test;

import swift.Scope;

public class Tests {

	/*
	 * Tests the usage of Math operators.
	 */
	
	@Test
	public void operatorTest() throws Exception {
		Scope scope = new Scope();
		
		String code = "x = 10 \n 20 + x";
		Assert.assertEquals(scope.run(code).toString(), "30");
		
		code = "x = 50 \n 80 + x";
		Assert.assertEquals(scope.run(code).toString(), "130");
		
		code = "x = 80 \n 78 + x";
		Assert.assertEquals(scope.run(code).toString(), "158");

		code = "x = 43 \n 80 / 2";
		Assert.assertEquals(scope.run(code).toString(), "40");

		code = "x = 65 \n 76 * x";
		Assert.assertEquals(scope.run(code).toString(), "4940");

		code = "x = 435 \n 5 + x";
		Assert.assertEquals(scope.run(code).toString(), "440");

		code = "x = 675 \n 43 + x";
		Assert.assertEquals(scope.run(code).toString(), "718");

		code = "x = 78 \n 34 - x";
		Assert.assertEquals(scope.run(code).toString(), "-44");
	}
	
	/*
	 * Tests the declaration and call of functions.
	 */
	
	@Test
	public void functionTest() throws Exception {
		Scope scope = new Scope();
		
		String code = "y = 10 \n fun f x { y = y + x } \n do f 13 \n y";
		Assert.assertEquals(scope.run(code).toString(), "23");
		
		code = "y = 7 \n fun f x { y = y * x } \n do f 8 \n y";
		Assert.assertEquals(scope.run(code).toString(), "56");
	}
	
	/*
	 * Tests the while loop.
	 */
	
	@Test
	public void whileLoopTest() throws Exception {
		Scope scope = new Scope();
		String code = "i = 1 \n while i < 80 { i = i + 1 } \n i";
		Assert.assertEquals(scope.run(code).toString(), "80");
	}

}
