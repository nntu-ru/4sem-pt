package organizer.util;

import organizer.iface.IReader;
import organizer.iface.IReporter;

public class IOCollection {
    protected IReader reader;
    protected IReporter reporter;

    public IOCollection(IReader reader, IReporter reporter) {
        this.reader = reader;
        this.reporter = reporter;
    }

    public IReader in() {
        return reader;
    }

    public IReporter out() {
        return reporter;
    }
}
