package commander;

import commander.iface.ICallback;

/**
 * Оболочка над аргументами для безопасного использования
 */
public class Command {
    /**
     * Аргумента
     */
    protected String[] args;

    /**
     * Обратный вызов
     */
    protected ICallback cb;

    public Command(String[] args) {
        this.args = args;
    }

    public Command(String[] args, ICallback cb) {
        this.args = args;
        this.cb = cb;
    }

    /**
     * Кол-во аргументов
     */
    public int argc() {
        return args.length;
    }

    /**
     * Получить аргумент по индексу
     */
    public String arg(int pos) {
        return argc() > pos ? args[pos] : "";
    }

    /**
     * Имя команды (нулевой аргумент)
     */
    public String name() {
        return arg(0);
    }

    /**
     * Найдена ли команда
     */
    public boolean found() {
        return cb != null;
    }

    /**
     * Выполняет обратный вызов
     */
    public void execute() {
        if (found()) {
            cb.execute(this);
        }
    }
}
