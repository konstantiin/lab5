package commands.concreteCommands;

import commands.abstraction.Command;

public class Help extends Command {
    @Override
    public void execute() {
        System.out.println("Help"); // insert documentation
    }
}
