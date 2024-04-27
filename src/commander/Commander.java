package commander;

import commander.iface.ICallback;
import commander.util.ArgBuilder;

import java.util.HashMap;

public class Commander {
    protected HashMap<String, ICallback> commands = new HashMap<>();

    public void register(String name, ICallback cb) {
        commands.put(name, cb);
    }

    public void unregister(String name) {
        commands.remove(name);
    }

    public boolean isRegistered(String name) {
        return commands.containsKey(name);
    }

    public Command process(String line) {
        if (line.isEmpty()) {
            return null;
        }

        boolean in_scope = false;
        ArgBuilder builder = new ArgBuilder();

        for (char c : line.toCharArray()) {
            if (c == '"') {
                in_scope = !in_scope;
                continue;
            }

            if (c == ' ' && !in_scope) {
                builder.push();
                continue;
            }

            builder.add(c);
        }

        builder.push();

        String[] args = builder.build();

        if (args.length == 0) {
            return null;
        }

        if (!commands.containsKey(args[0])) {
            return new Command(args);
        }

        return new Command(args, commands.get(args[0]));
    }
}
