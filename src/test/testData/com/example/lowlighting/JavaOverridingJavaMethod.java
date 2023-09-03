package com.example.lowlighting;

public class JavaOverridingJavaMethod extends JavaSuperclass {

    @Override
    public boolean isMethod() {
        return false;
    }

    void check() {
        <weak_warning descr="Lowlight">isMethod()</weak_warning>;
    }
}
