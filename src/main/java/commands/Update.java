package commands;

import Exceptions.IdException;
import commands.interfaces.Command;
import reading.Reader;

import java.math.BigInteger;

public class Update extends Command {
    public Update( Reader reader){
        super(reader);
    }

    @Override
    public void execute() {
        try {
            collection.update(input.readInt(BigInteger.ZERO, BigInteger.valueOf(Integer.MAX_VALUE)).longValue(), input.readObject());
            System.out.println("Element updated");
        } catch (IdException e) {
            System.out.println("Id not found");
        }
    }
}
