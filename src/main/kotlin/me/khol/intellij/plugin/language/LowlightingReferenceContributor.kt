package me.khol.intellij.plugin.language

import com.intellij.openapi.util.TextRange
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReferenceContributor
import com.intellij.psi.PsiReferenceProvider
import com.intellij.psi.PsiReferenceRegistrar
import com.intellij.util.ProcessingContext
import me.khol.intellij.plugin.language.psi.LowlightingTypes

class LowlightingReferenceContributor : PsiReferenceContributor() {

    override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
        // It is important that [LowlightingNamedElementImpl] overrides [getReferences].
        registrar.registerReferenceProvider(
            PlatformPatterns.psiElement(LowlightingTypes.PROPERTY),
            object : PsiReferenceProvider() {
                override fun getReferencesByElement(
                    element: PsiElement,
                    context: ProcessingContext
                ) = arrayOf(LowlightingReference(element, TextRange(0, element.textLength)))
            }
        )
    }
}
