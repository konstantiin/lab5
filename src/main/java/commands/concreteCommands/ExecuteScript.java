package commands.concreteCommands;

import Exceptions.InputException;
import Exceptions.UnknownCommandException;
import commands.abstraction.Command;
import reading.readers.OfflineReader;
import reading.readers.Reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ExecuteScript extends Command {
    public static List<File> currentScripts = new ArrayList<>();

    public ExecuteScript(Reader reader) {
        super(reader);
    }

    @Override
    public void execute() {
        File script = new File(input.readString());
        currentScripts.add(script);
        OfflineReader offlineReader = null;
        try {
            offlineReader = new OfflineReader(new FileInputStream(script), collection, input.getObjectTree());
            while (offlineReader.hasNext()) {
                Command met = null;
                try {
                    met = offlineReader.readCommand();
                } catch (UnknownCommandException e) {
                    System.out.println("No such command: " + e.getMessage() + " command was skipped");
                }
                try{
                    if (met != null) met.execute();
                } catch (InputException e){
                    System.out.println(e.getMessage() + " Command " + met + " was skipped");
                    offlineReader.skipTillNextCommand();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("file " + script + " does not exist, or can't be accessed");
        } finally {
            if (offlineReader != null) offlineReader.closeStream();
            currentScripts.remove(script);
        }
    }
}
