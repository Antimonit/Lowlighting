package me.khol.intellij.lowlighting.language.psi

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFileFactory
import me.khol.intellij.lowlighting.LowlightingFileType

object LowlightingElementFactory {

    @JvmStatic
    fun createProperty(project: Project?, name: String?): LowlightingKey {
        val file = createFile(project, name)
        return file.firstChild as LowlightingKey
    }

    private fun createFile(project: Project?, text: String?): LowlightingFile {
        val name = ".lowlighting"
        return PsiFileFactory.getInstance(project)
            .createFileFromText(name, LowlightingFileType.INSTANCE, text!!) as LowlightingFile
    }
}