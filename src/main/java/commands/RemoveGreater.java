package commands;


import commands.interfaces.Command;

import reading.Reader;

public class RemoveGreater extends Command {
    public RemoveGreater(Reader reader){
        super(reader);
    }
    @Override
    public void execute(){
        collection.removeGreater(input.readObject());
        System.out.println("Elements removed");             // mb print amount of deleted elements
    }
}
