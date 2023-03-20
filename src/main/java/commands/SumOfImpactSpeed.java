package commands;


import commands.interfaces.Command;

import reading.Reader;

public class SumOfImpactSpeed extends Command {
    public SumOfImpactSpeed(Reader reader){
        super(reader);
    }

    @Override
    public void execute() {
        System.out.println(collection.sumOfImpactSpeed());
    }
}
