@file:JvmName("LowlightingPsiImplUtil")
@file:Suppress("EXTENSION_SHADOWED_BY_MEMBER")

package me.khol.intellij.plugin.language.psi.impl

import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiElement
import me.khol.intellij.plugin.LowlightingIcons
import me.khol.intellij.plugin.language.psi.*
import me.khol.intellij.plugin.language.psi.LowlightingElementFactory.createProperty

private fun LowlightingKey.token(): ASTNode = node.findChildByType(LowlightingTypes.KEY_TOKEN)!!

fun LowlightingKey.getName(): String = token().text

fun LowlightingKey.setName(newName: String?): PsiElement {
    val keyNode = token()
    val property = createProperty(project, newName)
    val newKeyNode = property.firstChild.node
    node.replaceChild(keyNode, newKeyNode)
    return this
}

fun LowlightingKey.getNameIdentifier(): PsiElement = token().psi


private fun LowlightingSeverity.token(): ASTNode = node.findChildByType(LowlightingTypes.SEVERITY_TOKEN)!!

fun LowlightingSeverity.getName(): String = token().text

fun LowlightingSeverity.setName(newName: String?): PsiElement = apply {
    val keyNode = token()
    val property = createProperty(project, newName)
    val newKeyNode = property.firstChild.node
    node.replaceChild(keyNode, newKeyNode)
}

fun LowlightingSeverity.getNameIdentifier(): PsiElement = token().psi


fun LowlightingKey.getPresentation() = object : ItemPresentation {
    override fun getPresentableText() = "element.getKey()"
    override fun getLocationString() = "element.containingFile?.name"
    override fun getIcon(unused: Boolean) = LowlightingIcons.FILE
}
