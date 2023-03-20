package commands;

import commands.interfaces.Command;

import reading.Reader;

public class AddIfMin extends Command {

    public AddIfMin(Reader Reader){
        super(Reader);
    }
    @Override
    public void execute() {
        if(collection.addIfMin(input.readObject())){
            System.out.println("element added");
        }
        else System.out.println("element not added");
    }
}
