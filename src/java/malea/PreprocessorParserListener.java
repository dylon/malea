
  package malea;

import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.Token;

public interface PreprocessorParserListener extends ParseTreeListener {
	void enterAnnotation(PreprocessorParser.AnnotationContext ctx);
	void exitAnnotation(PreprocessorParser.AnnotationContext ctx);

	void enterPreprocess(PreprocessorParser.PreprocessContext ctx);
	void exitPreprocess(PreprocessorParser.PreprocessContext ctx);

	void enterHyperlink(PreprocessorParser.HyperlinkContext ctx);
	void exitHyperlink(PreprocessorParser.HyperlinkContext ctx);
}