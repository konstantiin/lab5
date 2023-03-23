package commands.concreteCommands;

import commands.abstraction.Command;

import static commands.launcher.CommandsLauncher.currentScripts;

public class Help extends Command {
    @Override
    public void execute() {
        System.out.println("Help"); // insert documentation
    }
    @Override
    public String toString() {
        String res = "help" ;
        if (currentScripts.size() != 0) {
            res += "(in " + currentScripts.get(currentScripts.size()-1) + " script)";
        }
        return res;
    }
}
