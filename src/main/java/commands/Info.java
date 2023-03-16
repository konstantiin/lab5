package commands;

import Managers.CollectionManager;
import commands.interfaces.AbstractCommand;
import commands.interfaces.Command;
import reading.Reader;

public class Info extends AbstractCommand {

    public Info(Reader reader){
        super( reader);
    }
    @Override
    public void execute(){
        System.out.println(collection);
    }

}
