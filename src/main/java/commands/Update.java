package commands;

import Managers.CollectionManager;
import commands.interfaces.AbstractCommand;
import commands.interfaces.Command;
import reading.Reader;

public class Update extends AbstractCommand {
    public Update( Reader reader){
        super(reader);
    }

    @Override
    public void execute() {

    }
}
