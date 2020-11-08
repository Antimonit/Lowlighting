package me.khol.intellij.plugin

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import me.khol.intellij.plugin.LowlightingIcons.FILE
import org.jetbrains.kotlin.psi.KtClass

class LowlightingLineMarkerProvider : RelatedItemLineMarkerProvider() {

    override fun collectNavigationMarkers(
        element: PsiElement,
        result: MutableCollection<in RelatedItemLineMarkerInfo<*>>
    ) {
        element.checkAnnotations()?.let { result.add(it) }
    }

    private fun PsiElement.checkAnnotations() = when {
        this is KtClass && isAnnotation() -> markerInfo(fqName?.asString())
        this is PsiClass && isAnnotationType -> markerInfo(qualifiedName)
        else -> null
    }

    private fun PsiNameIdentifierOwner.markerInfo(
        qualifiedName: String?
    ): RelatedItemLineMarkerInfo<PsiElement>? {
        val nameIdentifier = nameIdentifier ?: return null
        if (qualifiedName.isNullOrBlank()) return null
        val properties = project.findLowlightingProperties(qualifiedName)
        if (properties.isEmpty()) return null
        return NavigationGutterIconBuilder.create(FILE)
            .setTargets(properties)
            .setTooltipText("Navigate to Lowlighting declaration")
            .createLineMarkerInfo(nameIdentifier)
    }
}
