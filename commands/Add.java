package commands;

import commands.interfaces.Command;

import java.util.TreeSet;

public class Add implements Command {
    private final TreeSet<?> collection;

    public Add(TreeSet<?> col){
        collection = col;
    }
    @Override
    public void execute(){

    }
}
