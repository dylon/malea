parser grammar PreprocessorParser;

options {
  tokenVocab = PreprocessorLexer;
}

@parser::header {
  package malea;
}

preprocess returns [String preprocessed]
@init { StringBuilder buffer = new StringBuilder(); }
:
( a=annotation { buffer.append($a.text); }
| b=hyperlink { buffer.append($b.text); }
| c=. { buffer.append($c.text); }
)* EOF
{ $preprocessed = buffer.toString(); }
;

annotation returns [String text]
@init { StringBuilder buffer = new StringBuilder(); }
: DoubleOpenSquareBracket
		( ( annotation | ~( DoubleOpenSquareBracket | DoubleCloseSquareBracket )+ )+
			Pipe
		)*
	( b=annotation { buffer.append($b.text); }
	| ( a=~( DoubleOpenSquareBracket | DoubleCloseSquareBracket )
  		{ buffer.append($a.text); }
  	)+
	)+?
	DoubleCloseSquareBracket
{ $text = buffer.toString(); }
;

hyperlink returns [String text]
@init { StringBuilder buffer = new StringBuilder(); }
: OpenSquareBracket ~WhiteSpace+? ColonDoubleForwardSlash ~WhiteSpace+ WhiteSpace+
  ( a=~CloseSquareBracket { buffer.append($a.text); } )*?
  CloseSquareBracket
{ $text = buffer.toString(); }
;

