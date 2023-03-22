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
    @Override
    public String toString() {
        String res = "save" ;
        if (ExecuteScript.currentScripts.size() != 0) {
            res += "(in " + ExecuteScript.currentScripts.get(ExecuteScript.currentScripts.size()-1) + " script)";
        }
        return res;
    }
}
