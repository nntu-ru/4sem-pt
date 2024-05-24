package org.lab03.unused.thread;

public class InfinityLoop extends Thread {
    public void run() {
        int i = 0;
        while(true) {
            System.out.println("Infinity loop: " + i++);

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
