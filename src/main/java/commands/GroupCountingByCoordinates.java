package commands;

import Managers.CollectionManager;
import commands.interfaces.AbstractCommand;
import commands.interfaces.Command;
import reading.Reader;

public class GroupCountingByCoordinates extends AbstractCommand {

    public GroupCountingByCoordinates(Reader reader){
        super( reader);
    }
    @Override
    public void execute() {
        collection.groupCountingByCoordinates();
    }
}
