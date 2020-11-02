package me.khol.intellij.plugin.language

import com.intellij.lexer.FlexAdapter

class LowlightingLexerAdapter : FlexAdapter(LowlightingLexer(null))
