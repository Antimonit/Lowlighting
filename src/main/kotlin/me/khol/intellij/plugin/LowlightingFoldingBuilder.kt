package me.khol.intellij.plugin

import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiCall
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiExpressionList
import com.intellij.psi.util.PsiTreeUtil
import org.jetbrains.kotlin.psi.KtCallElement
import org.jetbrains.kotlin.psi.KtValueArgumentList

class LowlightingFoldingBuilder : FoldingBuilderEx() {

    override fun buildFoldRegions(root: PsiElement, document: Document, quick: Boolean) =
        root.foldingDescriptors().toTypedArray()

    override fun getPlaceholderText(node: ASTNode) = node.psi.getPlaceholderText()

    override fun isCollapsedByDefault(node: ASTNode) = true
}

// Group of folding regions that will expand/collapse together.
private val FOLDING_GROUP = FoldingGroup.newGroup("Lowlighting Folding Group")

private fun PsiElement.foldingDescriptors(): List<FoldingDescriptor> {
    return kotlinFoldingDescriptors(this) + javaFoldingDescriptors(this)
}

private fun javaFoldingDescriptors(root: PsiElement): List<FoldingDescriptor> =
    PsiTreeUtil.findChildrenOfType(root, PsiCall::class.java).mapNotNull {
        if (it.lowlightingSeverities().isNotEmpty()) {
            it.argumentList?.toFoldingDescriptor()
        } else {
            null
        }
    }

private fun kotlinFoldingDescriptors(root: PsiElement): List<FoldingDescriptor> =
    PsiTreeUtil.findChildrenOfType(root, KtCallElement::class.java).mapNotNull {
        if (it.lowlightingSeverities().isNotEmpty()) {
            it.valueArgumentList?.toFoldingDescriptor()
        } else {
            null
        }
    }

private fun PsiElement.toFoldingDescriptor(): FoldingDescriptor? {
    val textRange = TextRange(textRange.startOffset + 1, textRange.endOffset - 1)
    if (textRange.isEmpty) return null
    return FoldingDescriptor(node, textRange, FOLDING_GROUP)
}

private fun PsiElement.getPlaceholderText(): String? = when (this) {
    is PsiExpressionList -> argumentCount(expressionCount)
    is KtValueArgumentList -> argumentCount(arguments.size)
    else -> "..."
}

private fun argumentCount(count: Int): String = when (count) {
    1 -> "$count argument"
    else -> "$count arguments"
}
