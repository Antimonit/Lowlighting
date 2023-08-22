package me.khol.intellij.plugin.language.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider
import me.khol.intellij.plugin.LowlightingFileType
import me.khol.intellij.plugin.language.LowlightingLanguage

/**
 * The top-level node in the PSI tree for a Lowlighting file.
 */
class LowlightingFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, LowlightingLanguage) {

    override fun getFileType() = LowlightingFileType.INSTANCE
    override fun toString() = "Lowlighting File"
}
