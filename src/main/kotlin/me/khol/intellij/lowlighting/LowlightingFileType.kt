package me.khol.intellij.lowlighting

import com.intellij.openapi.fileTypes.LanguageFileType
import me.khol.intellij.lowlighting.language.LowlightingLanguage

class LowlightingFileType : LanguageFileType(LowlightingLanguage) {

    override fun getName() = "Lowlighting File"
    override fun getDescription() = "Lowlighting configuration file"
    override fun getDefaultExtension() = "lowlighting"
    override fun getIcon() = LowlightingIcons.FILE

    companion object {
        val INSTANCE = LowlightingFileType()
    }
}
