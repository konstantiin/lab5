package commands.concreteCommands;

import commands.abstraction.Command;
import reading.readers.Reader;

public class AddIfMin extends Command {

    public AddIfMin(Reader Reader) {
        super(Reader);
    }

    @Override
    public void execute() {
        if (collection.addIfMin(input.readObject())) {
            System.out.println("element added");
        } else System.out.println("element not added");
    }
    @Override
    public String toString() {
        String res = "add_if_min" ;
        if (ExecuteScript.currentScripts.size() != 0) {
            res += "(in " + ExecuteScript.currentScripts.get(ExecuteScript.currentScripts.size()-1) + " script)";
        }
        return res;
    }
}