package org.lab03.unused.thread;

import org.lab03.unused.resource.ICounter;

public class DecCounter extends Thread {
    private ICounter counter;

    public DecCounter(ICounter counter) {
        this.counter = counter;
    }

    public void run() {
        while (true) {
            counter.dec();

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
