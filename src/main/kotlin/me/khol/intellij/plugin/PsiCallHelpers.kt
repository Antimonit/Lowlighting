package me.khol.intellij.plugin

import com.intellij.openapi.project.Project
import com.intellij.psi.*
import me.khol.intellij.plugin.language.psi.LowlightingRecord
import org.jetbrains.kotlin.asJava.namedUnwrappedElement
import org.jetbrains.kotlin.descriptors.CallableDescriptor
import org.jetbrains.kotlin.idea.caches.resolve.analyze
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.resolve.calls.util.getResolvedCall
import org.jetbrains.uast.*

/**
 * Using UAST instead of PSI is generally preferable as it covers all Java-based languages.
 * UAST is generally great for method bodies, but there are some times when UAST is not enough,
 * such as resolving references.
 *
 * Because of design differences between Java (fields + getters) and Kotlin (properties), using
 * UAST might be more problematic that not using it. For example, it seems it is not possible
 * to retrieve annotations for a Kotlin property converted to a UElement, because it will be
 * represented either as a `UMethod` (for getter and setter) or as a `UField`, neither of which
 * can access the annotations of the underlying Kotlin property.
 *
 * For Kotlin, we can use [CallableDescriptor] which greatly simplify resolving.
 * For Java, we can use [PsiModifierListOwner] which contains list of annotations.
 * For Both, we can use [UResolvable], which is implemented by [UCallExpression] and [UReferenceExpression].
 */
private object Comments

val Project.lowlightingRecords: List<LowlightingRecord>
    get() = findLowlightingPropertiesNames()

/**
 * For Java methods.
 *
 * Searches for Lowlighting annotations on the declaration of a Java method call and all
 * super methods if the declaration is overriding.
 *
 * This also works when Java accesses a property declared in Kotlin because Kotlin automatically
 * generates getters and setters for Java, which are represented as plain method calls in Java.
 *
 * Note this is not invoked on Kotlin PSI, KtCallElement must be used instead.
 */
internal fun PsiCall.lowlightingSeverities(): Collection<LowlightingRecord> {
    val method = this.resolveMethod()
        ?.toUElement(UMethod::class.java) ?: return emptyList()
    val superMethods = method.javaPsi.findSuperMethods()
        .mapNotNull { it.toUElement(UMethod::class.java) }
    val uAnnotations = (superMethods + method)
        .flatMapTo(mutableSetOf()) { it.uAnnotations }
    return uAnnotations.filterLowlighting(project.lowlightingRecords)
}

/**
 * For Java fields.
 *
 * Note that unlike Java/Kotlin methods and Kotlin properties, Java fields cannot be overridden.
 */
internal fun PsiReferenceExpression.lowlightingSeverities(): Collection<LowlightingRecord> {
    val field = this.resolve()
        ?.toUElement(UField::class.java) ?: return emptyList()
    val uAnnotations = field.uAnnotations
    return uAnnotations.filterLowlighting(project.lowlightingRecords)
}

private fun Collection<UAnnotation>.filterLowlighting(records: List<LowlightingRecord>): Collection<LowlightingRecord> {
    val annotations = this.mapNotNull { it.qualifiedName }
    return records.filter { it.key.name in annotations }
}

/**
 * For Kotlin properties and methods.
 */
fun KtElement.lowlightingSeverities(): Collection<LowlightingRecord> {
    if (this !is KtCallElement && this !is KtReferenceExpression) return emptyList()

    val descriptor = this.getResolvedCall(this.analyze())
        ?.resultingDescriptor ?: return emptyList()
    val annotations = descriptor
        .allOverriddenDescriptors()
        .map { it.annotations }
        .filter { !it.isEmpty() }
    if (annotations.isEmpty())
        return emptyList()
    val records = project.findLowlightingPropertiesNames()
    return annotations
        .flatMapTo(mutableSetOf()) { annotationDescriptors ->
            records.filter {
                annotationDescriptors.hasAnnotation(FqName(it.key.name))
            }
        }
}

private fun CallableDescriptor.allOverriddenDescriptors(): Collection<CallableDescriptor> {
    return overriddenDescriptors.flatMap { it.allOverriddenDescriptors() } + this
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
