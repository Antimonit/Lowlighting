package com.example.lowlighting;

public class JavaOverridingKotlinField extends KotlinSuperclass {

    @Override
    public boolean isField() {
        return false;
    }

    void check() {
        <weak_warning descr="Lowlight">isField()</weak_warning>;
    }
}
