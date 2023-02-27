package commands;

import commands.interfaces.Command;

public class AddIfMin implements Command {
    private final Iterable<?> collection;
    public AddIfMin(Iterable<?> col){
        collection = col;
    }

    @Override
    public void execute() {

    }
}
