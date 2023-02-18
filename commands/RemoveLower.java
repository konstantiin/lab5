package commands;

import commands.interfaces.Command;

public class RemoveLower implements Command {
    private final Iterable<?> collection;
    public RemoveLower(Iterable<?> col){
        collection = col;
    }

}
