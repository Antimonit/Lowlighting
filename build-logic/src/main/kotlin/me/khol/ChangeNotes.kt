package me.khol

data class ChangeNotes(
    val releases: List<Release>,
) {

    fun toHtml() = buildString {
        releases.forEach { release ->
            append("<b>")
            append(release.version)
            append(" ")
            append("(${release.date})")
            append("</b>")
            appendLine()
            appendLine("<ul>")
            release.notes.forEach { note ->
                append("    ")
                appendLine("<li>$note</li>")
            }
            appendLine("</ul>")
        }
    }
}
