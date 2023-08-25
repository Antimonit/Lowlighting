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

EOL=\r|\n|\r\n
WHITE_SPACE=[\ \n\t\f]
ASSIGNMENT=[=]
SEVERITY=[_a-zA-Z]+
COMMENT=("#")[^\r\n]*
KEY=[^=\ \n\t\f]

%state WAITING_SEVERITY
%state WAITING_ASSIGNMENT

%%

<YYINITIAL> {COMMENT}                                       { yybegin(YYINITIAL); return LowlightingTypes.COMMENT; }
<YYINITIAL> {KEY}+                                          { yybegin(WAITING_ASSIGNMENT); return LowlightingTypes.KEY_TOKEN; }
<WAITING_ASSIGNMENT> {ASSIGNMENT}                           { yybegin(WAITING_SEVERITY); return LowlightingTypes.ASSIGNMENT; }
<WAITING_SEVERITY>  {SEVERITY}                              { yybegin(YYINITIAL); return LowlightingTypes.SEVERITY_TOKEN; }
({EOL})+                                                    { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
({WHITE_SPACE})+                                            { return TokenType.WHITE_SPACE; }

[^]                                                         { return TokenType.BAD_CHARACTER; }

// Right click this file -> "Run JFlex Generator" when done.
// Note: Choose the root directory if prompted for one. JFlex jar and skeleton will be downloaded to this directory
//       but need not to be checked into the git.
// This will generate a `LowlightingLexer.java` in the /gen source set.
