package custom.tasks;

import organizer.Task;
import organizer.util.IOCollection;

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
        super.fill(io);

        enter(io, "Enter place", "Please enter a valid place.", this::setPlace);
    }
}
