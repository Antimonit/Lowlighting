@file:JvmName("LowlightingPsiImplUtil")
@file:Suppress("EXTENSION_SHADOWED_BY_MEMBER")

package me.khol.intellij.plugin.language.psi.impl

import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiElement
import me.khol.intellij.plugin.LowlightingIcons
import me.khol.intellij.plugin.language.psi.LowlightingElementFactory.createProperty
import me.khol.intellij.plugin.language.psi.LowlightingProperty
import me.khol.intellij.plugin.language.psi.LowlightingTypes

fun LowlightingProperty.getName(): String? {
    val keyNode = node.findChildByType(LowlightingTypes.KEY)
    // TODO: Check why we need to replace regex
    return keyNode?.text //?.replace(Regex("\\\\ "), " ")
}

fun LowlightingProperty.setName(newName: String?): PsiElement {
    val keyNode = node.findChildByType(LowlightingTypes.KEY)
    if (keyNode != null) {
        val property = createProperty(project, newName)
        val newKeyNode = property.firstChild.node
        node.replaceChild(keyNode, newKeyNode)
    }
    return this
}

fun LowlightingProperty.getNameIdentifier(): PsiElement? {
    val keyNode = node.findChildByType(LowlightingTypes.KEY)
    return keyNode?.psi
}

fun LowlightingProperty.getPresentation() = object : ItemPresentation {
    override fun getPresentableText() = "element.getKey()"
    override fun getLocationString() = "element.containingFile?.name"
    override fun getIcon(unused: Boolean) = LowlightingIcons.FILE
}
