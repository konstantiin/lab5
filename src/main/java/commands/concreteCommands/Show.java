package commands.concreteCommands;


import commands.abstraction.Command;
import reading.readers.Reader;

public class Show extends Command {
    public Show(Reader reader) {
        super(reader);
    }

    @Override
    public void execute() {
        collection.show();
    }

}
