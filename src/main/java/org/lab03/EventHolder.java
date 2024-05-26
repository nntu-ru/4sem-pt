package org.lab03;

import java.util.LinkedList;

public class EventHolder {
    private final LinkedList<Event> queue = new LinkedList<>();

    public synchronized void set(Event event) {
        queue.push(event);
        notify();
    }

    public synchronized Event get() throws InterruptedException {
        if (queue.isEmpty()) {
            wait();
        }

        return queue.poll();
    }
}
