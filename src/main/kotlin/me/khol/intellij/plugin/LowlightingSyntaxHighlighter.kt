// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package me.khol.intellij.plugin

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import me.khol.intellij.plugin.language.LowlightingLexerAdapter
import me.khol.intellij.plugin.language.psi.LowlightingTypes
import org.jetbrains.annotations.NonNls

class LowlightingSyntaxHighlighter : SyntaxHighlighterBase() {

    override fun getHighlightingLexer() = LowlightingLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        return when (tokenType) {
            LowlightingTypes.SEPARATOR -> SEPARATOR_KEYS
            LowlightingTypes.KEY -> KEY_KEYS
            LowlightingTypes.VALUE -> VALUE_KEYS
            LowlightingTypes.COMMENT -> COMMENT_KEYS
            TokenType.BAD_CHARACTER -> BAD_CHAR_KEYS
            else -> EMPTY_KEYS
        }
    }

    companion object {
        private fun key(@NonNls externalName: String, fallbackAttributeKey: TextAttributesKey) =
            TextAttributesKey.createTextAttributesKey(externalName, fallbackAttributeKey)

        private val SEPARATOR = key("SIMPLE_SEPARATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN)
        private val KEY = key("SIMPLE_KEY", DefaultLanguageHighlighterColors.KEYWORD)
        private val VALUE = key("SIMPLE_VALUE", DefaultLanguageHighlighterColors.STRING)
        private val COMMENT = key("SIMPLE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
        private val BAD_CHARACTER = key("SIMPLE_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)

        private val BAD_CHAR_KEYS = arrayOf(BAD_CHARACTER)
        private val SEPARATOR_KEYS = arrayOf(SEPARATOR)
        private val KEY_KEYS = arrayOf(KEY)
        private val VALUE_KEYS = arrayOf(VALUE)
        private val COMMENT_KEYS = arrayOf(COMMENT)
        private val EMPTY_KEYS = arrayOf<TextAttributesKey>()
    }
}