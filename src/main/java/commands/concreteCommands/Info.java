package commands.concreteCommands;

import commands.abstraction.Command;
import reading.readers.Reader;

public class Info extends Command {

    public Info(Reader reader) {
        super(reader);
    }

    @Override
    public void execute() {
        collection.info();
    }

}
