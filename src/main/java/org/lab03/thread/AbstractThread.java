package org.lab03.thread;

import org.springframework.context.support.ClassPathXmlApplicationContext;

abstract public class AbstractThread implements Runnable {
    protected final ClassPathXmlApplicationContext ctx;
    protected final String name;
    protected final long rate;

    public AbstractThread(ClassPathXmlApplicationContext ctx, String name, long rate) {
        this.ctx = ctx;
        this.name = name;
        this.rate = rate;
    }

    @Override
    public void run() {
        while (true) {
            try {
                tick();
                Thread.sleep(1000 / rate);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    abstract protected void tick() throws InterruptedException;
}
