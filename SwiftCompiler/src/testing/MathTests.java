package testing;


import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import swift.MathMethods;

public class MathTests {

	@Test
	public void addition() {
		ArrayList<Integer> ints = new ArrayList<>();
		ints.add(2);
		ints.add(2);
		ints.add(2);
		ints.add(2);
		Assert.assertTrue(MathMethods.reduce(ints) == 8);
	}

}
