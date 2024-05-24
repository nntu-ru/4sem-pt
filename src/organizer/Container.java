package organizer;

import java.time.LocalDateTime;
import java.util.AbstractList;
import java.util.ArrayList;

/**
 * Контейнер для задач.
 * Представляет собой ArrayList, но при каждом добавлении элемента происходит сортировка по начальной дате задачи.
 */
public class Container extends AbstractList<Task> {
    protected ArrayList<Task> container = new ArrayList<>();

    @Override
    public void add(int index, Task task) {
        container.add(task);
        container.sort((o1, o2) -> {
            LocalDateTime t1 = o1.getStart();
            LocalDateTime t2 = o2.getStart();

            if (t1.isEqual(t2)) {
                 return 0;
            }

            return t1.isAfter(t2) ? 1 : -1;
        });
    }

    @Override
    public Task get(int index) {
        if (0 <= index && index < size()) {
            return container.get(index);
        }
        return null;
    }

    @Override
    public int size() {
        return container.size();
    }

    @Override
    public Task remove(int index) {
        return container.remove(index);
    }
}
