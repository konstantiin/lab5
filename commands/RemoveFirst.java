package commands;

import commands.interfaces.Command;

public class RemoveFirst implements Command {
    private final Iterable<?> collection;
    public RemoveFirst(Iterable<?> col){
        collection = col;
    }
    @Override
    public String getName(){
        return "remove_first";
    }
}
