package me.khol

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isGreaterThan
import strikt.assertions.isLessThan

class SemVerTest {

    @Test
    fun `compare major versions`() {
        expectThat(
            SemVer(1, 0, 0)
        ).isLessThan(
            SemVer(2, 0, 0)
        )
    }

    @Test
    fun `compare minor versions`() {
        expectThat(
            SemVer(0, 1, 0)
        ).isLessThan(
            SemVer(0, 2, 0)
        )
    }

    @Test
    fun `compare patch versions`() {
        expectThat(
            SemVer(0, 0, 1)
        ).isLessThan(
            SemVer(0, 0, 2)
        )
    }

    @Test
    fun `major is more important than minor`() {
        expectThat(
            SemVer(2, 1, 0)
        ).isGreaterThan(
            SemVer(1, 2, 0)
        )
    }

    @Test
    fun `minor is more important than patch`() {
        expectThat(
            SemVer(1, 2, 2)
        ).isGreaterThan(
            SemVer(1, 1, 1)
        )
    }

    @Test
    fun `versions are equal`() {
        val verOne = SemVer(1, 2, 3)
        val verTwo = SemVer(1, 2, 3)
        expectThat(verOne) {
            not().isLessThan(verTwo)
            not().isGreaterThan(verTwo)
            isEqualTo(verTwo)
        }
    }
}