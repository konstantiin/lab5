package commands.concreteCommands;

import commands.abstraction.Command;

public class Help extends Command {
    @Override
    public void execute() {
        System.out.println("Help"); // insert documentation
    }
    @Override
    public String toString() {
        String res = "help" ;
        if (ExecuteScript.currentScripts.size() != 0) {
            res += "(in " + ExecuteScript.currentScripts.get(ExecuteScript.currentScripts.size()-1) + " script)";
        }
        return res;
    }
}
