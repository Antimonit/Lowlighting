package com.example.lowlighting

open class KotlinSuperclass {

    @MyDeprecated
    open val isProperty: Boolean = false

    @get:MyDeprecated
    open val isField: Boolean = false

    @MyDeprecated
    open fun isMethod(): Boolean = false
}
