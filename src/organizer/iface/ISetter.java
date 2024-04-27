package organizer.iface;

/**
 * Интерфейс для функции organizer.Task::enter
 */
public interface ISetter {
    boolean trySet(String line);
}
