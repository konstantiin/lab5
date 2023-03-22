package commands.concreteCommands;

import commands.abstraction.Command;
import reading.readers.Reader;


public class Add extends Command {


    public Add(Reader Reader) {
        super(Reader);
    }

    @Override
    public void execute() {
        collection.add(input.readObject());
        System.out.println("element added");
    }

    @Override
    public String toString() {
        String res = "add" ;
        if (ExecuteScript.currentScripts.size() != 0) {
            res += "(in " + ExecuteScript.currentScripts.get(ExecuteScript.currentScripts.size()-1) + " script)";
        }
        return res;
    }


}
