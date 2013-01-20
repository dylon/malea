// $ANTLR ANTLRVersion> PreprocessorParser.java generatedTimestamp>

  package malea;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PreprocessorParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		MathTag=6, Titleize=14, Pipe=20, CapitalizedLetters=11, AnyOtherChar=21, 
		HtmlTag=7, OpenSquareBracket=17, NestedMetadata=1, Ellipsis=10, Emphasis=8, 
		NonBreakSpace=12, UnorderedList=13, ColonDoubleForwardSlash=19, DoubleCloseSquareBracket=16, 
		WhiteSpace=9, NestedTable=2, ReferenceTag=4, PreformattedTag=5, CloseSquareBracket=18, 
		DoubleOpenSquareBracket=15, CategoryTag=3;
	public static final String[] tokenNames = {
		"<INVALID>", "NestedMetadata", "NestedTable", "CategoryTag", "ReferenceTag", 
		"PreformattedTag", "MathTag", "HtmlTag", "Emphasis", "WhiteSpace", "Ellipsis", 
		"CapitalizedLetters", "'&nbsp;'", "UnorderedList", "Titleize", "'[['", 
		"']]'", "'['", "']'", "'://'", "'|'", "AnyOtherChar"
	};
	public static final int
		RULE_preprocess = 0, RULE_annotation = 1, RULE_hyperlink = 2;
	public static final String[] ruleNames = {
		"preprocess", "annotation", "hyperlink"
	};

	@Override
	public String getGrammarFileName() { return "PreprocessorParser.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public PreprocessorParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class PreprocessContext extends ParserRuleContext {
		public String preprocessed;
		public AnnotationContext a;
		public HyperlinkContext b;
		public Token c;
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public HyperlinkContext hyperlink(int i) {
			return getRuleContext(HyperlinkContext.class,i);
		}
		public List<HyperlinkContext> hyperlink() {
			return getRuleContexts(HyperlinkContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public TerminalNode EOF() { return getToken(PreprocessorParser.EOF, 0); }
		public PreprocessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_preprocess; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PreprocessorParserListener ) ((PreprocessorParserListener)listener).enterPreprocess(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PreprocessorParserListener ) ((PreprocessorParserListener)listener).exitPreprocess(this);
		}
	}

	public final PreprocessContext preprocess() throws RecognitionException {
		PreprocessContext _localctx = new PreprocessContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_preprocess);
		 StringBuilder buffer = new StringBuilder(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NestedMetadata) | (1L << NestedTable) | (1L << CategoryTag) | (1L << ReferenceTag) | (1L << PreformattedTag) | (1L << MathTag) | (1L << HtmlTag) | (1L << Emphasis) | (1L << WhiteSpace) | (1L << Ellipsis) | (1L << CapitalizedLetters) | (1L << NonBreakSpace) | (1L << UnorderedList) | (1L << Titleize) | (1L << DoubleOpenSquareBracket) | (1L << DoubleCloseSquareBracket) | (1L << OpenSquareBracket) | (1L << CloseSquareBracket) | (1L << ColonDoubleForwardSlash) | (1L << Pipe) | (1L << AnyOtherChar))) != 0)) {
				{
				setState(14);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(6); ((PreprocessContext)_localctx).a = annotation();
					 buffer.append(((PreprocessContext)_localctx).a.text); 
					}
					break;

				case 2:
					{
					setState(9); ((PreprocessContext)_localctx).b = hyperlink();
					 buffer.append(((PreprocessContext)_localctx).b.text); 
					}
					break;

				case 3:
					{
					setState(12);
					((PreprocessContext)_localctx).c = matchWildcard();
					 buffer.append((((PreprocessContext)_localctx).c!=null?((PreprocessContext)_localctx).c.getText():null)); 
					}
					break;
				}
				}
				setState(18);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(19); match(EOF);
			 ((PreprocessContext)_localctx).preprocessed =  buffer.toString(); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnnotationContext extends ParserRuleContext {
		public String text;
		public AnnotationContext b;
		public Token a;
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public TerminalNode Pipe(int i) {
			return getToken(PreprocessorParser.Pipe, i);
		}
		public List<TerminalNode> DoubleCloseSquareBracket() { return getTokens(PreprocessorParser.DoubleCloseSquareBracket); }
		public List<TerminalNode> Pipe() { return getTokens(PreprocessorParser.Pipe); }
		public TerminalNode DoubleOpenSquareBracket(int i) {
			return getToken(PreprocessorParser.DoubleOpenSquareBracket, i);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public TerminalNode DoubleCloseSquareBracket(int i) {
			return getToken(PreprocessorParser.DoubleCloseSquareBracket, i);
		}
		public List<TerminalNode> DoubleOpenSquareBracket() { return getTokens(PreprocessorParser.DoubleOpenSquareBracket); }
		public AnnotationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PreprocessorParserListener ) ((PreprocessorParserListener)listener).enterAnnotation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PreprocessorParserListener ) ((PreprocessorParserListener)listener).exitAnnotation(this);
		}
	}

	public final AnnotationContext annotation() throws RecognitionException {
		AnnotationContext _localctx = new AnnotationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_annotation);
		 StringBuilder buffer = new StringBuilder(); 
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(22); match(DoubleOpenSquareBracket);
			setState(36);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(29); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
					do {
						switch (_alt) {
						case 1:
							{
							setState(29);
							switch (_input.LA(1)) {
							case DoubleOpenSquareBracket:
								{
								setState(23); annotation();
								}
								break;
							case NestedMetadata:
							case NestedTable:
							case CategoryTag:
							case ReferenceTag:
							case PreformattedTag:
							case MathTag:
							case HtmlTag:
							case Emphasis:
							case WhiteSpace:
							case Ellipsis:
							case CapitalizedLetters:
							case NonBreakSpace:
							case UnorderedList:
							case Titleize:
							case OpenSquareBracket:
							case CloseSquareBracket:
							case ColonDoubleForwardSlash:
							case Pipe:
							case AnyOtherChar:
								{
								setState(25); 
								_errHandler.sync(this);
								_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
								do {
									switch (_alt) {
									case 1:
										{
										{
										setState(24);
										_la = _input.LA(1);
										if ( _la <= 0 || (_la==DoubleOpenSquareBracket || _la==DoubleCloseSquareBracket) ) {
										_errHandler.recoverInline(this);
										}
										consume();
										}
										}
										break;
									default:
										throw new NoViableAltException(this);
									}
									setState(27); 
									_errHandler.sync(this);
									_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
								} while ( _alt!=2 && _alt!=-1 );
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(31); 
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
					} while ( _alt!=2 && _alt!=-1 );
					setState(33); match(Pipe);
					}
					} 
				}
				setState(38);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			setState(48); 
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			do {
				switch (_alt) {
				case 1+1:
					{
					setState(48);
					switch (_input.LA(1)) {
					case DoubleOpenSquareBracket:
						{
						setState(39); ((AnnotationContext)_localctx).b = annotation();
						 buffer.append(((AnnotationContext)_localctx).b.text); 
						}
						break;
					case NestedMetadata:
					case NestedTable:
					case CategoryTag:
					case ReferenceTag:
					case PreformattedTag:
					case MathTag:
					case HtmlTag:
					case Emphasis:
					case WhiteSpace:
					case Ellipsis:
					case CapitalizedLetters:
					case NonBreakSpace:
					case UnorderedList:
					case Titleize:
					case OpenSquareBracket:
					case CloseSquareBracket:
					case ColonDoubleForwardSlash:
					case Pipe:
					case AnyOtherChar:
						{
						setState(44); 
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
						do {
							switch (_alt) {
							case 1:
								{
								{
								setState(42);
								((AnnotationContext)_localctx).a = _input.LT(1);
								_la = _input.LA(1);
								if ( _la <= 0 || (_la==DoubleOpenSquareBracket || _la==DoubleCloseSquareBracket) ) {
									((AnnotationContext)_localctx).a = (Token)_errHandler.recoverInline(this);
								}
								consume();
								 buffer.append((((AnnotationContext)_localctx).a!=null?((AnnotationContext)_localctx).a.getText():null)); 
								}
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							setState(46); 
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
						} while ( _alt!=2 && _alt!=-1 );
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(50); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			} while ( _alt!=1 && _alt!=-1 );
			setState(52); match(DoubleCloseSquareBracket);
			 ((AnnotationContext)_localctx).text =  buffer.toString(); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HyperlinkContext extends ParserRuleContext {
		public String text;
		public Token a;
		public TerminalNode CloseSquareBracket(int i) {
			return getToken(PreprocessorParser.CloseSquareBracket, i);
		}
		public TerminalNode ColonDoubleForwardSlash() { return getToken(PreprocessorParser.ColonDoubleForwardSlash, 0); }
		public List<TerminalNode> WhiteSpace() { return getTokens(PreprocessorParser.WhiteSpace); }
		public List<TerminalNode> CloseSquareBracket() { return getTokens(PreprocessorParser.CloseSquareBracket); }
		public TerminalNode WhiteSpace(int i) {
			return getToken(PreprocessorParser.WhiteSpace, i);
		}
		public TerminalNode OpenSquareBracket() { return getToken(PreprocessorParser.OpenSquareBracket, 0); }
		public HyperlinkContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hyperlink; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PreprocessorParserListener ) ((PreprocessorParserListener)listener).enterHyperlink(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PreprocessorParserListener ) ((PreprocessorParserListener)listener).exitHyperlink(this);
		}
	}

	public final HyperlinkContext hyperlink() throws RecognitionException {
		HyperlinkContext _localctx = new HyperlinkContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_hyperlink);
		 StringBuilder buffer = new StringBuilder(); 
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(55); match(OpenSquareBracket);
			setState(57); 
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			do {
				switch (_alt) {
				case 1+1:
					{
					{
					setState(56);
					_la = _input.LA(1);
					if ( _la <= 0 || (_la==WhiteSpace) ) {
					_errHandler.recoverInline(this);
					}
					consume();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(59); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			} while ( _alt!=1 && _alt!=-1 );
			setState(61); match(ColonDoubleForwardSlash);
			setState(63); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(62);
				_la = _input.LA(1);
				if ( _la <= 0 || (_la==WhiteSpace) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				}
				setState(65); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NestedMetadata) | (1L << NestedTable) | (1L << CategoryTag) | (1L << ReferenceTag) | (1L << PreformattedTag) | (1L << MathTag) | (1L << HtmlTag) | (1L << Emphasis) | (1L << Ellipsis) | (1L << CapitalizedLetters) | (1L << NonBreakSpace) | (1L << UnorderedList) | (1L << Titleize) | (1L << DoubleOpenSquareBracket) | (1L << DoubleCloseSquareBracket) | (1L << OpenSquareBracket) | (1L << CloseSquareBracket) | (1L << ColonDoubleForwardSlash) | (1L << Pipe) | (1L << AnyOtherChar))) != 0) );
			setState(68); 
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(67); match(WhiteSpace);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(70); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			} while ( _alt!=2 && _alt!=-1 );
			setState(76);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=1 && _alt!=-1 ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(72);
					((HyperlinkContext)_localctx).a = _input.LT(1);
					_la = _input.LA(1);
					if ( _la <= 0 || (_la==CloseSquareBracket) ) {
						((HyperlinkContext)_localctx).a = (Token)_errHandler.recoverInline(this);
					}
					consume();
					 buffer.append((((HyperlinkContext)_localctx).a!=null?((HyperlinkContext)_localctx).a.getText():null)); 
					}
					} 
				}
				setState(78);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			}
			setState(79); match(CloseSquareBracket);
			 ((HyperlinkContext)_localctx).text =  buffer.toString(); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\1\1\25S\2\0\7\0\2\1\7\1\2\2\7\2\1\0\1\0\1\0\1\0\1\0\1\0\1\0\1\0\5\0\17"+
		"\b\0\n\0\f\0\22\t\0\1\0\1\0\1\0\1\1\1\1\1\1\4\1\32\b\1\13\1\f\1\33\4\1"+
		"\36\b\1\13\1\f\1\37\1\1\5\1#\b\1\n\1\f\1&\t\1\1\1\1\1\1\1\1\1\1\1\4\1"+
		"-\b\1\13\1\f\1.\4\1\61\b\1\13\1\f\1\62\1\1\1\1\1\1\1\2\1\2\4\2:\b\2\13"+
		"\2\f\2;\1\2\1\2\4\2@\b\2\13\2\f\2A\1\2\4\2E\b\2\13\2\f\2F\1\2\1\2\5\2"+
		"K\b\2\n\2\f\2N\t\2\1\2\1\2\1\2\1\2\3\62;L\3\0\2\4\0\5\1\17\20\1\17\20"+
		"\1\t\t\1\t\t\1\22\22]\0\20\1\0\0\0\2\26\1\0\0\0\4\67\1\0\0\0\6\7\3\2\1"+
		"\0\7\b\6\0\uffff\0\b\17\1\0\0\0\t\n\3\4\2\0\n\13\6\0\uffff\0\13\17\1\0"+
		"\0\0\f\r\t\0\0\0\r\17\6\0\uffff\0\16\6\1\0\0\0\16\t\1\0\0\0\16\f\1\0\0"+
		"\0\17\22\1\0\0\0\20\16\1\0\0\0\20\21\1\0\0\0\21\23\1\0\0\0\22\20\1\0\0"+
		"\0\23\24\5\uffff\0\0\24\25\6\0\uffff\0\25\1\1\0\0\0\26$\5\17\0\0\27\36"+
		"\3\2\1\0\30\32\b\0\0\0\31\30\1\0\0\0\32\33\1\0\0\0\33\31\1\0\0\0\33\34"+
		"\1\0\0\0\34\36\1\0\0\0\35\27\1\0\0\0\35\31\1\0\0\0\36\37\1\0\0\0\37\35"+
		"\1\0\0\0\37 \1\0\0\0 !\1\0\0\0!#\5\24\0\0\"\35\1\0\0\0#&\1\0\0\0$\"\1"+
		"\0\0\0$%\1\0\0\0%\60\1\0\0\0&$\1\0\0\0\'(\3\2\1\0()\6\1\uffff\0)\61\1"+
		"\0\0\0*+\b\1\0\0+-\6\1\uffff\0,*\1\0\0\0-.\1\0\0\0.,\1\0\0\0./\1\0\0\0"+
		"/\61\1\0\0\0\60\'\1\0\0\0\60,\1\0\0\0\61\62\1\0\0\0\62\63\1\0\0\0\62\60"+
		"\1\0\0\0\63\64\1\0\0\0\64\65\5\20\0\0\65\66\6\1\uffff\0\66\3\1\0\0\0\67"+
		"9\5\21\0\08:\b\2\0\098\1\0\0\0:;\1\0\0\0;<\1\0\0\0;9\1\0\0\0<=\1\0\0\0"+
		"=?\5\23\0\0>@\b\3\0\0?>\1\0\0\0@A\1\0\0\0A?\1\0\0\0AB\1\0\0\0BD\1\0\0"+
		"\0CE\5\t\0\0DC\1\0\0\0EF\1\0\0\0FD\1\0\0\0FG\1\0\0\0GL\1\0\0\0HI\b\4\0"+
		"\0IK\6\2\uffff\0JH\1\0\0\0KN\1\0\0\0LM\1\0\0\0LJ\1\0\0\0MO\1\0\0\0NL\1"+
		"\0\0\0OP\5\22\0\0PQ\6\2\uffff\0Q\5\1\0\0\0\r\16\20\33\35\37$.\60\62;A"+
		"FL";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
	    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}