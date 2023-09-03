package com.example.lowlighting

class KotlinUsingKotlinMethod {

    @KotlinLowlight
    fun isMethod(): Boolean = false

    init {
        <weak_warning descr="Lowlight">isMethod()</weak_warning>
    }
}
