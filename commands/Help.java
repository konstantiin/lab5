package commands;

import commands.interfaces.Command;

public class Help implements Command {
    @Override
    public void execute() {
        System.out.println("Help"); // insert documentation
    }
    @Override
    public String getName(){
        return "help";
    }
}
