package me.khol.intellij.plugin

import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.guessProjectDir
import com.intellij.openapi.util.io.systemIndependentPath
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.VirtualFileManager
import com.intellij.openapi.vfs.newvfs.BulkFileListener
import com.intellij.openapi.vfs.newvfs.events.VFileEvent
import com.intellij.openapi.vfs.newvfs.events.VFileMoveEvent
import com.intellij.openapi.vfs.newvfs.events.VFilePropertyChangeEvent
import java.io.File

/**
 * TODO: Implement [com.intellij.openapi.Disposable]
 * TODO: Consider using [com.intellij.openapi.vfs.AsyncFileListener] instead
 * TODO: Make config file configurable by user
 * TODO: Observe changes directly in the file without waiting for save
 */
@Service
class LowlightingService(project: Project) {

    var annotations: List<String> = emptyList()
        private set

    private val ioFile = File("${project.basePath}/.lowlighting")
    private val path = ioFile.systemIndependentPath

    init {
        project.messageBus
            .connect()
            .subscribe(VirtualFileManager.VFS_CHANGES, object : BulkFileListener {
                override fun after(events: List<VFileEvent>) {
                    if (events.any { it.isLowlighting(path) }) {
                        updateAnnotations()
                    }
                }
            })

        updateAnnotations()
    }

    private fun VFileEvent.isLowlighting(path: String) = when (this) {
        is VFilePropertyChangeEvent -> oldPath == path || newPath == path
        is VFileMoveEvent -> oldPath == path || newPath == path
        else -> path == path
    }

    private fun updateAnnotations() {
        val virtualFile = LocalFileSystem.getInstance().findFileByIoFile(ioFile)
        annotations = virtualFile?.read() ?: emptyList()
    }

    private fun VirtualFile.read(): List<String> =
        // TODO: Check differences `LoadTextUtil.loadText(this)`
        VfsUtil.loadText(this)
            .split(System.lineSeparator())
            .filter(String::isNotBlank)
}
