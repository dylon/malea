// $ANTLR ANTLRVersion> PreprocessorLexer.java generatedTimestamp>

	package malea;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PreprocessorLexer extends AbstractPreprocessorLexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		NestedMetadata=1, NestedTable=2, CategoryTag=3, ReferenceTag=4, PreformattedTag=5, 
		MathTag=6, HtmlTag=7, Emphasis=8, WhiteSpace=9, Ellipsis=10, CapitalizedLetters=11, 
		NonBreakSpace=12, UnorderedList=13, Titleize=14, DoubleOpenSquareBracket=15, 
		DoubleCloseSquareBracket=16, OpenSquareBracket=17, CloseSquareBracket=18, 
		ColonDoubleForwardSlash=19, Pipe=20, AnyOtherChar=21;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"NestedMetadata", "NestedTable", "CategoryTag", "ReferenceTag", "PreformattedTag", 
		"MathTag", "HtmlTag", "Emphasis", "WhiteSpace", "Ellipsis", "CapitalizedLetters", 
		"'&nbsp;'", "UnorderedList", "Titleize", "'[['", "']]'", "'['", "']'", 
		"'://'", "'|'", "AnyOtherChar"
	};
	public static final String[] ruleNames = {
		"NestedMetadata", "NestedTable", "CategoryText", "CategoryTag", "ReferenceTag", 
		"PreformattedTag", "MathTag", "HtmlTag", "Emphasis", "WhiteSpace", "SpaceTabOrFormFeed", 
		"EllipsisFragment", "Ellipsis", "CapitalizedLetters", "NonBreakSpace", 
		"UnorderedList", "Titleize", "DoubleOpenSquareBracket", "DoubleCloseSquareBracket", 
		"OpenSquareBracket", "CloseSquareBracket", "ColonDoubleForwardSlash", 
		"Pipe", "AnyOtherChar"
	};


	public PreprocessorLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "PreprocessorLexer.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 0: NestedMetadata_action((RuleContext)_localctx, actionIndex); break;

		case 1: NestedTable_action((RuleContext)_localctx, actionIndex); break;

		case 3: CategoryTag_action((RuleContext)_localctx, actionIndex); break;

		case 4: ReferenceTag_action((RuleContext)_localctx, actionIndex); break;

		case 5: PreformattedTag_action((RuleContext)_localctx, actionIndex); break;

		case 6: MathTag_action((RuleContext)_localctx, actionIndex); break;

		case 7: HtmlTag_action((RuleContext)_localctx, actionIndex); break;

		case 8: Emphasis_action((RuleContext)_localctx, actionIndex); break;

		case 12: Ellipsis_action((RuleContext)_localctx, actionIndex); break;

		case 13: CapitalizedLetters_action((RuleContext)_localctx, actionIndex); break;

		case 14: NonBreakSpace_action((RuleContext)_localctx, actionIndex); break;

		case 15: UnorderedList_action((RuleContext)_localctx, actionIndex); break;

		case 16: Titleize_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void NonBreakSpace_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2: setText(" "); break;
		}
	}
	private void Emphasis_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 10: skip();  break;
		}
	}
	private void MathTag_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 8: skip();  break;
		}
	}
	private void UnorderedList_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 11: skip();  break;
		}
	}
	private void Titleize_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 12: skip();  break;
		}
	}
	private void CapitalizedLetters_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1: setText(getText().toLowerCase()); break;
		}
	}
	private void ReferenceTag_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 6: skip();  break;
		}
	}
	private void NestedTable_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 4: skip();  break;
		}
	}
	private void PreformattedTag_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 7: skip();  break;
		}
	}
	private void HtmlTag_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 9: skip();  break;
		}
	}
	private void NestedMetadata_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3: skip();  break;
		}
	}
	private void CategoryTag_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 5: skip();  break;
		}
	}
	private void Ellipsis_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: setText("\u2026"); break;
		}
	}
	@Override
	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 15: return UnorderedList_sempred((RuleContext)_localctx, predIndex);

		case 16: return Titleize_sempred((RuleContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean UnorderedList_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return start_of_line();
		}
		return true;
	}
	private boolean Titleize_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1: return start_of_line();

		case 2: return end_of_line();
		}
		return true;
	}

	public static final String _serializedATN =
		"\1\2\25\u0108\6\uffff\2\0\7\0\2\1\7\1\2\2\7\2\2\3\7\3\2\4\7\4\2\5\7\5"+
		"\2\6\7\6\2\7\7\7\2\b\7\b\2\t\7\t\2\n\7\n\2\13\7\13\2\f\7\f\2\r\7\r\2\16"+
		"\7\16\2\17\7\17\2\20\7\20\2\21\7\21\2\22\7\22\2\23\7\23\2\24\7\24\2\25"+
		"\7\25\2\26\7\26\2\27\7\27\1\0\1\0\1\0\1\0\1\0\5\0\67\b\0\n\0\f\0:\t\0"+
		"\1\0\1\0\1\0\1\0\1\0\1\1\1\1\1\1\1\1\1\1\4\1F\b\1\13\1\f\1G\1\1\1\1\1"+
		"\1\1\1\1\1\1\2\4\2P\b\2\13\2\f\2Q\1\3\1\3\1\3\1\3\1\3\1\3\1\3\1\3\1\3"+
		"\1\3\1\3\1\4\1\4\1\4\1\4\1\4\1\4\1\4\5\4f\b\4\n\4\f\4i\t\4\1\4\1\4\1\4"+
		"\1\4\1\4\1\4\1\4\1\4\1\4\1\5\1\5\1\5\1\5\1\5\1\5\5\5z\b\5\n\5\f\5}\t\5"+
		"\1\5\1\5\5\5\u0081\b\5\n\5\f\5\u0084\t\5\1\5\1\5\1\5\1\5\1\5\1\5\1\5\1"+
		"\5\1\5\1\6\1\6\1\6\1\6\1\6\1\6\1\6\1\6\5\6\u0097\b\6\n\6\f\6\u009a\t\6"+
		"\1\6\1\6\1\6\1\6\1\6\1\6\1\6\1\6\1\6\1\6\1\7\1\7\5\7\u00a8\b\7\n\7\f\7"+
		"\u00ab\t\7\1\7\1\7\1\7\1\7\1\b\1\b\4\b\u00b3\b\b\13\b\f\b\u00b4\1\b\1"+
		"\b\1\t\1\t\1\n\1\n\1\13\1\13\5\13\u00bf\b\13\n\13\f\13\u00c2\t\13\1\f"+
		"\1\f\4\f\u00c6\b\f\13\f\f\f\u00c7\1\f\1\f\1\r\4\r\u00cd\b\r\13\r\f\r\u00ce"+
		"\1\r\1\r\1\16\1\16\1\16\1\16\1\16\1\16\1\16\1\16\1\16\1\17\1\17\1\17\4"+
		"\17\u00df\b\17\13\17\f\17\u00e0\3\17\u00e3\b\17\1\17\1\17\1\20\1\20\4"+
		"\20\u00e9\b\20\13\20\f\20\u00ea\1\20\4\20\u00ee\b\20\13\20\f\20\u00ef"+
		"\1\20\3\20\u00f3\b\20\1\20\1\20\1\21\1\21\1\21\1\22\1\22\1\22\1\23\1\23"+
		"\1\24\1\24\1\25\1\25\1\25\1\25\1\26\1\26\1\27\1\27\78Gg{\u0082\u0098\u00a9"+
		"\30\1\1\3\3\2\4\5\0\uffff\7\3\5\t\4\6\13\5\7\r\6\b\17\7\t\21\b\n\23\t"+
		"\uffff\25\0\uffff\27\0\uffff\31\n\0\33\13\1\35\f\2\37\r\13!\16\f#\17\uffff"+
		"%\20\uffff\'\21\uffff)\22\uffff+\23\uffff-\24\uffff/\25\uffff\1\0\5\4"+
		"::[[]]||\2\t\r  \3\t\t\13\f  \1AZ\2##**\u0117\0\1\1\0\0\0\0\3\1\0\0\0"+
		"\0\7\1\0\0\0\0\t\1\0\0\0\0\13\1\0\0\0\0\r\1\0\0\0\0\17\1\0\0\0\0\21\1"+
		"\0\0\0\0\23\1\0\0\0\0\31\1\0\0\0\0\33\1\0\0\0\0\35\1\0\0\0\0\37\1\0\0"+
		"\0\0!\1\0\0\0\0#\1\0\0\0\0%\1\0\0\0\0\'\1\0\0\0\0)\1\0\0\0\0+\1\0\0\0"+
		"\0-\1\0\0\0\0/\1\0\0\0\1\61\1\0\0\0\3@\1\0\0\0\5O\1\0\0\0\7S\1\0\0\0\t"+
		"^\1\0\0\0\13s\1\0\0\0\r\u008e\1\0\0\0\17\u00a5\1\0\0\0\21\u00b0\1\0\0"+
		"\0\23\u00b8\1\0\0\0\25\u00ba\1\0\0\0\27\u00bc\1\0\0\0\31\u00c3\1\0\0\0"+
		"\33\u00cc\1\0\0\0\35\u00d2\1\0\0\0\37\u00db\1\0\0\0!\u00f2\1\0\0\0#\u00f6"+
		"\1\0\0\0%\u00f9\1\0\0\0\'\u00fc\1\0\0\0)\u00fe\1\0\0\0+\u0100\1\0\0\0"+
		"-\u0104\1\0\0\0/\u0106\1\0\0\0\61\62\5{\0\0\62\63\5{\0\0\638\1\0\0\0\64"+
		"\67\3\1\0\0\65\67\t\0\0\0\66\64\1\0\0\0\66\65\1\0\0\0\67:\1\0\0\089\1"+
		"\0\0\08\66\1\0\0\09;\1\0\0\0:8\1\0\0\0;<\5}\0\0<=\5}\0\0=>\1\0\0\0>?\6"+
		"\0\3\0?\2\1\0\0\0@A\5{\0\0AB\5|\0\0BE\1\0\0\0CF\3\3\1\0DF\t\0\0\0EC\1"+
		"\0\0\0ED\1\0\0\0FG\1\0\0\0GH\1\0\0\0GE\1\0\0\0HI\1\0\0\0IJ\5|\0\0JK\5"+
		"}\0\0KL\1\0\0\0LM\6\1\4\0M\4\1\0\0\0NP\b\0\0\0ON\1\0\0\0PQ\1\0\0\0QO\1"+
		"\0\0\0QR\1\0\0\0R\6\1\0\0\0ST\5[\0\0TU\5[\0\0UV\1\0\0\0VW\3\5\2\0WX\5"+
		":\0\0XY\3\5\2\0YZ\5]\0\0Z[\5]\0\0[\\\1\0\0\0\\]\6\3\5\0]\b\1\0\0\0^_\5"+
		"<\0\0_`\5r\0\0`a\5e\0\0ab\5f\0\0bc\5>\0\0cg\1\0\0\0df\t\0\0\0ed\1\0\0"+
		"\0fi\1\0\0\0gh\1\0\0\0ge\1\0\0\0hj\1\0\0\0ig\1\0\0\0jk\5<\0\0kl\5/\0\0"+
		"lm\5r\0\0mn\5e\0\0no\5f\0\0op\5>\0\0pq\1\0\0\0qr\6\4\6\0r\n\1\0\0\0st"+
		"\5<\0\0tu\5p\0\0uv\5r\0\0vw\5e\0\0w{\1\0\0\0xz\t\0\0\0yx\1\0\0\0z}\1\0"+
		"\0\0{|\1\0\0\0{y\1\0\0\0|~\1\0\0\0}{\1\0\0\0~\u0082\5>\0\0\177\u0081\t"+
		"\0\0\0\u0080\177\1\0\0\0\u0081\u0084\1\0\0\0\u0082\u0083\1\0\0\0\u0082"+
		"\u0080\1\0\0\0\u0083\u0085\1\0\0\0\u0084\u0082\1\0\0\0\u0085\u0086\5<"+
		"\0\0\u0086\u0087\5/\0\0\u0087\u0088\5p\0\0\u0088\u0089\5r\0\0\u0089\u008a"+
		"\5e\0\0\u008a\u008b\5>\0\0\u008b\u008c\1\0\0\0\u008c\u008d\6\5\7\0\u008d"+
		"\f\1\0\0\0\u008e\u008f\5<\0\0\u008f\u0090\5m\0\0\u0090\u0091\5a\0\0\u0091"+
		"\u0092\5t\0\0\u0092\u0093\5h\0\0\u0093\u0094\5>\0\0\u0094\u0098\1\0\0"+
		"\0\u0095\u0097\t\0\0\0\u0096\u0095\1\0\0\0\u0097\u009a\1\0\0\0\u0098\u0099"+
		"\1\0\0\0\u0098\u0096\1\0\0\0\u0099\u009b\1\0\0\0\u009a\u0098\1\0\0\0\u009b"+
		"\u009c\5<\0\0\u009c\u009d\5/\0\0\u009d\u009e\5m\0\0\u009e\u009f\5a\0\0"+
		"\u009f\u00a0\5t\0\0\u00a0\u00a1\5h\0\0\u00a1\u00a2\5>\0\0\u00a2\u00a3"+
		"\1\0\0\0\u00a3\u00a4\6\6\b\0\u00a4\16\1\0\0\0\u00a5\u00a9\5<\0\0\u00a6"+
		"\u00a8\t\0\0\0\u00a7\u00a6\1\0\0\0\u00a8\u00ab\1\0\0\0\u00a9\u00aa\1\0"+
		"\0\0\u00a9\u00a7\1\0\0\0\u00aa\u00ac\1\0\0\0\u00ab\u00a9\1\0\0\0\u00ac"+
		"\u00ad\5>\0\0\u00ad\u00ae\1\0\0\0\u00ae\u00af\6\7\t\0\u00af\20\1\0\0\0"+
		"\u00b0\u00b2\5\'\0\0\u00b1\u00b3\5\'\0\0\u00b2\u00b1\1\0\0\0\u00b3\u00b4"+
		"\1\0\0\0\u00b4\u00b2\1\0\0\0\u00b4\u00b5\1\0\0\0\u00b5\u00b6\1\0\0\0\u00b6"+
		"\u00b7\6\b\n\0\u00b7\22\1\0\0\0\u00b8\u00b9\7\1\0\0\u00b9\24\1\0\0\0\u00ba"+
		"\u00bb\7\2\0\0\u00bb\26\1\0\0\0\u00bc\u00c0\5.\0\0\u00bd\u00bf\3\25\n"+
		"\0\u00be\u00bd\1\0\0\0\u00bf\u00c2\1\0\0\0\u00c0\u00be\1\0\0\0\u00c0\u00c1"+
		"\1\0\0\0\u00c1\30\1\0\0\0\u00c2\u00c0\1\0\0\0\u00c3\u00c5\3\27\13\0\u00c4"+
		"\u00c6\3\27\13\0\u00c5\u00c4\1\0\0\0\u00c6\u00c7\1\0\0\0\u00c7\u00c5\1"+
		"\0\0\0\u00c7\u00c8\1\0\0\0\u00c8\u00c9\1\0\0\0\u00c9\u00ca\6\f\0\0\u00ca"+
		"\32\1\0\0\0\u00cb\u00cd\7\3\0\0\u00cc\u00cb\1\0\0\0\u00cd\u00ce\1\0\0"+
		"\0\u00ce\u00cc\1\0\0\0\u00ce\u00cf\1\0\0\0\u00cf\u00d0\1\0\0\0\u00d0\u00d1"+
		"\6\r\1\0\u00d1\34\1\0\0\0\u00d2\u00d3\5&\0\0\u00d3\u00d4\5n\0\0\u00d4"+
		"\u00d5\5b\0\0\u00d5\u00d6\5s\0\0\u00d6\u00d7\5p\0\0\u00d7\u00d8\5;\0\0"+
		"\u00d8\u00d9\1\0\0\0\u00d9\u00da\6\16\2\0\u00da\36\1\0\0\0\u00db\u00e2"+
		"\4\17\0\0\u00dc\u00e3\7\4\0\0\u00dd\u00df\5:\0\0\u00de\u00dd\1\0\0\0\u00df"+
		"\u00e0\1\0\0\0\u00e0\u00de\1\0\0\0\u00e0\u00e1\1\0\0\0\u00e1\u00e3\1\0"+
		"\0\0\u00e2\u00dc\1\0\0\0\u00e2\u00de\1\0\0\0\u00e3\u00e4\1\0\0\0\u00e4"+
		"\u00e5\6\17\13\0\u00e5 \1\0\0\0\u00e6\u00e8\4\20\1\0\u00e7\u00e9\5=\0"+
		"\0\u00e8\u00e7\1\0\0\0\u00e9\u00ea\1\0\0\0\u00ea\u00e8\1\0\0\0\u00ea\u00eb"+
		"\1\0\0\0\u00eb\u00f3\1\0\0\0\u00ec\u00ee\5=\0\0\u00ed\u00ec\1\0\0\0\u00ee"+
		"\u00ef\1\0\0\0\u00ef\u00ed\1\0\0\0\u00ef\u00f0\1\0\0\0\u00f0\u00f1\1\0"+
		"\0\0\u00f1\u00f3\4\20\2\0\u00f2\u00e6\1\0\0\0\u00f2\u00ed\1\0\0\0\u00f3"+
		"\u00f4\1\0\0\0\u00f4\u00f5\6\20\f\0\u00f5\"\1\0\0\0\u00f6\u00f7\5[\0\0"+
		"\u00f7\u00f8\5[\0\0\u00f8$\1\0\0\0\u00f9\u00fa\5]\0\0\u00fa\u00fb\5]\0"+
		"\0\u00fb&\1\0\0\0\u00fc\u00fd\5[\0\0\u00fd(\1\0\0\0\u00fe\u00ff\5]\0\0"+
		"\u00ff*\1\0\0\0\u0100\u0101\5:\0\0\u0101\u0102\5/\0\0\u0102\u0103\5/\0"+
		"\0\u0103,\1\0\0\0\u0104\u0105\5|\0\0\u0105.\1\0\0\0\u0106\u0107\t\0\0"+
		"\0\u0107\60\1\0\0\0\24\0\668EGQg{\u0082\u0098\u00a9\u00b4\u00c0\u00c7"+
		"\u00ce\u00e0\u00e2\u00ea\u00ef\u00f2";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
	    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}