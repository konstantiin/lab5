package commands;

import commands.interfaces.Command;

public class PrintUniqueNationality implements Command {
    private final Iterable<?> collection;
    public PrintUniqueNationality(Iterable<?> col){
        collection = col;
    }
}
