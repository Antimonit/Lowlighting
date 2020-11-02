package me.khol.intellij.plugin.language.psi

import com.intellij.psi.tree.IElementType
import me.khol.intellij.plugin.language.LowlightingLanguage
import org.jetbrains.annotations.NonNls

class LowlightingTokenType(@NonNls debugName: String) : IElementType(debugName, LowlightingLanguage) {

    override fun toString() = "SimpleTokenType." + super.toString()
}