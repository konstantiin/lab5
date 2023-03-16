package commands;

import commands.interfaces.Command;
import reading.Reader;


public class Add extends Command {


    public Add( Reader reader){
        super(reader);
    }
    @Override
    public void execute(){
        collection.add(input.readObject());
        System.out.println("element added");
    }


}
