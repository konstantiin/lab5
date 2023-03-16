package commands;

import Managers.CollectionManager;
import commands.interfaces.AbstractCommand;
import commands.interfaces.Command;
import reading.Reader;

public class AddIfMin extends AbstractCommand {

    public AddIfMin(Reader reader){
        super( reader);
    }
    @Override
    public void execute() {
        collection.addIfMin(input.readObject());
    }
}
