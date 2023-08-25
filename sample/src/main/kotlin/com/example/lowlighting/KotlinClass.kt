package com.example.lowlighting

@Suppress("unused")
class KotlinClass : KotlinSuperclass() {

    @MyDeprecated
    val isFirst: Boolean = false

    @MyDeprecated
    fun isLast(): Boolean = false

    @MyDeprecated
    fun log() = Unit

    fun check() {
        if (isField)
            if (isProperty)
                if (isMethod())
                    if (isFirst)
                        if (isLast())
                            log()
    }
}
