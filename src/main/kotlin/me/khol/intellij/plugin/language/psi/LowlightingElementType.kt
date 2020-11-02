package me.khol.intellij.plugin.language.psi

import com.intellij.psi.tree.IElementType
import me.khol.intellij.plugin.language.LowlightingLanguage
import org.jetbrains.annotations.NonNls

class LowlightingElementType(@NonNls debugName: String) : IElementType(debugName, LowlightingLanguage)