package me.khol

import org.junit.jupiter.api.Test

import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.time.LocalDate
import java.time.Month

internal class ChangeNotesTest {

    @Test
    fun toHtml() {
        expectThat(
            ChangeNotes(
                releases = listOf(
                    Release(
                        version = SemVer(1, 1, 0),
                        date = LocalDate.of(2020, Month.NOVEMBER, 2),
                        notes = listOf(
                            "Note one.",
                        )
                    ),
                    Release(
                        version = SemVer(1, 0, 1),
                        date = LocalDate.of(2020, Month.OCTOBER, 21),
                        notes = listOf(
                            "Note one.",
                        )
                    ),
                    Release(
                        version = SemVer(1, 0, 0),
                        date = LocalDate.of(2020, Month.OCTOBER, 14),
                        notes = listOf(
                            "Note one.",
                            "Note two.",
                            "Note three.",
                        )
                    )
                )
            )
        ).get(ChangeNotes::toHtml).isEqualTo(
            """
            |<b>1.1.0 (2020-11-02)</b>
            |<ul>
            |    <li>Note one.</li>
            |</ul>
            |<b>1.0.1 (2020-10-21)</b>
            |<ul>
            |    <li>Note one.</li>
            |</ul>
            |<b>1.0.0 (2020-10-14)</b>
            |<ul>
            |    <li>Note one.</li>
            |    <li>Note two.</li>
            |    <li>Note three.</li>
            |</ul>
            |""".trimMargin()
        )
    }
}

