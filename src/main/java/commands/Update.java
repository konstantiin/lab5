package commands;

import Managers.CollectionManager;
import commands.interfaces.AbstractCommand;
import commands.interfaces.Command;
import reading.Reader;

import java.math.BigInteger;

public class Update extends AbstractCommand {
    public Update( Reader reader){
        super(reader);
    }

    @Override
    public void execute() {
        collection.update(input.readInt(BigInteger.ZERO, BigInteger.valueOf(Integer.MAX_VALUE)).intValue(), input.readObject());
    }
}
