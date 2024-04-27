package commander.util;

import java.util.ArrayList;

/**
 * Сборщик аргумента
 */
public class ArgBuilder {
    protected ArrayList<String> args = new ArrayList<>();
    protected StringBuilder builder = new StringBuilder();

    /**
     * Добавляет символ в аргумент
     */
    public void add(char c) {
        builder.append(c);
    }

    /**
     * Записывает аргумент в массив
     */
    public void push() {
        if (!builder.isEmpty()) {
            args.add(builder.toString());
            builder.setLength(0);
        }
    }

    /**
     * Возвращает массив аргументов
     */
    public String[] build() {
        return args.toArray(String[]::new);
    }
}
