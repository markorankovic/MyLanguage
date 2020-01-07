package parser;

import block.Block;
import swift.Type;
import swift.Variable;
import tokenizer.Token;
import tokenizer.TokenType;
import tokenizer.Tokenizer;

public class VariableParser extends Parser<Block> {

	@Override
	public boolean shouldParse(String line) {
		// TODO Auto-generated method stub
		return line.matches("var [a-zA-Z]+ [a-zA-Z0-9]+ = \"?[a-zA-Z0-9]\"?");
	}

	@Override
	public Block parse(Block superBlock, Tokenizer tokenizer) {
		tokenizer.nextToken();
		
		Type type = Type.valueOf(tokenizer.nextToken().getToken().toUpperCase());
		
		if (type == Type.VOID) {
			throw new IllegalStateException();
		}
		
		String name = tokenizer.nextToken().getToken();
		
		tokenizer.nextToken();
		
		Token v = tokenizer.nextToken();
		Object value;
		
		if (v.getType() == TokenType.INTEGER_LITERAL) {
			value = Integer.valueOf(v.getToken());
		}
		else if (v.getType() == TokenType.STRING_LITERAL) {
			value = v.getToken();
		}
		else {
			value = superBlock.getVariable(v.getToken()).getValue();
		}
		
		superBlock.addVariable(new Variable(superBlock, type, name, value));
		return null;
	}

}
