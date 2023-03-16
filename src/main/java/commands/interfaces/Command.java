package commands.interfaces;

import Managers.CollectionManager;
import reading.Reader;

public abstract class Command {
    protected final CollectionManager<?> collection;
    protected final Reader input;
    public Command(Reader reader){
        collection = reader.getCollection();
        input = reader;
    }
    public Command(){
        collection = null;
        input = null;
    }
    public void execute(){
        throw new RuntimeException("not implemented");
    }
}
