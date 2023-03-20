package commands.concreteCommands;


import commands.abstraction.Command;
import reading.readers.Reader;

public class SumOfImpactSpeed extends Command {
    public SumOfImpactSpeed(Reader reader) {
        super(reader);
    }

    @Override
    public void execute() {
        System.out.println(collection.sumOfImpactSpeed());
    }
}
