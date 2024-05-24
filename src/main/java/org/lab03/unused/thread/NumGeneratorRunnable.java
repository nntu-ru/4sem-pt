package org.lab03.unused.thread;

public class NumGeneratorRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; ++i) {
            System.out.println("Runnable: " + i);

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
