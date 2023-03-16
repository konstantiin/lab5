package commands;

import Managers.CollectionManager;
import commands.interfaces.AbstractCommand;
import commands.interfaces.Command;
import reading.Reader;

public class RemoveById extends AbstractCommand {

    public RemoveById(Reader reader){
        super(reader);
    }

    @Override
    public void execute() {

    }
}
