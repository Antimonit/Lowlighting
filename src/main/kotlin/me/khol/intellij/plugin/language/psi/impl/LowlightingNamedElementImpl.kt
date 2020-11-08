package me.khol.intellij.plugin.language.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiReference
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry
import me.khol.intellij.plugin.language.psi.LowlightingNamedElement

abstract class LowlightingNamedElementImpl(node: ASTNode) : ASTWrapperPsiElement(node), LowlightingNamedElement {

    override fun getReferences(): Array<PsiReference> {
        return ReferenceProvidersRegistry.getReferencesFromProviders(this)
    }
}
