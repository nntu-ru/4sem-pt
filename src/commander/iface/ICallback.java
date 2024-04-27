package commander.iface;

import commander.Command;

/**
 * Интерфейс обратного вызова команды
 */
public interface ICallback {
    void execute(Command command);
}
