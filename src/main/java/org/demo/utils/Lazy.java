package org.demo.utils;

import java.util.function.Supplier;

public final class Lazy<T> {

    private final Supplier<T> supplier;

    private volatile T instance;

    public Lazy(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    public T get() {

        if (instance == null) {

            synchronized (this) {

                if (instance == null) {
                    instance = supplier.get();
                }
            }
        }

        return instance;
    }
}