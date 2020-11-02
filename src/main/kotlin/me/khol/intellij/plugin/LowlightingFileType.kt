package me.khol.intellij.plugin

import com.intellij.openapi.fileTypes.LanguageFileType

object LowlightingFileType : LanguageFileType(LowlightingLanguage) {

    override fun getName() = "Lowlighting File"
    override fun getDescription() = "Lowlighting configuration file"
    override fun getDefaultExtension() = "lowlighting"
    override fun getIcon() = LowlightingIcons.FILE
}
