package org.lab03.unused.thread;

public class NumGenerator extends Thread {
    public void run() {
        for (int i = 0; i < 100; ++i) {
            System.out.println("Thread: " + i);

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
