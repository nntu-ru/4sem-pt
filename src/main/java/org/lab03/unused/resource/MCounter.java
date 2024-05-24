package org.lab03.unused.resource;

public class MCounter extends Counter {
    public void inc() {
        synchronized (this) {
            super.inc();
        }
    }

    public void dec() {
        synchronized (this) {
            super.dec();
        }
    }
}
