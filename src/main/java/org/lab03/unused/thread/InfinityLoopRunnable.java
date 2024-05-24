package org.lab03.unused.thread;

public class InfinityLoopRunnable implements Runnable {

    @Override
    public void run() {
        int i = 0;
        while(true) {
            System.out.println("Infinity loop (runnable): " + i++);

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
