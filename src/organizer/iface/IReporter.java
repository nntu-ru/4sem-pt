package organizer.iface;

public interface IReporter {
    void print(String message);
    void println(String message);

    void debug(String message);
    void error(String message);
    void warning(String message);
    void success(String message);
}
