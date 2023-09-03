package com.example.lowlighting

class KotlinUsingJavaMethod : JavaSuperclass() {

    init {
        <weak_warning descr="Lowlight">isMethod()</weak_warning>
    }
}
