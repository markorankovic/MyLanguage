package swift;

import java.util.ArrayList;

public class MathMethods {
	
	public static int reduce(ArrayList<Integer> integers) {
		int result = 0;
		for (Integer n : integers) {
			result += n;
		}
		return result;
	}
	
}
