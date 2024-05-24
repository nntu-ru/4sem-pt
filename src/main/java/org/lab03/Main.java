package org.lab03;

import org.lab03.thread.Generator;
import org.lab03.thread.Reader;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("app.xml");

        Logger logger = ctx.getBean("logger", Logger.class);
        logger.subscribe(System.out::println);

        Thread th1 = new Thread(new Generator(ctx, 5));
        Thread th2 = new Thread(new Reader(ctx, 200));

        th1.start();
        th2.start();

        Thread.sleep(1000);

        th1.interrupt();
        th2.interrupt();

        ctx.close();
    }
}