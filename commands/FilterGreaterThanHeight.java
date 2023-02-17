package commands;

import commands.interfaces.Command;

public class FilterGreaterThanHeight implements Command {
    private final Iterable<?> collection;
    public FilterGreaterThanHeight(Iterable<?> col){
        collection = col;
    }
    @Override
    public String getName(){
        return "filter_greater_than_height";
    }
}
