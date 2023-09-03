package me.khol.intellij.lowlighting.language

import com.intellij.openapi.util.TextRange
import com.intellij.psi.JavaPsiFacade
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementResolveResult
import com.intellij.psi.PsiPolyVariantReference
import com.intellij.psi.PsiReferenceBase
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.util.containers.map2Array

class LowlightingReference(
    element: PsiElement,
    textRange: TextRange
) : PsiReferenceBase<PsiElement>(element, textRange), PsiPolyVariantReference {

    private val key: String = element.text.substring(textRange.startOffset, textRange.endOffset)

    override fun multiResolve(incompleteCode: Boolean): Array<PsiElementResolveResult> {
        val project = myElement.project
        val psiFacade = JavaPsiFacade.getInstance(project)
        val scope = GlobalSearchScope.allScope(project)
        return psiFacade.findClasses(key, scope).map2Array(::PsiElementResolveResult)
    }

    override fun resolve(): PsiElement? {
        return multiResolve(false).firstOrNull()?.element
    }
}
