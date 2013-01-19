lexer grammar PreprocessorLexer;

options {
	superClass = AbstractPreprocessorLexer;
}

@lexer::header {
	package malea;
}

NestedMetadata
: '{{' (NestedMetadata | .)*? '}}' -> skip
;

NestedTable
: '{|' ( NestedTable | .)+? '|}' -> skip
;

fragment CategoryText
: ~( ':' | '|' | '[' | ']' )+
;

CategoryTag
: '[[' CategoryText ':' CategoryText ']]' -> skip
;

ReferenceTag
: '<ref>' .*? '</ref>' -> skip
;

PreformattedTag
: '<pre' .*? '>' .*? '</pre>' -> skip
;

HtmlTag
: '<' .*? '>' -> skip
;

Emphasis
: '\'' '\''+ -> skip
;

WhiteSpace
: [ \r\n\t\f\u0009-\u000d]
;

fragment SpaceTabOrFormFeed
: [ \t\f\u0009\u000b\u000c]
;

fragment EllipsisFragment
: '.' SpaceTabOrFormFeed*
;

Ellipsis
: EllipsisFragment EllipsisFragment+ {setText("\u2026");}
;

// Lower-case all capitalized letters
CapitalizedLetters
: [A-Z]+ {setText(getText().toLowerCase());}
;

NonBreakSpace
: '&nbsp;' {setText(" ");}
;

UnorderedList
: {start_of_line()}? ('*'|'#'|':'+) -> skip
;

Titleize
: // Heuristic
( {start_of_line()}? '='+
| '='+ {end_of_line()}?
) -> skip
;

DoubleOpenSquareBracket
: '[['
;

DoubleCloseSquareBracket
: ']]'
;

OpenSquareBracket
: '['
;

CloseSquareBracket
: ']'
;

ColonDoubleForwardSlash
: '://'
;

Pipe
: '|'
;

AnyOtherChar
: .
;

