package org.lab03.unused.thread;

import org.lab03.unused.resource.ICounter;

public class IncCounter extends Thread {
    private ICounter counter;

    public IncCounter(ICounter counter) {
        this.counter = counter;
    }

    public void run() {
        while (true) {
            counter.inc();

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
