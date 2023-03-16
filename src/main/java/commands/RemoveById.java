package commands;

import Exceptions.IdException;
import commands.interfaces.Command;
import reading.Reader;

import java.math.BigInteger;

public class RemoveById extends Command {

    public RemoveById(Reader reader){
        super(reader);
    }

    @Override
    public void execute() {
        try {
            collection.removeById(input.readInt(BigInteger.ZERO, BigInteger.valueOf(Integer.MAX_VALUE)).longValue());
            System.out.println("Element removed");
        } catch (IdException e) {
            System.out.println("Element with this id does not exist");
        }
    }
}
