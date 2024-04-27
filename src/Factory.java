import custom.tasks.TaskWithPlace;
import organizer.Task;

import organizer.util.IOCollection;

/**
 * Фабрика представляет собой таблицу, где вместо имени класса используется строковый идентификатор.
 */
public class Factory {
    protected IOCollection io;

    public Factory(IOCollection io) {
        this.io = io;
    }

    public Task create(String type) {
        Task task;

        // Если тип пустой, то установим его на базовый класс task
        if (type.isEmpty()) {
            type = "default";
        }

        // Выберем по типу
        switch (type) {
            case "default":
                task = new Task();
                break;
            case "place":
                task = new TaskWithPlace();
                break;
            default:
                return null;
        }

        // Заполним
        task.fill(io);

        return task;
    }
}
