package parser;

import java.util.ArrayList;

import block.Block;
import block.Method;
import swift.Parameter;
import swift.Type;
import tokenizer.Token;
import tokenizer.Tokenizer;

public class MethodParser extends Parser<Method> {

	@Override
	public boolean shouldParse(String line) {
		return line.matches("method [a-zA-Z][a-zA-Z0-9]* requires \\(([a-zA-Z][a-zA-Z0-9]* [a-zA-Z][a-zA-Z0-9]*)*\\) -> [a-zA-Z][a-zA-Z0-9]*");
	}

	@Override
	public Method parse(Block superBlock, Tokenizer tokenizer) {
		tokenizer.nextToken();
		String name = tokenizer.nextToken().getToken();
		tokenizer.nextToken();
		tokenizer.nextToken();
		Token first = tokenizer.nextToken();
		ArrayList<Parameter> params = new ArrayList<>();
		
		if (!first.getToken().equals(")")) {
			String[] paramData = new String[] { first.getToken(), null };
			while (tokenizer.hasNextToken()) {
				Token token = tokenizer.nextToken();
				if (token.getToken().equals(")")) {
					break;
				}
				if (paramData[0] == null) {
					paramData[0] = token.getToken();
				} else {
					paramData[1] = token.getToken();
					params.add(new Parameter(Type.valueOf(paramData[0].toUpperCase()), paramData[1]));
					paramData = new String[2];
				}
			}
		}
		tokenizer.nextToken();
		Type returnType = Type.valueOf(tokenizer.nextToken().getToken().toUpperCase());
		return new Method(superBlock, name, returnType, params.toArray(new Parameter[params.size()]));
	}

}
