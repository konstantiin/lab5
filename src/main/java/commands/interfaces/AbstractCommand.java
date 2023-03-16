package commands.interfaces;

import Managers.CollectionManager;
import reading.Reader;

public abstract class AbstractCommand implements Command{
    protected final CollectionManager<?> collection;
    protected final Reader input;
    public AbstractCommand(Reader reader){
        collection = reader.getCollection();
        input = reader;
    }
    public AbstractCommand(){
        collection = null;
        input = null;
    }
}
