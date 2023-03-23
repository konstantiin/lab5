package commands.concreteCommands;

import commands.abstraction.Command;

import static commands.launcher.CommandsLauncher.currentScripts;

public class Exit extends Command {
    @Override
    public void execute() {
        System.exit(0);                 // говно
    }
    @Override
    public String toString() {
        String res = "exit" ;
        if (currentScripts.size() != 0) {
            res += "(in " + currentScripts.get(currentScripts.size()-1) + " script)";
        }
        return res;
    }
}
