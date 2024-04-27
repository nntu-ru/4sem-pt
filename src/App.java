import commander.Command;
import commander.Commander;

import organizer.Container;
import organizer.Task;
import organizer.util.IOCollection;
import util.Reader;
import util.Reporter;

/**
 * Контроллер приложения
 */
public class App {
    protected IOCollection io = new IOCollection(new Reader(System.in), new Reporter(System.out));
    protected Factory factory = new Factory(io);

    protected Container container = new Container();
    protected Commander commander = new Commander();

    public App() {
        registerCommands();
    }

    /**
     * Функция делает эмуляцию терминала
     */
    public void run() {
        while (true) {
            io.out().print("organizer> ");

            String line = io.in().readLine();
            if (line.equals("exit")) { // Команда exit всегда имеет выше приоритет, чем остальные
                break;
            }

            // Пытаемся обработать введеную строку
            Command command = commander.process(line);

            // Если не удалось обработать или команда не найдена
            if (command == null || !command.found()) {
                io.out().error("Invalid command.");
                continue;
            }

            // Выполним команду
            command.execute();

            // Для того чтобы строки терминала не слились после выполнения, добавим еще одну строку
            io.out().println("");
        }

        io.out().println("Bye.");
    }

    /**
     * Регистрация команд
     */
    protected void registerCommands() {
        // help
        commander.register("help", _ -> {
            io.out().println("""
                    Available commands:
                        help - View help
                        exit - Exit from application

                        create [type] - Create task
                        remove <pos> - Remove task
                        view [id] - View task(s)""");
        });

        // create [type] (Создание)
        commander.register("create", command -> {
            String type = command.arg(1);

            Task task = factory.create(type);
            if (task == null) {
                io.out().error("Unable to create task with type: " + type);
                return;
            }

            // Запишем в контейнер
            container.add(task);

            io.out().println("Task was successfully created.");
        });

        // remove <id> (Удаление)
        commander.register("remove", command -> {
            // Отсечем ввод, где только 1 аргумент (т.к. команда требует как минимум 2 аргумента)
            if (command.argc() < 2) {
                io.out().error("Usage: remove <id>");
                return;
            }

            // Пытаемся извлечь integer из второго аргумента
            int id;

            try {
                id = Integer.parseInt(command.arg(1));
            } catch (Exception e) {
                io.out().error("Argument <id> must be an integer value.");
                return;
            }

            // Проверяем, существует ли задание с таким id
            Task task = container.get(id);
            if (task == null) {
                io.out().error("Task with id=" + id + " not found.");
                return;
            }

            // Удаляем
            container.remove(task);

            io.out().success("Task was removed.");
        });

        // view [id] (Просмотр)
        commander.register("view", command -> {
            // Завершим преждевременно, если заданий не существует.
            if (container.isEmpty()) {
                io.out().warning("Please create tasks first.");
                return;
            }

            // Если команда имеет 2 и более аргумента
            if (command.argc() > 1) {
                // Пытаемся извлечь id
                int id;

                try {
                    id = Integer.parseInt(command.arg(1));
                } catch (Exception e) {
                    io.out().error("Argument [id] must be an integer value.");
                    return;
                }

                // Проверим, существует ли такое задание
                Task task = container.get(id);
                if (task == null) {
                    io.out().error("Task with id=" + id + " not found.");
                    return;
                }

                // Просмотрим детально
                io.out().print(container.get(id).detailedView());
                return;
            }

            // Просмотрим кратко все
            for (int i = 0; i < container.size(); ++i) {
                io.out().println(i + ": " + container.get(i).quickView());
            }
        });
    }
}
