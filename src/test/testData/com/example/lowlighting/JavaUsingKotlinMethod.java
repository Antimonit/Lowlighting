package com.example.lowlighting;

public class JavaUsingKotlinMethod extends KotlinSuperclass {

    void check() {
        <weak_warning descr="Lowlight">isMethod()</weak_warning>;
    }
}
