package commands.concreteCommands;

import commands.abstraction.Command;

public class Exit extends Command {
    @Override
    public void execute() {
        System.exit(0);                 // говно
    }
}
