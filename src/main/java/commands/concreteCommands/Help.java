package commands.concreteCommands;

import commands.abstraction.Command;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static commands.launcher.CommandsLauncher.currentScripts;

public class Help extends Command {
    @Override
    public void execute() {
        try {
            Scanner help = new Scanner(new File("resources/help.txt"));
            while (help.hasNextLine()) System.out.println(help.nextLine());
        } catch (FileNotFoundException ignored) {}
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
