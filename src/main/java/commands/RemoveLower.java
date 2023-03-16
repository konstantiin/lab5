package commands;

import Managers.CollectionManager;
import commands.interfaces.AbstractCommand;
import commands.interfaces.Command;
import reading.Reader;

public class RemoveLower extends AbstractCommand {

    public RemoveLower(Reader reader){
        super(reader);
    }

    @Override
    public void execute() {
        collection.removeLower(input.readObject());
    }
}
