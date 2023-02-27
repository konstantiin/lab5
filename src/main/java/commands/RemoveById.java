package commands;

import commands.interfaces.Command;

public class RemoveById implements Command {
    private final Iterable<?> collection;
    public RemoveById(Iterable<?> col){
        collection = col;
    }

    @Override
    public void execute() {

    }
}
