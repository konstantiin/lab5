package commands;


import Managers.CollectionManager;
import commands.interfaces.AbstractCommand;
import commands.interfaces.Command;
import reading.Reader;

public class Show extends AbstractCommand {
    public Show(Reader reader){
        super(reader);
    }
    @Override
    public void execute() {

    }

}
