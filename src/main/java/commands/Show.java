package commands;


import commands.interfaces.Command;

import reading.Reader;

public class Show extends Command {
    public Show(Reader reader){
        super(reader);
    }
    @Override
    public void execute() {
        collection.show();
    }

}
