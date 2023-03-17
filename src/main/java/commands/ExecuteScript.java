package commands;

import Exceptions.UnknownCommandException;
import commands.interfaces.Command;
import reading.Reader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.io.File;
import java.util.List;
import java.util.Scanner;

public class ExecuteScript extends Command {
    public static List<File> currentScripts = new ArrayList<>();

    public ExecuteScript(Reader reader){
        super(reader);
    }

    @Override
    public void execute() {
        File script = new File(input.readString());
        currentScripts.add(script);
        try {
            Reader reader = new Reader(new Scanner(script), collection, input.getObjectTree());
            while (reader.hasNext()){
                Command met = null;
                try {
                    met = reader.readCommand();
                } catch (UnknownCommandException e) {
                    System.out.println("No such command: " + e.getMessage() + "command was skipped");
                }
                if(met != null) met.execute();
            }
        } catch (FileNotFoundException e) {
            System.out.println("file " + script + " does not exist, or can't be accessed");
        }
        finally {
            currentScripts.remove(script);
        }
    }
}
