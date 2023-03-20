package commands.concreteCommands;

import commands.abstraction.Command;
import reading.readers.Reader;

public class Clear extends Command {
    public Clear(Reader reader) {
        super(reader);
    }

    @Override
    public void execute() {
        collection.clear();
        System.out.println("collection cleared");
    }

}
