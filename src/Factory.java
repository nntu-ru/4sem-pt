import custom.tasks.TaskWithPlace;
import organizer.Task;

import organizer.util.IOCollection;

public class Factory {
    protected IOCollection io;

    public Factory(IOCollection io) {
        this.io = io;
    }

    public Task create(String type) {
        Task task;

        if (type.isEmpty()) {
            type = "default";
        }

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

        task.fill(io);

        return task;
    }
}
