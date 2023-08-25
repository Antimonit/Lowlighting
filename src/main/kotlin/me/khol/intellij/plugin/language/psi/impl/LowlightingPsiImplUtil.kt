@file:JvmName("LowlightingPsiImplUtil")
@file:Suppress("EXTENSION_SHADOWED_BY_MEMBER")

package me.khol.intellij.plugin.language.psi.impl

import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiElement
import me.khol.intellij.plugin.LowlightingIcons
import me.khol.intellij.plugin.language.psi.*
import me.khol.intellij.plugin.language.psi.LowlightingElementFactory.createProperty

fun LowlightingKey.getName(): String? {
    val keyNode = node.findChildByType(LowlightingTypes.KEY_TOKEN)
    // TODO: Check why we need to replace regex
    return keyNode?.text //?.replace(Regex("\\\\ "), " ")
}

fun LowlightingKey.setName(newName: String?): PsiElement {
    val keyNode = node.findChildByType(LowlightingTypes.KEY_TOKEN)
    if (keyNode != null) {
        val property = createProperty(project, newName)
        val newKeyNode = property.firstChild.node
        node.replaceChild(keyNode, newKeyNode)
    }
    return this
}

fun LowlightingKey.getNameIdentifier(): PsiElement? {
    val keyNode = node.findChildByType(LowlightingTypes.KEY_TOKEN)
    return keyNode?.psi
}


fun LowlightingSeverity.getName(): String? {
    val keyNode = node.findChildByType(LowlightingTypes.SEVERITY_TOKEN)
    // TODO: Check why we need to replace regex
    return keyNode?.text //?.replace(Regex("\\\\ "), " ")
}

fun LowlightingSeverity.setName(newName: String?): PsiElement {
    val keyNode = node.findChildByType(LowlightingTypes.SEVERITY_TOKEN)
    if (keyNode != null) {
        val property = createProperty(project, newName)
        val newKeyNode = property.firstChild.node
        node.replaceChild(keyNode, newKeyNode)
    }
    return this
}

fun LowlightingSeverity.getNameIdentifier(): PsiElement? {
    val keyNode = node.findChildByType(LowlightingTypes.SEVERITY_TOKEN)
    return keyNode?.psi
}


fun LowlightingKey.getPresentation() = object : ItemPresentation {
    override fun getPresentableText() = "element.getKey()"
    override fun getLocationString() = "element.containingFile?.name"
    override fun getIcon(unused: Boolean) = LowlightingIcons.FILE
}
