package com.example.lowlighting;

@SuppressWarnings("unused")
public class JavaClass extends JavaSuperclass {

    @MyDeprecated
    Boolean isFirst = false;

    @MyDeprecated
    Boolean isLast() {
        return false;
    }

    @MyDeprecated
    void log() {
    }

    void check() {
        if (isField)
            if (isMethod())
                if (isFirst)
                    if (isLast())
                        log();
    }
}
