package io.github.monthalcantara.core.command;

import java.util.HashMap;

public class CommandContext extends HashMap<String, Object> {

    private final String DATA = "data";
    private final String RESULT = "result";

    public CommandContext(Object data) {
        super.put(DATA, data);
    }

    public Object getData(Object data) {
        return super.get(DATA);
    }
}
