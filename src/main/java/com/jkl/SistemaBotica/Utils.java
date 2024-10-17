package com.jkl.SistemaBotica;

import java.util.Optional;
import java.util.function.Consumer;

public class Utils {
    public static <T> void updateField(T newValue, Consumer<T> setter) {
        Optional.ofNullable(newValue).ifPresent(setter);
    }
}
