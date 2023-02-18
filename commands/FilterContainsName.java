package commands;

import commands.interfaces.Command;

public class FilterContainsName implements Command {
    private final Iterable<?> collection;
    public FilterContainsName(Iterable<?> col){
        collection = col;
    }
}
