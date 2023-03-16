package commands;

import commands.interfaces.Command;
import reading.Reader;

public class Clear extends Command {
    public Clear( Reader reader){
        super( reader);
    }
    @Override
    public void execute(){
        collection.clear();
        System.out.println("collection cleared");
    }

}
