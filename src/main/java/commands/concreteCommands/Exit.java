package commands.concreteCommands;

import commands.abstraction.Command;

public class Exit extends Command {
    @Override
    public void execute() {
        System.exit(0);                 // говно
    }
    @Override
    public String toString() {
        String res = "exit" ;
        if (ExecuteScript.currentScripts.size() != 0) {
            res += "(in " + ExecuteScript.currentScripts.get(ExecuteScript.currentScripts.size()-1) + " script)";
        }
        return res;
    }
}
