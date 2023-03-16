package commands;

import Managers.CollectionManager;
import commands.interfaces.AbstractCommand;
import commands.interfaces.Command;
import reading.Reader;

public class Help extends AbstractCommand {
    @Override
    public void execute() {
        System.out.println("Help"); // insert documentation
    }
}
