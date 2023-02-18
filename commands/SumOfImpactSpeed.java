package commands;

import commands.interfaces.Command;

public class SumOfImpactSpeed implements Command {
    private final Iterable<?> collection;
    public SumOfImpactSpeed(Iterable<?> col){
        collection = col;
    }

}
