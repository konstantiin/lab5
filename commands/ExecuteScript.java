package commands;

import commands.interfaces.Command;

public class ExecuteScript implements Command {
    @Override
    public String getName(){
        return "execute_script";
    }
}
