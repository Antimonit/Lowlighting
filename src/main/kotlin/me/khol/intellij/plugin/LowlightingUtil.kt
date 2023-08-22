package me.khol.intellij.plugin

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiManager
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.PsiTreeUtil
import me.khol.intellij.plugin.language.psi.LowlightingProperty

fun Project.findLowlightingProperties(key: String?): List<LowlightingProperty> =
    findLowlightingProperties()
        .filter { key.equals(it.name) }

fun Project.findLowlightingProperties(): List<LowlightingProperty> =
    FileTypeIndex.getFiles(LowlightingFileType.INSTANCE, GlobalSearchScope.allScope(this)).flatMap { virtualFile ->
        PsiManager.getInstance(this).findFile(virtualFile)?.let { psiFile ->
            PsiTreeUtil.getChildrenOfType(psiFile, LowlightingProperty::class.java)?.toList()
        } ?: emptyList()
    }

fun Project.findLowlightingPropertiesNames(): List<String> =
    findLowlightingProperties()
        .mapNotNull { it.name }
        .filter { it.isNotEmpty() }
