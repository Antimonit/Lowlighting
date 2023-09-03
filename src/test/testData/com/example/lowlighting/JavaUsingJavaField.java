package com.example.lowlighting;

public class JavaUsingJavaField extends JavaSuperclass {

    void check() {
        boolean field = <weak_warning descr="Lowlight">isField</weak_warning>;
    }
}
