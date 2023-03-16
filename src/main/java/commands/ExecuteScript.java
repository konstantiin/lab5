package commands;

import Managers.CollectionManager;
import commands.interfaces.AbstractCommand;
import commands.interfaces.Command;
import reading.Reader;

public class ExecuteScript extends AbstractCommand {

    public ExecuteScript(Reader reader){
        super(reader);
    }

    @Override
    public void execute() {

    }
}
