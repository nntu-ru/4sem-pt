package commander;

import commander.iface.ICallback;

public class Command {
    protected String[] args;
    protected ICallback cb;

    public Command(String[] args) {
        this.args = args;
    }

    public Command(String[] args, ICallback cb) {
        this.args = args;
        this.cb = cb;
    }

    public int argc() {
        return args.length;
    }

    public String arg(int pos) {
        return argc() > pos ? args[pos] : "";
    }

    public String name() {
        return arg(0);
    }

    public boolean found() {
        return cb != null;
    }

    public void execute() {
        if (found()) {
            cb.execute(this);
        }
    }
}
