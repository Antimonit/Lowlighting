package me.khol.intellij.plugin

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider

class LowlightingFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, LowlightingLanguage) {

    override fun getFileType() = LowlightingFileType
    override fun toString() = "Lowlighting File"
}
