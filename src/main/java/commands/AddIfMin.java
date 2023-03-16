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
        if(collection.addIfMin(input.readObject())){
            System.out.println("element added");
        }
        else System.out.println("element not added");
    }
}
