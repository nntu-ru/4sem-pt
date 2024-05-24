package org.lab03;

public class EventHolder {
    private Event event;

    public void set(Event event) {
        synchronized (this) {
            this.event = event;
            notifyAll();
        }
    }

    public Event get() throws InterruptedException {
        synchronized (this) {
            if (event == null) {
                wait();
            }
        }

        Event res = event;
        event = null;
        return res;
    }
}
