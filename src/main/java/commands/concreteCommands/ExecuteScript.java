package commands.concreteCommands;


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
        if (currentScripts.contains(script)){
            System.out.println("Script is already compiling. Command " + this + " was skipped");
            return;
        }
        currentScripts.add(script);
        OfflineReader offlineReader = null;
        try {
            offlineReader = new OfflineReader(new FileInputStream(script), collection, input.getObjectTree());
            collection.execute_script(offlineReader);
        } catch (FileNotFoundException e) {
            System.out.println("File " + script + " does not exist, or can't be accessed");
        } finally {
            if (offlineReader != null) offlineReader.closeStream();
            currentScripts.remove(script);
        }
        System.out.println("Script " + script + " has compiled");
    }
}
