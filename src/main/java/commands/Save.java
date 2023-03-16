package commands;

import Managers.CollectionManager;
import commands.interfaces.AbstractCommand;
import commands.interfaces.Command;
import reading.Reader;

public class Save extends AbstractCommand {
    public Save(Reader reader){
        super( reader);
    }

    @Override
    public void execute() {

    }
}
