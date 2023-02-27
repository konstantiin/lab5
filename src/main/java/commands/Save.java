package commands;

import commands.interfaces.Command;

public class Save implements Command {
    private final Iterable<?> collection;
    public Save(Iterable<?> col){
        collection = col;
    }

    @Override
    public void execute() {

    }
}
