package commands;

import commands.interfaces.Command;

public class GroupCountingByCoordinates implements Command {
    private final Iterable<?> collection;
    public GroupCountingByCoordinates(Iterable<?> col){
        collection = col;
    }
}
