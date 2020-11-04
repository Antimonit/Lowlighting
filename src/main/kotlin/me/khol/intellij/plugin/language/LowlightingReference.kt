package me.khol.intellij.plugin.language

import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementResolveResult
import com.intellij.psi.PsiPolyVariantReference
import com.intellij.psi.PsiReferenceBase
import me.khol.intellij.plugin.LowlightingIcons
import me.khol.intellij.plugin.findProperties

class LowlightingReference(
    element: PsiElement,
    textRange: TextRange
) : PsiReferenceBase<PsiElement>(element, textRange), PsiPolyVariantReference {

    private val key: String = element.text.substring(textRange.startOffset, textRange.endOffset)

    override fun multiResolve(incompleteCode: Boolean) = myElement.project
        .findProperties(key)
        .map(::PsiElementResolveResult)
        .toTypedArray()

    override fun resolve(): PsiElement? {
        val resolveResults = multiResolve(false)
        return if (resolveResults.size == 1) resolveResults[0].element else null
    }

    override fun getVariants() = myElement.project
        .findProperties()
        .filter { it.name != null && it.name!!.isNotEmpty() }
        .map {
            LookupElementBuilder
                .create(it)
                .withIcon(LowlightingIcons.FILE)
                .withTypeText(it.containingFile.name)
        }
        .toTypedArray()
}