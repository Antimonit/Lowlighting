package me.khol.intellij.plugin

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiManager
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.PsiTreeUtil
import me.khol.intellij.plugin.language.psi.LowlightingProperty
import org.jetbrains.kotlin.name.FqName

fun Project.findProperties(key: String?): List<LowlightingProperty> =
    FileTypeIndex.getFiles(LowlightingFileType, GlobalSearchScope.allScope(this)).flatMap { virtualFile ->
        PsiManager.getInstance(this).findFile(virtualFile)?.let { psiFile ->
            PsiTreeUtil.getChildrenOfType(psiFile, LowlightingProperty::class.java)?.filter { property ->
                key.equals(property.name)
            }
        } ?: emptyList()
    }

fun Project.findProperties(): List<LowlightingProperty> =
    FileTypeIndex.getFiles(LowlightingFileType, GlobalSearchScope.allScope(this)).flatMap { virtualFile ->
        PsiManager.getInstance(this).findFile(virtualFile)?.let { psiFile ->
            PsiTreeUtil.getChildrenOfType(psiFile, LowlightingProperty::class.java)?.toList()
        } ?: emptyList()
    }
