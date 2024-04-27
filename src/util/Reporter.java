package util;

import organizer.iface.IReporter;

import java.io.PrintStream;

/**
 * Адаптер вывода
 */
public class Reporter implements IReporter {
    protected PrintStream stream;

    public Reporter(PrintStream stream) {
        this.stream = stream;
    }

    @Override
    public void print(String message) {
        stream.print(Decorator.RESET + message);
    }

    @Override
    public void println(String message) {
        stream.println(Decorator.RESET + message);
    }

    @Override
    public void debug(String message) {
        stream.println(Decorator.BLUE + message + Decorator.RESET);
    }

    @Override
    public void error(String message) {
        stream.println(Decorator.RED + message + Decorator.RESET);
    }

    @Override
    public void warning(String message) {
        stream.println(Decorator.YELLOW + message + Decorator.RESET);
    }

    @Override
    public void success(String message) {
        stream.println(Decorator.GREEN + message + Decorator.RESET);
    }
}
