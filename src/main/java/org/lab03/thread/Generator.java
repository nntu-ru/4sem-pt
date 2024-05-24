package org.lab03.thread;

import org.lab03.Event;
import org.lab03.EventHolder;
import org.lab03.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDateTime;
import java.util.Random;

public class Generator extends AbstractThread {
    public Generator(ClassPathXmlApplicationContext ctx, long rate) {
        super(ctx, rate);
    }

    @Override
    protected void tick() {
        Random random = new Random();
        Logger logger = ctx.getBean("logger", Logger.class);
        EventHolder holder = ctx.getBean("holder", EventHolder.class);

        Event event = ctx.getBean("event", Event.class);

        event.setName("Зачет");
        event.setPlace("Ауд. " + random.nextInt(6999));
        event.setDateTime(LocalDateTime.now().plusDays(random.nextInt(20)));

        logger.info("Generator: " + event);

        holder.set(event);
    }
}
