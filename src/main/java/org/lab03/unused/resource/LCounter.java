package org.lab03.unused.resource;

import java.util.concurrent.locks.ReentrantLock;

public class LCounter extends Counter {
    private ReentrantLock mutex = new ReentrantLock();

    @Override
    public void inc() {
        mutex.lock();
        try {
            super.inc();
        } catch (Exception ignored) {
        } finally {
            mutex.unlock();
        }
    }

    @Override
    public void dec() {
        mutex.lock();
        try {
            super.dec();
        } catch (Exception ignored) {
        } finally {
            mutex.unlock();
        }
    }
}
