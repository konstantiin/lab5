package commands.concreteCommands;


import StoredClasses.Coordinates;
import commands.abstraction.Command;
import reading.readers.Reader;

public class GroupCountingByCoordinates extends Command {

    public GroupCountingByCoordinates(Reader reader) {
        super(reader);
    }

    @Override
    public void execute() {
        var groups = collection.groupCountingByCoordinates();
        for (Coordinates key : groups.keySet()) {
            System.out.println("Group with coordinates " + key + " has " + groups.get(key).size() + " elements");
        }
    }
}
