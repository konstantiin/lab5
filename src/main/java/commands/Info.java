package commands;

import commands.interfaces.Command;
import reading.Reader;

public class Info extends Command {

    public Info(Reader reader){
        super( reader);
    }
    @Override
    public void execute(){
        collection.info();
    }

}
