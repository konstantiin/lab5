package commands;


import StoredClasses.Coordinates;
import StoredClasses.HumanBeing;
import commands.interfaces.AbstractCommand;

import reading.Reader;

public class GroupCountingByCoordinates extends AbstractCommand {

    public GroupCountingByCoordinates(Reader reader){
        super( reader);
    }
    @Override
    public void execute() {
        var groups = collection.groupCountingByCoordinates();
        int count = 1;
        for (Coordinates key: groups.keySet()) {
            System.out.println("Group with coordinates " + key + " has " + groups.get(key).size() + " elements");
        }
    }
}
