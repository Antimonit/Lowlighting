package me.khol.intellij.plugin.language.psi

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFileFactory
import me.khol.intellij.plugin.LowlightingFileType

object LowlightingElementFactory {

    @JvmStatic
    fun createProperty(project: Project?, name: String?): LowlightingProperty {
        val file = createFile(project, name)
        return file.firstChild as LowlightingProperty
    }

    private fun createFile(project: Project?, text: String?): LowlightingFile {
        val name = "dummy.simple"
        return PsiFileFactory.getInstance(project)
            .createFileFromText(name, LowlightingFileType, text!!) as LowlightingFile
    }
}