package org.lab03.unused.resource;

public class Counter implements ICounter {
    private int counter;

    @Override
    public void inc() {
        System.out.println(++counter);
    }

    @Override
    public void dec() {
        System.out.println(--counter);
    }
}
