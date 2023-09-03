package com.example.lowlighting

class KotlinUsingKotlinProperty {

    @KotlinLowlight
    val property = false

    init {
        <weak_warning descr="Lowlight">property</weak_warning>
    }
}
