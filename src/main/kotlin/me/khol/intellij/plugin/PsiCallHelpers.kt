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
import me.khol.intellij.plugin.settings.AppSettingsState

private val storedAnnotationsFqn: List<String>
    get() = AppSettingsState.instance.annotationsFqn.split(System.lineSeparator()).filter(String::isNotBlank)

internal fun PsiCall.isLowlightingAnnotated(): Boolean {
    val method = resolveMethod() ?: return false
    return method.annotations.any { annotation ->
        storedAnnotationsFqn.any { fqn ->
            fqn == annotation.qualifiedName
        }
    }
}

internal fun KtCallElement.isLowlightingAnnotated(): Boolean {
    val referenceExpression = children.find { it is KtReferenceExpression } ?: return false
    val reference = referenceExpression.references.find { it is KtSimpleNameReference } ?: return false
    val namedFunction = reference.resolve() as? KtNamedFunction ?: return false
    return namedFunction.annotationEntries.any(::isLowlightingAnnotation)
}

private fun isLowlightingAnnotation(entry: KtAnnotationEntry): Boolean {
    return entry.getCallNameExpression()
        ?.resolve()
        ?.getKotlinFqName()?.let { kotlinFqn ->
            storedAnnotationsFqn.any { fqn ->
                FqName(fqn) == kotlinFqn
            }
        } ?: false
}
