package commands;

import commands.interfaces.Command;

import java.util.Collection;

public class Clear implements Command {
    private final Collection<?> collection;
    public Clear(Collection<?> col){
        collection = col;
    }
    @Override
    public void execute(){
        collection.clear();
    }
    @Override
    public String getName(){
        return "clear";
    }
}
