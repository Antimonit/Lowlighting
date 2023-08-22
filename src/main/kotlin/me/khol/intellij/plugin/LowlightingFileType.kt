package me.khol.intellij.plugin

import com.intellij.openapi.fileTypes.LanguageFileType
import me.khol.intellij.plugin.language.LowlightingLanguage

class LowlightingFileType : LanguageFileType(LowlightingLanguage) {

    override fun getName() = "Lowlighting File"
    override fun getDescription() = "Lowlighting configuration file"
    override fun getDefaultExtension() = "lowlighting"
    override fun getIcon() = LowlightingIcons.FILE

    companion object {
        val INSTANCE = LowlightingFileType()
    }
}
