package com.example.lowlighting

class KotlinOverridingKotlinProperty : KotlinSuperclass() {

    override val isProperty: Boolean = false

    init {
        <weak_warning descr="Lowlight">isProperty</weak_warning>
    }
}
