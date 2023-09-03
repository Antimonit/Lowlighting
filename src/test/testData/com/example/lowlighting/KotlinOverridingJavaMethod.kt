package com.example.lowlighting

class KotlinOverridingJavaMethod : JavaSuperclass() {

    override fun isMethod(): Boolean = false

    init {
        <weak_warning descr="Lowlight">isMethod()</weak_warning>
    }
}
