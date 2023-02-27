package commands;

import commands.interfaces.Command;

public class Info implements Command {
    private Iterable<?> collection = null;
    public Info(Iterable<?> col){
        collection = col;
    }
    @Override
    public void execute(){
        System.out.println(collection);
    }

}
