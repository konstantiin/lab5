package commands.abstraction;

import commands.launcher.CommandsLauncher;
import reading.readers.Reader;

public abstract class Command {
    protected final CommandsLauncher<?> collection;
    protected final Reader input;

    public Command(Reader onlineReader) {
        collection = onlineReader.getCollection();
        input = onlineReader;
    }

    public Command() {
        collection = null;
        input = null;
    }

    public abstract void execute();
    //@Override
    //public abstract String toString();
}
