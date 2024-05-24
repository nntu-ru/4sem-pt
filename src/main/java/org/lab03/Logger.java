package org.lab03;

import org.lab03.iface.ILogger;
import org.lab03.iface.IPrinter;

import java.time.LocalDateTime;
import java.util.HashSet;

public class Logger implements ILogger {
    private String format = "${time}:${level}:${message}";
    private HashSet<IPrinter> subscribers = new HashSet<>();

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void subscribe(IPrinter printer) {
        subscribers.add(printer);
    }

    public void unsubscribe(IPrinter printer) {
        subscribers.remove(printer);
    }

    @Override
    public void debug(String message) {
        log("DEBUG", message);
    }

    @Override
    public void info(String message) {
        log("INFO", message);
    }

    @Override
    public void warning(String message) {
        log("WARNING", message);
    }

    private void log(String level, String message) {
        String formatted = getFormat();

        formatted = formatted.replace("${time}", LocalDateTime.now().toString());
        formatted = formatted.replace("${level}", level);
        formatted = formatted.replace("${message}", message);

        for (IPrinter printer : subscribers) {
            printer.handle(formatted);
        }
    }
}
