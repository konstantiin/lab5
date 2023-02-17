package commands;

import commands.interfaces.Command;

public class Add implements Command {
    private final Iterable<?> collection;
    public Add(Iterable<?> col){
        collection = col;
    }
    @Override
    public String getName(){
        return "add";
    }
}
