package commands;

import commands.interfaces.Command;

public class Exit implements Command {
    @Override
    public void execute(){
        System.exit(0);
    }
    @Override
    public String getName(){
        return "exit";
    }
}
