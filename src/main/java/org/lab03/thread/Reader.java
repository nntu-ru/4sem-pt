package org.lab03.thread;

import org.lab03.Event;
import org.lab03.EventHolder;
import org.lab03.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Reader extends AbstractThread {

    public Reader(ClassPathXmlApplicationContext ctx, String name, long rate) {
        super(ctx, name, rate);
    }

    @Override
    protected void tick() throws InterruptedException {
        Logger logger = ctx.getBean("logger", Logger.class);
        EventHolder holder = ctx.getBean("holder", EventHolder.class);

        Event event = holder.get();

        if (event == null) {
            logger.warning(name + ": event is null");
            return;
        }

        logger.info(name + ": " + event);
    }
}
