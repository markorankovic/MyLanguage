import tokenizer.Tokenizer;

public class Main {

	public static void main(String[] args) {
		String program = "func MyFunc ()";
		Tokenizer tokenizer = new Tokenizer(program);
		
		while (tokenizer.hasNextToken()) {
			System.out.println(tokenizer.nextToken().getToken());
		}
	}
	
}
