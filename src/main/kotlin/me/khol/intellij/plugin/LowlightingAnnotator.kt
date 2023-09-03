package me.khol.intellij.plugin

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiCall
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReferenceExpression
import org.jetbrains.kotlin.psi.KtElement

/**
 * The core functionality of the Lowlighting plugin.
 *
 * See [SimpleAnnotator docs](https://plugins.jetbrains.com/docs/intellij/annotator.html).
 */
class LowlightingAnnotator : Annotator {

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        val records = when (element) {
            // Java
            is PsiCall -> element.lowlightingSeverities()
            is PsiReferenceExpression -> element.lowlightingSeverities()
            // Kotlin
            is KtElement -> element.lowlightingSeverities()
            else -> return
        }

        val severity = records
            .map { ProblemHighlightType.valueOf(it.severity.name) }
            .maxByOrNull { it.ordinal } ?: return

        holder.newAnnotation(HighlightSeverity.WEAK_WARNING, "Lowlight")
            .range(element.textRange)
            .highlightType(severity)
            .create()
    }
}
