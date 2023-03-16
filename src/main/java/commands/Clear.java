package commands;

import Managers.CollectionManager;
import commands.interfaces.AbstractCommand;
import commands.interfaces.Command;
import reading.Reader;

public class Clear extends AbstractCommand {
    public Clear( Reader reader){
        super( reader);
    }
    @Override
    public void execute(){
        collection.clear();
    }

}
