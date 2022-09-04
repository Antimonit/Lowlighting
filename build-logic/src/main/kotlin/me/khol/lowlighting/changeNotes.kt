package me.khol.lowlighting

import me.khol.ChangeNotes
import me.khol.Release
import me.khol.SemVer
import java.time.LocalDate
import java.time.Month

val changeNotes = ChangeNotes(
    releases = listOf(
        Release(
            version = SemVer(1, 3, 4),
            date = LocalDate.of(2022, Month.AUGUST, 27),
            notes = listOf(
                "Fixed: Fix compatibility issues.",
            ),
        ),
        Release(
            version = SemVer(1, 3, 3),
            date = LocalDate.of(2022, Month.AUGUST, 21),
            notes = listOf(
                "Fixed: Do not attempt to fold an empty text range.",
            ),
        ),
        Release(
            version = SemVer(1, 3, 2),
            date = LocalDate.of(2022, Month.AUGUST, 20),
            notes = listOf(
                "Fixed: Do not use deprecated API.",
                "Fixed: Do not access indices before they are loaded.",
            ),
        ),
        Release(
            version = SemVer(1, 3, 1),
            date = LocalDate.of(2022, Month.MAY, 14),
            notes = listOf(
                "Fixed: Updated plugin to be compatible with IDEA 2021.1 up to IDEA 2023.1.",
            ),
        ),
        Release(
            version = SemVer(1, 3, 0),
            date = LocalDate.of(2022, Month.MAY, 14),
            notes = listOf(
                "Updated plugin to be compatible with IDEA 2021.1 and higher.",
            ),
        ),
        Release(
            version = SemVer(1, 2, 0),
            date = LocalDate.of(2020, Month.NOVEMBER, 8),
            notes = listOf(
                "Added proper lexer and parser for .lowlighting files with comments and with highlighting support.",
                "The .lowlighting file can now be placed anywhere in the project.",
                "Navigation support from .lowlighting file to class declaration.",
                "Line marker for class declarations defined in .lowlighting file.",
                "Code completion in .lowlighting file finds annotation declarations in the project.",
                "Fixed synchronization issue in .lowlighting.",
            ),
        ),
        Release(
            version = SemVer(1, 1, 1),
            date = LocalDate.of(2020, Month.NOVEMBER, 2),
            notes = listOf(
                "Added support for .lowlighting file type.",
            )
        ),
        Release(
            version = SemVer(1, 1, 0),
            date = LocalDate.of(2020, Month.NOVEMBER, 2),
            notes = listOf(
                "The plugin now works on a per-project basis. List of annotations to be lowlighted is now configured via .lowlighting file placed in the root directory of the project.",
            )
        ),
        Release(
            version = SemVer(1, 0, 1),
            date = LocalDate.of(2020, Month.OCTOBER, 21),
            notes = listOf(
                "Update the max supported version.",
            )
        ),
        Release(
            version = SemVer(1, 0, 0),
            date = LocalDate.of(2020, Month.OCTOBER, 14),
            notes = listOf(
                "The FQN of the annotation can be changed in Settings -> Tools -> Lowlighting.",
                "Annotated method calls are displayed with the same style as unused code.",
                "Annotated method calls can be all folded at once with IDE 'Collapse' shortcut.",
            )
        )
    )
)
