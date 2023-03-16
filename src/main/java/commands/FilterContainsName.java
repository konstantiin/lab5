package commands;

import Managers.CollectionManager;
import commands.interfaces.AbstractCommand;
import commands.interfaces.Command;
import reading.Reader;

public class FilterContainsName extends AbstractCommand {

    public FilterContainsName(Reader reader){
        super( reader);
    }

    @Override
    public void execute() {

    }
}
