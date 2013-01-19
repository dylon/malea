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
		HtmlTag=6, Emphasis=7, WhiteSpace=8, Ellipsis=9, CapitalizedLetters=10, 
		NonBreakSpace=11, UnorderedList=12, Titleize=13, DoubleOpenSquareBracket=14, 
		DoubleCloseSquareBracket=15, OpenSquareBracket=16, CloseSquareBracket=17, 
		ColonDoubleForwardSlash=18, Pipe=19, AnyOtherChar=20;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"NestedMetadata", "NestedTable", "CategoryTag", "ReferenceTag", "PreformattedTag", 
		"HtmlTag", "Emphasis", "WhiteSpace", "Ellipsis", "CapitalizedLetters", 
		"'&nbsp;'", "UnorderedList", "Titleize", "'[['", "']]'", "'['", "']'", 
		"'://'", "'|'", "AnyOtherChar"
	};
	public static final String[] ruleNames = {
		"NestedMetadata", "NestedTable", "CategoryText", "CategoryTag", "ReferenceTag", 
		"PreformattedTag", "HtmlTag", "Emphasis", "WhiteSpace", "SpaceTabOrFormFeed", 
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

		case 6: HtmlTag_action((RuleContext)_localctx, actionIndex); break;

		case 7: Emphasis_action((RuleContext)_localctx, actionIndex); break;

		case 11: Ellipsis_action((RuleContext)_localctx, actionIndex); break;

		case 12: CapitalizedLetters_action((RuleContext)_localctx, actionIndex); break;

		case 13: NonBreakSpace_action((RuleContext)_localctx, actionIndex); break;

		case 14: UnorderedList_action((RuleContext)_localctx, actionIndex); break;

		case 15: Titleize_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void NonBreakSpace_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2: setText(" "); break;
		}
	}
	private void Emphasis_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 9: skip();  break;
		}
	}
	private void UnorderedList_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 10: skip();  break;
		}
	}
	private void Titleize_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 11: skip();  break;
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
		case 8: skip();  break;
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
		case 14: return UnorderedList_sempred((RuleContext)_localctx, predIndex);

		case 15: return Titleize_sempred((RuleContext)_localctx, predIndex);
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
		"\1\2\24\u00ef\6\uffff\2\0\7\0\2\1\7\1\2\2\7\2\2\3\7\3\2\4\7\4\2\5\7\5"+
		"\2\6\7\6\2\7\7\7\2\b\7\b\2\t\7\t\2\n\7\n\2\13\7\13\2\f\7\f\2\r\7\r\2\16"+
		"\7\16\2\17\7\17\2\20\7\20\2\21\7\21\2\22\7\22\2\23\7\23\2\24\7\24\2\25"+
		"\7\25\2\26\7\26\1\0\1\0\1\0\1\0\1\0\5\0\65\b\0\n\0\f\08\t\0\1\0\1\0\1"+
		"\0\1\0\1\0\1\1\1\1\1\1\1\1\1\1\4\1D\b\1\13\1\f\1E\1\1\1\1\1\1\1\1\1\1"+
		"\1\2\4\2N\b\2\13\2\f\2O\1\3\1\3\1\3\1\3\1\3\1\3\1\3\1\3\1\3\1\3\1\3\1"+
		"\4\1\4\1\4\1\4\1\4\1\4\1\4\5\4d\b\4\n\4\f\4g\t\4\1\4\1\4\1\4\1\4\1\4\1"+
		"\4\1\4\1\4\1\4\1\5\1\5\1\5\1\5\1\5\1\5\5\5x\b\5\n\5\f\5{\t\5\1\5\1\5\5"+
		"\5\177\b\5\n\5\f\5\u0082\t\5\1\5\1\5\1\5\1\5\1\5\1\5\1\5\1\5\1\5\1\6\1"+
		"\6\5\6\u008f\b\6\n\6\f\6\u0092\t\6\1\6\1\6\1\6\1\6\1\7\1\7\4\7\u009a\b"+
		"\7\13\7\f\7\u009b\1\7\1\7\1\b\1\b\1\t\1\t\1\n\1\n\5\n\u00a6\b\n\n\n\f"+
		"\n\u00a9\t\n\1\13\1\13\4\13\u00ad\b\13\13\13\f\13\u00ae\1\13\1\13\1\f"+
		"\4\f\u00b4\b\f\13\f\f\f\u00b5\1\f\1\f\1\r\1\r\1\r\1\r\1\r\1\r\1\r\1\r"+
		"\1\r\1\16\1\16\1\16\4\16\u00c6\b\16\13\16\f\16\u00c7\3\16\u00ca\b\16\1"+
		"\16\1\16\1\17\1\17\4\17\u00d0\b\17\13\17\f\17\u00d1\1\17\4\17\u00d5\b"+
		"\17\13\17\f\17\u00d6\1\17\3\17\u00da\b\17\1\17\1\17\1\20\1\20\1\20\1\21"+
		"\1\21\1\21\1\22\1\22\1\23\1\23\1\24\1\24\1\24\1\24\1\25\1\25\1\26\1\26"+
		"\6\66Eey\u0080\u0090\27\1\1\3\3\2\4\5\0\uffff\7\3\5\t\4\6\13\5\7\r\6\b"+
		"\17\7\t\21\b\uffff\23\0\uffff\25\0\uffff\27\t\0\31\n\1\33\13\2\35\f\n"+
		"\37\r\13!\16\uffff#\17\uffff%\20\uffff\'\21\uffff)\22\uffff+\23\uffff"+
		"-\24\uffff\1\0\5\4::[[]]||\2\t\r  \3\t\t\13\f  \1AZ\2##**\u00fd\0\1\1"+
		"\0\0\0\0\3\1\0\0\0\0\7\1\0\0\0\0\t\1\0\0\0\0\13\1\0\0\0\0\r\1\0\0\0\0"+
		"\17\1\0\0\0\0\21\1\0\0\0\0\27\1\0\0\0\0\31\1\0\0\0\0\33\1\0\0\0\0\35\1"+
		"\0\0\0\0\37\1\0\0\0\0!\1\0\0\0\0#\1\0\0\0\0%\1\0\0\0\0\'\1\0\0\0\0)\1"+
		"\0\0\0\0+\1\0\0\0\0-\1\0\0\0\1/\1\0\0\0\3>\1\0\0\0\5M\1\0\0\0\7Q\1\0\0"+
		"\0\t\\\1\0\0\0\13q\1\0\0\0\r\u008c\1\0\0\0\17\u0097\1\0\0\0\21\u009f\1"+
		"\0\0\0\23\u00a1\1\0\0\0\25\u00a3\1\0\0\0\27\u00aa\1\0\0\0\31\u00b3\1\0"+
		"\0\0\33\u00b9\1\0\0\0\35\u00c2\1\0\0\0\37\u00d9\1\0\0\0!\u00dd\1\0\0\0"+
		"#\u00e0\1\0\0\0%\u00e3\1\0\0\0\'\u00e5\1\0\0\0)\u00e7\1\0\0\0+\u00eb\1"+
		"\0\0\0-\u00ed\1\0\0\0/\60\5{\0\0\60\61\5{\0\0\61\66\1\0\0\0\62\65\3\1"+
		"\0\0\63\65\t\0\0\0\64\62\1\0\0\0\64\63\1\0\0\0\658\1\0\0\0\66\67\1\0\0"+
		"\0\66\64\1\0\0\0\679\1\0\0\08\66\1\0\0\09:\5}\0\0:;\5}\0\0;<\1\0\0\0<"+
		"=\6\0\3\0=\2\1\0\0\0>?\5{\0\0?@\5|\0\0@C\1\0\0\0AD\3\3\1\0BD\t\0\0\0C"+
		"A\1\0\0\0CB\1\0\0\0DE\1\0\0\0EF\1\0\0\0EC\1\0\0\0FG\1\0\0\0GH\5|\0\0H"+
		"I\5}\0\0IJ\1\0\0\0JK\6\1\4\0K\4\1\0\0\0LN\b\0\0\0ML\1\0\0\0NO\1\0\0\0"+
		"OM\1\0\0\0OP\1\0\0\0P\6\1\0\0\0QR\5[\0\0RS\5[\0\0ST\1\0\0\0TU\3\5\2\0"+
		"UV\5:\0\0VW\3\5\2\0WX\5]\0\0XY\5]\0\0YZ\1\0\0\0Z[\6\3\5\0[\b\1\0\0\0\\"+
		"]\5<\0\0]^\5r\0\0^_\5e\0\0_`\5f\0\0`a\5>\0\0ae\1\0\0\0bd\t\0\0\0cb\1\0"+
		"\0\0dg\1\0\0\0ef\1\0\0\0ec\1\0\0\0fh\1\0\0\0ge\1\0\0\0hi\5<\0\0ij\5/\0"+
		"\0jk\5r\0\0kl\5e\0\0lm\5f\0\0mn\5>\0\0no\1\0\0\0op\6\4\6\0p\n\1\0\0\0"+
		"qr\5<\0\0rs\5p\0\0st\5r\0\0tu\5e\0\0uy\1\0\0\0vx\t\0\0\0wv\1\0\0\0x{\1"+
		"\0\0\0yz\1\0\0\0yw\1\0\0\0z|\1\0\0\0{y\1\0\0\0|\u0080\5>\0\0}\177\t\0"+
		"\0\0~}\1\0\0\0\177\u0082\1\0\0\0\u0080\u0081\1\0\0\0\u0080~\1\0\0\0\u0081"+
		"\u0083\1\0\0\0\u0082\u0080\1\0\0\0\u0083\u0084\5<\0\0\u0084\u0085\5/\0"+
		"\0\u0085\u0086\5p\0\0\u0086\u0087\5r\0\0\u0087\u0088\5e\0\0\u0088\u0089"+
		"\5>\0\0\u0089\u008a\1\0\0\0\u008a\u008b\6\5\7\0\u008b\f\1\0\0\0\u008c"+
		"\u0090\5<\0\0\u008d\u008f\t\0\0\0\u008e\u008d\1\0\0\0\u008f\u0092\1\0"+
		"\0\0\u0090\u0091\1\0\0\0\u0090\u008e\1\0\0\0\u0091\u0093\1\0\0\0\u0092"+
		"\u0090\1\0\0\0\u0093\u0094\5>\0\0\u0094\u0095\1\0\0\0\u0095\u0096\6\6"+
		"\b\0\u0096\16\1\0\0\0\u0097\u0099\5\'\0\0\u0098\u009a\5\'\0\0\u0099\u0098"+
		"\1\0\0\0\u009a\u009b\1\0\0\0\u009b\u0099\1\0\0\0\u009b\u009c\1\0\0\0\u009c"+
		"\u009d\1\0\0\0\u009d\u009e\6\7\t\0\u009e\20\1\0\0\0\u009f\u00a0\7\1\0"+
		"\0\u00a0\22\1\0\0\0\u00a1\u00a2\7\2\0\0\u00a2\24\1\0\0\0\u00a3\u00a7\5"+
		".\0\0\u00a4\u00a6\3\23\t\0\u00a5\u00a4\1\0\0\0\u00a6\u00a9\1\0\0\0\u00a7"+
		"\u00a5\1\0\0\0\u00a7\u00a8\1\0\0\0\u00a8\26\1\0\0\0\u00a9\u00a7\1\0\0"+
		"\0\u00aa\u00ac\3\25\n\0\u00ab\u00ad\3\25\n\0\u00ac\u00ab\1\0\0\0\u00ad"+
		"\u00ae\1\0\0\0\u00ae\u00ac\1\0\0\0\u00ae\u00af\1\0\0\0\u00af\u00b0\1\0"+
		"\0\0\u00b0\u00b1\6\13\0\0\u00b1\30\1\0\0\0\u00b2\u00b4\7\3\0\0\u00b3\u00b2"+
		"\1\0\0\0\u00b4\u00b5\1\0\0\0\u00b5\u00b3\1\0\0\0\u00b5\u00b6\1\0\0\0\u00b6"+
		"\u00b7\1\0\0\0\u00b7\u00b8\6\f\1\0\u00b8\32\1\0\0\0\u00b9\u00ba\5&\0\0"+
		"\u00ba\u00bb\5n\0\0\u00bb\u00bc\5b\0\0\u00bc\u00bd\5s\0\0\u00bd\u00be"+
		"\5p\0\0\u00be\u00bf\5;\0\0\u00bf\u00c0\1\0\0\0\u00c0\u00c1\6\r\2\0\u00c1"+
		"\34\1\0\0\0\u00c2\u00c9\4\16\0\0\u00c3\u00ca\7\4\0\0\u00c4\u00c6\5:\0"+
		"\0\u00c5\u00c4\1\0\0\0\u00c6\u00c7\1\0\0\0\u00c7\u00c5\1\0\0\0\u00c7\u00c8"+
		"\1\0\0\0\u00c8\u00ca\1\0\0\0\u00c9\u00c3\1\0\0\0\u00c9\u00c5\1\0\0\0\u00ca"+
		"\u00cb\1\0\0\0\u00cb\u00cc\6\16\n\0\u00cc\36\1\0\0\0\u00cd\u00cf\4\17"+
		"\1\0\u00ce\u00d0\5=\0\0\u00cf\u00ce\1\0\0\0\u00d0\u00d1\1\0\0\0\u00d1"+
		"\u00cf\1\0\0\0\u00d1\u00d2\1\0\0\0\u00d2\u00da\1\0\0\0\u00d3\u00d5\5="+
		"\0\0\u00d4\u00d3\1\0\0\0\u00d5\u00d6\1\0\0\0\u00d6\u00d4\1\0\0\0\u00d6"+
		"\u00d7\1\0\0\0\u00d7\u00d8\1\0\0\0\u00d8\u00da\4\17\2\0\u00d9\u00cd\1"+
		"\0\0\0\u00d9\u00d4\1\0\0\0\u00da\u00db\1\0\0\0\u00db\u00dc\6\17\13\0\u00dc"+
		" \1\0\0\0\u00dd\u00de\5[\0\0\u00de\u00df\5[\0\0\u00df\"\1\0\0\0\u00e0"+
		"\u00e1\5]\0\0\u00e1\u00e2\5]\0\0\u00e2$\1\0\0\0\u00e3\u00e4\5[\0\0\u00e4"+
		"&\1\0\0\0\u00e5\u00e6\5]\0\0\u00e6(\1\0\0\0\u00e7\u00e8\5:\0\0\u00e8\u00e9"+
		"\5/\0\0\u00e9\u00ea\5/\0\0\u00ea*\1\0\0\0\u00eb\u00ec\5|\0\0\u00ec,\1"+
		"\0\0\0\u00ed\u00ee\t\0\0\0\u00ee.\1\0\0\0\23\0\64\66CEOey\u0080\u0090"+
		"\u009b\u00a7\u00ae\u00b5\u00c7\u00c9\u00d1\u00d6\u00d9";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
	    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}