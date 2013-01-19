package malea;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;

public abstract class AbstractPreprocessorLexer extends Lexer {
	protected AbstractPreprocessorLexer(CharStream input) {
		super(input);
	}

	protected boolean start_of_line() {
		return 0 == getCharPositionInLine();
	}

	protected boolean end_of_line() {
		final char lookahead = (char) _input.LA(1);
		return (lookahead == '\n') || (lookahead == '\r');
	}
}
