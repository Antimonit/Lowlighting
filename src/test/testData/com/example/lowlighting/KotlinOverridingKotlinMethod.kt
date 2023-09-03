package com.example.lowlighting

class KotlinOverridingKotlinMethod : KotlinSuperclass() {

    override fun isMethod(): Boolean = false

    init {
        <weak_warning descr="Lowlight">isMethod()</weak_warning>
    }
}
