package me.khol

import java.time.LocalDate

data class Release(
    val version: SemVer,
    val date: LocalDate,
    val notes: List<String>,
)
