package commands;

import Managers.CollectionManager;
import commands.interfaces.AbstractCommand;
import commands.interfaces.Command;
import reading.Reader;

public class Exit extends AbstractCommand {
    @Override
    public void execute(){
        System.exit(0); // говно
    }
}
