package me.khol.intellij.plugin.language;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import me.khol.intellij.plugin.language.psi.LowlightingTypes;

%%

%class LowlightingLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

CRLF=\R
WHITE_SPACE=[\ \n\t\f]
END_OF_LINE_COMMENT=("#")[^\r\n]*
KEY_CHARACTER=[^\ \n\t\f\\] | "\\ "

%%

<YYINITIAL> {END_OF_LINE_COMMENT}                           { yybegin(YYINITIAL); return LowlightingTypes.COMMENT; }

<YYINITIAL> {KEY_CHARACTER}+                                { yybegin(YYINITIAL); return LowlightingTypes.KEY; }

({CRLF}|{WHITE_SPACE})+                                     { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }

[^]                                                         { return TokenType.BAD_CHARACTER; }

// Right click this file -> "Run JFlex Generator" when done.
// Note: Choose the root directory if prompted for one. JFlex jar and skeleton will be downloaded to this directory
//       but need not to be checked into the git.
// This will generate a `LowlightingLexer.java` in the /gen source set.
