package reading.readers;

import Exceptions.EnumInputException;
import Exceptions.OutOfBoundsException;
import Exceptions.UnknownCommandException;
import Exceptions.WrongInputException;
import commands.launcher.CommandsLauncher;
import commands.abstraction.Command;
import org.apache.commons.lang3.StringUtils;
import reading.objectTree.Node;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;


public class OnlineReader extends Reader {
    private char lastPrintedChar;

    public OnlineReader(InputStream source, CommandsLauncher<?> col, Node tree) {
        super(source, col, tree);
    }
    private void print(String s) {
        lastPrintedChar = s.charAt(s.length() - 1);
        System.out.print(s);
    }
    @Override
    public BigInteger readInt(BigInteger lowerBound, BigInteger upperBound) {

        try {
            lastPrintedChar = '\n';
            return super.readInt(lowerBound, upperBound);
        } catch (OutOfBoundsException e) {
            print(StringUtils.repeat("\t", tabs) + "This field should be between " + lowerBound + " and "
                    + upperBound + " please type it correctly\n" + StringUtils.repeat("\t", tabs));
            return this.readInt(lowerBound, upperBound);
        } catch (WrongInputException e) {
            print(StringUtils.repeat("\t", tabs) + "Please enter decimal number\n" + StringUtils.repeat("\t", tabs));
            return this.readInt(lowerBound, upperBound);
        }
    }
    @Override
    protected Object readTree(Node v) {
        if (lastPrintedChar != '\n') {
            print("\n");
        }
        print(StringUtils.repeat("\t", tabs + 1) + v.getName() + ": ");
        return super.readTree(v);
    }
    @Override
    public Object readObject() {
        tabs = -1;
        print("Type object\n");
        var result = readTree(objectTree);
        print("Input ended\n");
        return result;
    }
    @Override
    public String readString() {
        lastPrintedChar = '\n';
        return super.readString();
    }
    @Override
    protected boolean readNull(Node v) {
        print(StringUtils.repeat("\t", tabs) + "Leave this field null?(y/n)");
        String l = this.readString();
        if (l.charAt(0) == 'y') {
            lastPrintedChar = '\n';
            return true;
        }
        if (Node.leavesClasses.contains(v.getType()) || v.getType().isPrimitive() || v.getType().isEnum()) {
            print(StringUtils.repeat("\t", tabs));
        }
        lastPrintedChar = '\n';
        return false;
    }

    @Override
    protected boolean checkNotNullObject() {
        return false;
    }

    @Override
    public BigDecimal readDec(BigDecimal lowerBound, BigDecimal upperBound) {
        try {
            lastPrintedChar = '\n';
            return super.readDec(lowerBound, upperBound);
        } catch (OutOfBoundsException e) {
            print(StringUtils.repeat("\t", tabs) + "This field should be between " + lowerBound + " and "
                    + upperBound + " please type it correctly\n" + StringUtils.repeat("\t", tabs));
            return this.readDec(lowerBound, upperBound);
        } catch (WrongInputException e) {
            print(StringUtils.repeat("\t", tabs) + "Please enter decimal number\n" + StringUtils.repeat("\t", tabs));
            return this.readDec(lowerBound, upperBound);
        }
    }
    @Override
    public Boolean readBool() {
        try {
            lastPrintedChar = '\n';
            return super.readBool();
        } catch (WrongInputException e) {
            print(StringUtils.repeat("\t", tabs) + "Type true/false\n" + StringUtils.repeat("\t", tabs));
            return this.readBool();
        }
    }
    public boolean hasNext() {
        return scan.hasNext();
    }
    @Override
    public Object readEnum(Class<?> type) {
        try {
            lastPrintedChar = '\n';
            return super.readEnum(type);
        } catch (EnumInputException e) {
            print(StringUtils.repeat("\t", tabs) + "Type one of " + type.getName() + " values\n" + StringUtils.repeat("\t", tabs));
            return this.readEnum(type);
        }
    }
    @Override
    public Command readCommand() {
        try {
            return super.readCommand();
        } catch (UnknownCommandException e) {
            print("Command not found\n");
            return null;
        }
    }
    @Override
    protected String getNext() {
        return scan.next();
    }
}


