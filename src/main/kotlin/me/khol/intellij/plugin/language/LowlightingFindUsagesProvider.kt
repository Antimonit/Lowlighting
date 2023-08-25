package me.khol.intellij.plugin.language

import com.intellij.lang.cacheBuilder.DefaultWordsScanner
import com.intellij.lang.cacheBuilder.WordsScanner
import com.intellij.lang.findUsages.FindUsagesProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNamedElement
import com.intellij.psi.tree.TokenSet
import me.khol.intellij.plugin.language.psi.LowlightingKey
import me.khol.intellij.plugin.language.psi.LowlightingTypes

class LowlightingFindUsagesProvider : FindUsagesProvider {

    override fun getWordsScanner(): WordsScanner? {
        return DefaultWordsScanner(
            LowlightingLexerAdapter(),
            TokenSet.create(LowlightingTypes.KEY_TOKEN),
            TokenSet.create(LowlightingTypes.COMMENT),
            TokenSet.EMPTY
        )
    }

    override fun canFindUsagesFor(psiElement: PsiElement): Boolean = psiElement is PsiNamedElement

    override fun getHelpId(psiElement: PsiElement): String? = null

    override fun getType(element: PsiElement): String {
        return if (element is LowlightingKey) {
            "lowlighting property"
        } else {
            ""
        }
    }

    override fun getDescriptiveName(element: PsiElement): String {
        return if (element is LowlightingKey) {
            element.name
        } else {
            ""
        }
    }

    override fun getNodeText(element: PsiElement, useFullName: Boolean): String {
        return getDescriptiveName(element)
    }
}