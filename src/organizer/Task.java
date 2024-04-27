package organizer;

import organizer.iface.ISetter;
import organizer.util.IOCollection;
import organizer.util.TimeFormatter;

import java.time.LocalDateTime;

/**
 * Базовый класс задания
 */
public class Task {
    // Имеет 3 поля: заголовок, дата начала и дата окончания
    protected String title;

    protected LocalDateTime start = LocalDateTime.MIN;
    protected LocalDateTime end = LocalDateTime.MIN;

    /**
     * Get/Set
     */
    public String getTitle() {
        return title;
    }

    public boolean setTitle(String title) {
        if (title.isEmpty()) {
            return false;
        }

        this.title = title;
        return true;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public boolean setStart(LocalDateTime start) {
        this.start = start;

        if (start.isAfter(end)) {
            end = start;
        }

        return true;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public boolean setEnd(LocalDateTime end) {
        if (!end.isBefore(start)) {
            this.end = end;
            return true;
        }
        return false;
    }

    /**
     * Быстрый просмотр
     */
    public String quickView() {
        return getTitle() + " (" + TimeFormatter.retrieve(getStart()) + " - " + TimeFormatter.retrieve(getEnd()) + ")";
    }

    /**
     * Детальный просмотр
     */
    public String detailedView() {
        return String.format("""
                Title: %s
                Time: (%s - %s)""", getTitle(), TimeFormatter.retrieve(getStart()), TimeFormatter.retrieve(getEnd()));
    }

    /**
     * Заполняет данные используя адаптер
     */
    public void fill(IOCollection io) {
        // Ввод заголовка
        enter(io, "Enter title", "Unable to set title.",
                this::setTitle);

        // Ввод даты начала
        enter(io, "Enter start (" + TimeFormatter.format + ")", "Please enter valid datetime.", line -> {
            LocalDateTime time;

            try {
                time = TimeFormatter.retrieve(line);
            } catch (Exception e) {
                return false;
            }

            return setStart(time);
        });

        // Ввод даты конца
        enter(io, "Enter end (" + TimeFormatter.format + ")", "Please enter valid datetime.", line -> {
            LocalDateTime time;

            try {
                time = TimeFormatter.retrieve(line);
            } catch (Exception e) {
                return false;
            }

            return setEnd(time);
        });
    }

    /**
     * Данная функция упрощает заполнение, требуя ввод до тех пор, пока он не будет верным.
     */
    protected void enter(IOCollection io, String welcome, String error, ISetter setter) {
        while (true) {
            io.out().print(welcome + ": ");

            if (setter.trySet(io.in().readLine())) {
                break;
            }

            io.out().error(error + " Please try again.");
        }
    }
}
