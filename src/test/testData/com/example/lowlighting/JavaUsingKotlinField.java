package com.example.lowlighting;

public class JavaUsingKotlinField extends KotlinSuperclass {

    void check() {
        <weak_warning descr="Lowlight">isField()</weak_warning>;
    }
}
