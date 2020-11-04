package me.khol.intellij.plugin

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
            LowlightingTypes.KEY -> KEY_KEYS
            LowlightingTypes.COMMENT -> COMMENT_KEYS
            TokenType.BAD_CHARACTER -> BAD_CHAR_KEYS
            else -> EMPTY_KEYS
        }
    }

    companion object {
        private fun key(@NonNls externalName: String, fallbackAttributeKey: TextAttributesKey) =
            TextAttributesKey.createTextAttributesKey(externalName, fallbackAttributeKey)

        private val KEY = key("LOWLIGHTING_KEY", DefaultLanguageHighlighterColors.KEYWORD)
        private val COMMENT = key("LOWLIGHTING_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
        private val BAD_CHARACTER = key("LOWLIGHTING_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)

        private val BAD_CHAR_KEYS = arrayOf(BAD_CHARACTER)
        private val KEY_KEYS = arrayOf(KEY)
        private val COMMENT_KEYS = arrayOf(COMMENT)
        private val EMPTY_KEYS = arrayOf<TextAttributesKey>()
    }
}