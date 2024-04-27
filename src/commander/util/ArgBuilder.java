package commander.util;

import java.util.ArrayList;

public class ArgBuilder {
    protected ArrayList<String> args = new ArrayList<>();
    protected StringBuilder builder = new StringBuilder();

    public void add(char c) {
        builder.append(c);
    }

    public void push() {
        if (!builder.isEmpty()) {
            args.add(builder.toString());
            builder.setLength(0);
        }
    }

    public String[] build() {
        return args.toArray(String[]::new);
    }
}
