package commands.concreteCommands;


import commands.abstraction.Command;
import reading.readers.Reader;

public class RemoveGreater extends Command {
    public RemoveGreater(Reader reader) {
        super(reader);
    }

    @Override
    public void execute() {
        collection.removeGreater(input.readObject());
        System.out.println("Elements removed");             // mb print amount of deleted elements
    }
}
