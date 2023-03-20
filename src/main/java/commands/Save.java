package commands;

import commands.interfaces.Command;

import reading.Reader;

public class Save extends Command {
    public Save(Reader reader){
        super(reader);
    }

    @Override
    public void execute() {
        collection.save();
    }
}
