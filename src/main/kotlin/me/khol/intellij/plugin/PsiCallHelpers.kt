package me.khol.intellij.plugin

import com.intellij.psi.*
import org.jetbrains.kotlin.asJava.namedUnwrappedElement
import org.jetbrains.kotlin.idea.references.KtSimpleNameReference
import org.jetbrains.kotlin.idea.references.mainReference
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.getCallNameExpression
import org.jetbrains.kotlin.utils.addToStdlib.firstIsInstanceOrNull

// TODO: Check also definition of methods, properties and fields in super classes

internal fun PsiCall.isLowlightingAnnotated(): Boolean {
    val method = resolveMethod() ?: return false
    return method.isLowLightingAnnotated()
}

internal fun PsiReferenceExpression.isLowlightingAnnotated(): Boolean {
    val resolved = this.resolve() as? PsiModifierListOwner ?: return false
    return resolved.isLowLightingAnnotated()
}

private fun PsiModifierListOwner.isLowLightingAnnotated(): Boolean {
    if (annotations.isEmpty()) return false
    val lowlighting = project.findLowlightingPropertiesNames()
    return annotations.any { annotation ->
        lowlighting.any { fqn ->
            fqn == annotation.qualifiedName
        }
    }
}

internal fun KtReferenceExpression.isLowlightingAnnotated(): Boolean {
    /*
    val first: Boolean = false
    if (first)

        CONDITION
          REFERENCE_EXPRESSION
            PsiElement(IDENTIFIER)
     */
    val reference = this.references.firstIsInstanceOrNull<KtSimpleNameReference>() ?: return false
    val namedDeclaration = reference.resolve() as? KtNamedDeclaration ?: return false
    val annotationEntries = namedDeclaration.annotationEntries
    if (annotationEntries.isEmpty()) return false
    val annotations = project.findLowlightingPropertiesNames()
    return annotationEntries.any { isLowlightingAnnotation(it, annotations) }
}

internal fun KtCallElement.isLowlightingAnnotated(): Boolean {
    /*
    fun last(): Boolean = false
    if (last())

        CONDITION
          CALL_EXPRESSION
            REFERENCE_EXPRESSION
              PsiElement(IDENTIFIER)
            VALUE_ARGUMENT_LIST
     */
    val referenceExpression = children.firstIsInstanceOrNull<KtReferenceExpression>() ?: return false
    return referenceExpression.isLowlightingAnnotated()
}

private fun isLowlightingAnnotation(entry: KtAnnotationEntry, annotations: List<String>): Boolean {
    return entry.getCallNameExpression()
        ?.mainReference
        ?.resolve()
        ?.kotlinFqName?.let { kotlinFqn ->
            annotations.any { fqn ->
                FqName(fqn) == kotlinFqn
            }
        } ?: false
}

val PsiElement.kotlinFqName: FqName?
    get() = when (val element = namedUnwrappedElement) {
        is PsiPackage -> FqName(element.qualifiedName)
        is PsiClass -> element.qualifiedName?.let(::FqName)
        is PsiMember -> element.getName()?.let { name ->
            val prefix = element.containingClass?.qualifiedName
            FqName(if (prefix != null) "$prefix.$name" else name)
        }
        is KtNamedDeclaration -> element.fqName
        else -> null
    }
