package org.lab03;

import org.lab03.thread.Generator;
import org.lab03.thread.Reader;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("app.xml");

        Logger logger = ctx.getBean("logger", Logger.class);
        logger.subscribe(System.out::println);

        Thread gen1 = new Thread(new Generator(ctx, "Generator 1", 10));

        Thread rd1 = new Thread(new Reader(ctx, "Reader 1", 200));
        Thread rd2 = new Thread(new Reader(ctx, "Reader 2", 200));

        gen1.start();

        rd1.start();
        rd2.start();

        Thread.sleep(1000);

        gen1.interrupt();

        rd1.interrupt();
        rd2.interrupt();

        ctx.close();
    }
}