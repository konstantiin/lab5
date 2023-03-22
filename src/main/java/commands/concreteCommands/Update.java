package commands.concreteCommands;

import Exceptions.IdException;
import commands.abstraction.Command;
import reading.readers.Reader;

import java.math.BigInteger;

public class Update extends Command {
    public Update(Reader reader) {
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
    @Override
    public String toString() {
        String res = "update" ;
        if (ExecuteScript.currentScripts.size() != 0) {
            res += "(in " + ExecuteScript.currentScripts.get(ExecuteScript.currentScripts.size()-1) + " script)";
        }
        return res;
    }
}