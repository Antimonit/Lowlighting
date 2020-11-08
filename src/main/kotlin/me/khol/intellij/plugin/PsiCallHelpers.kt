package me.khol.intellij.plugin

import com.intellij.psi.PsiCall
import org.jetbrains.kotlin.idea.refactoring.fqName.getKotlinFqName
import org.jetbrains.kotlin.idea.references.KtSimpleNameReference
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.nj2k.postProcessing.resolve
import org.jetbrains.kotlin.psi.KtAnnotationEntry
import org.jetbrains.kotlin.psi.KtCallElement
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.KtReferenceExpression
import org.jetbrains.kotlin.psi.psiUtil.getCallNameExpression

// TODO: Check also definition of methods in super classes
internal fun PsiCall.isLowlightingAnnotated(): Boolean {
    val method = resolveMethod() ?: return false
    if (method.annotations.isEmpty()) return false
    val annotations = project.findLowlightingPropertiesNames()
    return method.annotations.any { annotation ->
        annotations.any { fqn ->
            fqn == annotation.qualifiedName
        }
    }
}

// TODO: Check also definition of methods in super classes
internal fun KtCallElement.isLowlightingAnnotated(): Boolean {
    val referenceExpression = children.find { it is KtReferenceExpression } ?: return false
    val reference = referenceExpression.references.find { it is KtSimpleNameReference } ?: return false
    val namedFunction = reference.resolve() as? KtNamedFunction ?: return false
    if (namedFunction.annotationEntries.isEmpty()) return false
    val annotations = project.findLowlightingPropertiesNames()
    return namedFunction.annotationEntries.any { isLowlightingAnnotation(it, annotations) }
}

private fun isLowlightingAnnotation(entry: KtAnnotationEntry, annotations: List<String>): Boolean {
    return entry.getCallNameExpression()
        ?.resolve()
        ?.getKotlinFqName()?.let { kotlinFqn ->
            annotations.any { fqn ->
                FqName(fqn) == kotlinFqn
            }
        } ?: false
}
