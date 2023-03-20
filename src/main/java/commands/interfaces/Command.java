package commands.interfaces;

import Managers.CollectionManager;
import reading.Reader;

public abstract class Command {
    protected final CollectionManager<?> collection;
    protected final Reader input;
    public Command(Reader onlineReader){
        collection = onlineReader.getCollection();
        input = onlineReader;
    }
    public Command(){
        collection = null;
        input = null;
    }
    public void execute(){
        throw new RuntimeException("not implemented");
    }
}
