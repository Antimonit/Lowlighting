package me.khol.intellij.plugin

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiCall
import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtCallElement

class LowlightingAnnotator : Annotator {

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element is PsiCall && element.isLowlightingAnnotated() ||
            element is KtCallElement && element.isLowlightingAnnotated()
        ) {
            highlight(element, holder)
        }
    }

    private fun highlight(call: PsiElement, holder: AnnotationHolder) {
        holder.newAnnotation(HighlightSeverity.WEAK_WARNING, "Lowlight")
            .range(call.textRange)
            .highlightType(ProblemHighlightType.LIKE_UNUSED_SYMBOL)
            .create()
    }
}
