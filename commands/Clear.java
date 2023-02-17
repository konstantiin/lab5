package commands;

import commands.interfaces.Command;

public class Clear implements Command {
    private final Iterable<?> collection;
    public Clear(Iterable<?> col){
        collection = col;
    }
}
