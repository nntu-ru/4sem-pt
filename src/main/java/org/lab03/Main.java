package org.lab03;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("app.xml");

        Logger logger = ctx.getBean("logger", Logger.class);
        logger.subscribe(System.out::println);

        Event examEvent = ctx.getBean("testEvent", Event.class);
        logger.info(examEvent.toString());

        Event practiceEvent = ctx.getBean("practiceEvent", Event.class);
        logger.info(practiceEvent.toString());

        ctx.close();
    }
}