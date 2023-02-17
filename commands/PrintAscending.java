package commands;

import commands.interfaces.Command;

public class PrintAscending implements Command {
    private final Iterable<?> collection;
    public PrintAscending(Iterable<?> col){
        collection = col;
    }
}
