package me.khol.intellij.lowlighting.language

import com.intellij.lexer.FlexAdapter

class LowlightingLexerAdapter : FlexAdapter(LowlightingLexer(null))
