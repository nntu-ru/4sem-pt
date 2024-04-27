import commander.Command;
import commander.Commander;

import organizer.Container;
import organizer.Task;
import organizer.util.IOCollection;
import util.Reader;
import util.Reporter;

public class App {
    protected IOCollection io = new IOCollection(new Reader(System.in), new Reporter(System.out));
    protected Factory factory = new Factory(io);

    protected Container container = new Container();
    protected Commander commander = new Commander();

    public App() {
        registerCommands();
    }

    public void run() {
        while (true) {
            io.out().print("organizer> ");

            String line = io.in().readLine();
            if (line.equals("exit")) {
                break;
            }

            Command command = commander.process(line);

            if (command == null || !command.found()) {
                io.out().error("Invalid command.");
                continue;
            }

            command.execute();

            io.out().println("");
        }

        io.out().println("Bye.");
    }

    protected void registerCommands() {
        commander.register("help", _ -> {
            io.out().println("""
                    Available commands:
                        help - View help
                        exit - Exit from application

                        create [type] - Create task
                        remove <pos> - Remove task
                        view [id] - View task(s)""");
        });

        commander.register("create", command -> {
            String type = command.arg(1);

            Task task = factory.create(type);
            if (task == null) {
                io.out().error("Unable to create task with type: " + type);
                return;
            }

            container.add(task);

            io.out().println("Task was successfully created.");
        });

        commander.register("remove", command -> {
            if (command.argc() < 2) {
                io.out().error("Usage: remove <id>");
                return;
            }

            int id;

            try {
                id = Integer.parseInt(command.arg(1));
            } catch (Exception e) {
                io.out().error("Argument <id> must be an integer value.");
                return;
            }

            Task task = container.get(id);
            if (task == null) {
                io.out().error("Task with id=" + id + " not found.");
                return;
            }

            container.remove(task);

            io.out().success("Task was removed.");
        });

        commander.register("view", command -> {
            if (container.isEmpty()) {
                io.out().warning("Please create tasks first.");
                return;
            }

            if (command.argc() > 1) {
                int id;

                try {
                    id = Integer.parseInt(command.arg(1));
                } catch (Exception e) {
                    io.out().error("Argument [id] must be an integer value.");
                    return;
                }

                Task task = container.get(id);
                if (task == null) {
                    io.out().error("Task with id=" + id + " not found.");
                    return;
                }

                io.out().print(container.get(id).detailedView());
                return;
            }

            for (int i = 0; i < container.size(); ++i) {
                io.out().println(i + ": " + container.get(i).quickView());
            }
        });
    }
}
