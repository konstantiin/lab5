package commands.concreteCommands;

import commands.abstraction.Command;
import reading.readers.Reader;

public class Save extends Command {
    public Save(Reader reader) {
        super(reader);
    }

    @Override
    public void execute() {
        collection.save();
    }
}
