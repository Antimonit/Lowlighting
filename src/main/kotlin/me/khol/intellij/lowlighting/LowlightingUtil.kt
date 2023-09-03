package me.khol.intellij.lowlighting

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiManager
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.PsiTreeUtil
import me.khol.intellij.lowlighting.language.psi.LowlightingRecord

fun Project.findLowlightingProperties(key: String?): List<LowlightingRecord> =
    findLowlightingProperties()
        .filter { key.equals(it.key.name) }

fun Project.findLowlightingProperties(): List<LowlightingRecord> =
    FileTypeIndex.getFiles(LowlightingFileType.INSTANCE, GlobalSearchScope.allScope(this)).flatMap { virtualFile ->
        PsiManager.getInstance(this).findFile(virtualFile)?.let { psiFile ->
            PsiTreeUtil.getChildrenOfTypeAsList(psiFile, LowlightingRecord::class.java)
        } ?: emptyList()
    }

fun Project.findLowlightingPropertiesNames(): List<LowlightingRecord> =
    findLowlightingProperties()
        .filter { it.key.name.isNotEmpty() }
