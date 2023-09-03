package com.example.lowlighting

open class KotlinSuperclass {

    @KotlinLowlight
    open val isProperty: Boolean = false

    @get:KotlinLowlight
    open val isField: Boolean = false

    @KotlinLowlight
    open fun isMethod(): Boolean = false
}
