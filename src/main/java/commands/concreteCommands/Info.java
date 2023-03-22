package commands.concreteCommands;

import commands.abstraction.Command;
import reading.readers.Reader;

public class Info extends Command {

    public Info(Reader reader) {
        super(reader);
    }

    @Override
    public void execute() {
        collection.info();
    }
    @Override
    public String toString() {
        String res = "info" ;
        if (ExecuteScript.currentScripts.size() != 0) {
            res += "(in " + ExecuteScript.currentScripts.get(ExecuteScript.currentScripts.size()-1) + " script)";
        }
        return res;
    }
}
