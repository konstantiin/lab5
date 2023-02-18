package commands;

import commands.interfaces.Command;

public class UpdateId implements Command {
    private final Iterable<?> collection;
    public UpdateId(Iterable<?> col){
        collection = col;
    }

}
