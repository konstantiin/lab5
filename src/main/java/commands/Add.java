package commands;

import commands.interfaces.Command;

import reading.Reader;


public class Add extends Command {


    public Add( Reader Reader){
        super(Reader);
    }
    @Override
    public void execute(){
        collection.add(input.readObject());
        System.out.println("element added");
    }


}
