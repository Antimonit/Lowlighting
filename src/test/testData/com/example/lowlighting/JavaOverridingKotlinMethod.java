package com.example.lowlighting;

public class JavaOverridingKotlinMethod extends KotlinSuperclass {

    @Override
    public boolean isMethod() {
        return false;
    }

    void check() {
        <weak_warning descr="Lowlight">isMethod()</weak_warning>;
    }
}
