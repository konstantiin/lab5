package commands;

import commands.interfaces.Command;

public class AddIfMax implements Command {
    private final Iterable<?> collection;
    public AddIfMax(Iterable<?> col){
        collection = col;
    }
}
