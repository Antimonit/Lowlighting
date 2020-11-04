package me.khol.intellij.plugin.language.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import me.khol.intellij.plugin.language.psi.LowlightingNamedElement

abstract class LowlightingNamedElementImpl(node: ASTNode) : ASTWrapperPsiElement(node), LowlightingNamedElement
