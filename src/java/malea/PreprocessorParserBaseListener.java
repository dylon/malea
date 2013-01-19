
	package malea;


import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.ErrorNode;

public class PreprocessorParserBaseListener implements PreprocessorParserListener {
	@Override public void enterAnnotation(PreprocessorParser.AnnotationContext ctx) { }
	@Override public void exitAnnotation(PreprocessorParser.AnnotationContext ctx) { }

	@Override public void enterPreprocess(PreprocessorParser.PreprocessContext ctx) { }
	@Override public void exitPreprocess(PreprocessorParser.PreprocessContext ctx) { }

	@Override public void enterHyperlink(PreprocessorParser.HyperlinkContext ctx) { }
	@Override public void exitHyperlink(PreprocessorParser.HyperlinkContext ctx) { }

	@Override public void enterEveryRule(ParserRuleContext ctx) { }
	@Override public void exitEveryRule(ParserRuleContext ctx) { }
	@Override public void visitTerminal(TerminalNode node) { }
	@Override public void visitErrorNode(ErrorNode node) { }
}