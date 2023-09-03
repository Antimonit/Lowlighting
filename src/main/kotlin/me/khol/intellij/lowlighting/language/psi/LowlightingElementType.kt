package me.khol.intellij.lowlighting.language.psi

import com.intellij.psi.tree.IElementType
import me.khol.intellij.lowlighting.language.LowlightingLanguage
import org.jetbrains.annotations.NonNls

class LowlightingElementType(@NonNls debugName: String) : IElementType(debugName, LowlightingLanguage)