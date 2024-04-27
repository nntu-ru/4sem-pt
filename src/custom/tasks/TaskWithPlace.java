package custom.tasks;

import organizer.Task;
import organizer.util.IOCollection;

/**
 * Расширяем базовый класс задания, добавляя в него поле "место"
 */
public class TaskWithPlace extends Task {
    protected String place;

    public String getPlace() {
        return place;
    }

    public boolean setPlace(String place) {
        this.place = place;
        return true;
    }

    @Override
    public String detailedView() {
        return String.format("""
                %s
                Place: %s""", super.detailedView(), getPlace());
    }

    @Override
    public void fill(IOCollection io) {
        super.fill(io); // Заполняем поля из родительского класса

        // Просим ввести поле "место"
        enter(io, "Enter place", "Please enter a valid place.", this::setPlace);
    }
}
