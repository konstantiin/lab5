package commands.concreteCommands;

import StoredClasses.HumanBeing;
import commands.abstraction.Command;
import reading.readers.Reader;

public class FilterContainsName extends Command {

    public FilterContainsName(Reader reader) {
        super(reader);
    }

    @Override
    public void execute() {
        var list = collection.filterContainsName(input.readString());
        for (HumanBeing item : list) System.out.println(item);
        if (list.size() == 0) System.out.println("Nothing matches pattern");
    }
}
