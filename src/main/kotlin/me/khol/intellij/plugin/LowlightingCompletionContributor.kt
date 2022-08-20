package me.khol.intellij.plugin

import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.ide.highlighter.JavaFileType
import com.intellij.openapi.fileTypes.FileType
import com.intellij.openapi.project.Project
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiManager
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.ProcessingContext
import me.khol.intellij.plugin.language.psi.LowlightingTypes
import org.jetbrains.kotlin.idea.KotlinFileType
import org.jetbrains.kotlin.psi.KtClass

class LowlightingCompletionContributor : CompletionContributor() {

    // TODO: Find a way to provide completion results only for the current module and its source sets.
    //  This was the closest scope: GlobalSearchScope.moduleWithDependentsScope(module)
    // TODO: Check existing contributors like [com.intellij.codeInsight.completion.JavaClassNameCompletionContributor]
    init {
        extend(
            CompletionType.BASIC,
            PlatformPatterns.psiElement(LowlightingTypes.KEY),
            object : CompletionProvider<CompletionParameters>() {
                public override fun addCompletions(
                    parameters: CompletionParameters,
                    context: ProcessingContext,
                    resultSet: CompletionResultSet
                ) {
                    val project = parameters.position.project
                    resultSet.addAllElements(
                        project.findAnnotations().map {
                            LookupElementBuilder.createWithSmartPointer(it.kotlinFqName?.asString() ?: "", it)
                        }
                    )
                }
            }
        )
    }
}

// TODO: We should also find annotation of other languages like Groovy, Scala, ...
private fun Project.findAnnotations() = findJavaAnnotations() + findKotlinAnnotations()

private fun Project.findJavaAnnotations() = abstractFindAnnotations(JavaFileType.INSTANCE, PsiClass::isAnnotationType)

private fun Project.findKotlinAnnotations() = abstractFindAnnotations(KotlinFileType.INSTANCE, KtClass::isAnnotation)

private inline fun <reified T : PsiElement> Project.abstractFindAnnotations(
    fileType: FileType,
    filter: T.() -> Boolean
) = FileTypeIndex.getFiles(fileType, GlobalSearchScope.projectScope(this)).flatMap { virtualFile ->
    PsiManager.getInstance(this).findFile(virtualFile)?.let { psiFile ->
        PsiTreeUtil.getChildrenOfType(psiFile, T::class.java)?.filter(filter)
    } ?: emptyList()
}


