package commands;


import Managers.CollectionManager;
import commands.interfaces.AbstractCommand;
import commands.interfaces.Command;
import reading.Reader;

public class RemoveGreater extends AbstractCommand {
    public RemoveGreater(Reader reader){
        super(reader);
    }
    @Override
    public void execute(){
        collection.removeGreater(input.readObject());
    }
}
