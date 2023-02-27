package commands;

import commands.interfaces.Command;

import java.util.TreeSet;


public class RemoveGreater implements Command {
    private final TreeSet<?> collection;
    public RemoveGreater(TreeSet<?> col){
        this.collection = col;
    }
    @Override
    public void execute(){
    }
}
