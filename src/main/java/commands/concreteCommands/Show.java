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
    @Override
    public String toString() {
        String res = "show" ;
        if (ExecuteScript.currentScripts.size() != 0) {
            res += "(in " + ExecuteScript.currentScripts.get(ExecuteScript.currentScripts.size()-1) + " script)";
        }
        return res;
    }
}
