package commands.concreteCommands;

import commands.abstraction.Command;
import reading.readers.Reader;

public class RemoveLower extends Command {

    public RemoveLower(Reader reader) {
        super(reader);
    }

    @Override
    public void execute() {
        collection.removeLower(input.readObject()); // mb print amount of deleted elements
        System.out.println("Elements removed");
    }
}
