package me.khol.intellij.lowlighting

import com.intellij.testFramework.TestDataFile
import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase4
import org.junit.Test

/**
 * To resolve `Cannot resolve symbol` errors when testing highlighting we need to configure the following:
 * [How to test a JVM language?](https://plugins.jetbrains.com/docs/intellij/testing-faq.html#how-to-test-a-jvm-language)
 *
 * See also:
 * * [Testing Highlighting](https://plugins.jetbrains.com/docs/intellij/testing-highlighting.html)
 * * [Light and Heavy Tests](https://plugins.jetbrains.com/docs/intellij/light-and-heavy-tests.html)
 */
class LowlightingAnnotatorTest : LightJavaCodeInsightFixtureTestCase4(
    testDataPath = "src/test/testData",
) {

    private fun check(@TestDataFile filePath: String) {
        fixture.configureByFiles(
            filePath,
            "com/example/lowlighting/JavaLowlight.java",
            "com/example/lowlighting/KotlinLowlight.kt",
            "com/example/lowlighting/JavaSuperclass.java",
            "com/example/lowlighting/KotlinSuperclass.kt",
            ".lowlighting",
        )
        fixture.checkHighlighting(false, false, true, true)
    }

    @Test
    fun `Java using Java field`() = check("com/example/lowlighting/JavaUsingJavaField.java")

    @Test
    fun `Java using Java method`() = check("com/example/lowlighting/JavaUsingJavaMethod.java")

    @Test
    fun `Java using Kotlin field`() = check("com/example/lowlighting/JavaUsingKotlinField.java")

    @Test
    fun `Java using Kotlin method`() = check("com/example/lowlighting/JavaUsingKotlinMethod.java")

    @Test
    fun `Java overriding Java method`() = check("com/example/lowlighting/JavaOverridingJavaMethod.java")

    @Test
    fun `Java overriding Kotlin field`() = check("com/example/lowlighting/JavaOverridingKotlinField.java")

    @Test
    fun `Java overriding Kotlin method`() = check("com/example/lowlighting/JavaOverridingKotlinMethod.java")

    @Test
    fun `Kotlin using Java field`() = check("com/example/lowlighting/KotlinUsingJavaField.kt")

    @Test
    fun `Kotlin using Java method`() = check("com/example/lowlighting/KotlinUsingJavaMethod.kt")

    @Test
    fun `Kotlin using Kotlin property`() = check("com/example/lowlighting/KotlinUsingKotlinProperty.kt")

    @Test
    fun `Kotlin using Kotlin method`() = check("com/example/lowlighting/KotlinUsingKotlinMethod.kt")

    @Test
    fun `Kotlin overriding Java method`() = check("com/example/lowlighting/KotlinOverridingJavaMethod.kt")

    @Test
    fun `Kotlin overriding Kotlin method`() = check("com/example/lowlighting/KotlinOverridingKotlinMethod.kt")

    @Test
    fun `Kotlin overriding Kotlin property`() = check("com/example/lowlighting/KotlinOverridingKotlinProperty.kt")
}
