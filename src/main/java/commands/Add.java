package commands;

import Managers.CollectionManager;
import commands.interfaces.AbstractCommand;
import commands.interfaces.Command;
import reading.Reader;


public class Add extends AbstractCommand {


    public Add( Reader reader){
        super(reader);
    }
    @Override
    public void execute(){
        collection.add(input.readObject());
    }


}
