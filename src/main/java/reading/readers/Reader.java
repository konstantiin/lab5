package reading.readers;

import Exceptions.EmptyStringException;
import Exceptions.OutOfBoundsException;
import Exceptions.UnknownCommandException;
import commands.launcher.CommandsLauncher;
import commands.abstraction.Command;
import commands.concreteCommands.*;
import reading.objectTree.Node;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public abstract class Reader {
    public static List<Class<?>> numbers = new ArrayList<>(Arrays.asList(Long.class, long.class, Integer.class, int.class,
            Float.class, float.class, Double.class, double.class,
            Short.class, short.class));
    protected final HashMap<String, Command> commands = new HashMap<>();
    protected final Scanner scan;
    protected final Node objectTree;
    protected final CommandsLauncher<?> collection;
    protected int tabs = 0;

    public Reader(InputStream source, CommandsLauncher<?> col, Node tree) {
        this.scan = new Scanner(source);
        collection = col;
        objectTree = tree;
        this.initCommands();
    }

    protected String getNext() {
        return scan.next();
    }

    public String readString() {
        String input = getNext().trim();
        if (input.equals("")) throw new EmptyStringException();
        return input;
    }

    public BigInteger readInt(BigInteger lowerBound, BigInteger upperBound) throws OutOfBoundsException {
        BigInteger value;
        value = new Scanner(getNext()).nextBigInteger(); // NoSuchElementException
        if (value.compareTo(lowerBound) < 0 || value.compareTo(upperBound) > 0) {
            throw new OutOfBoundsException();
        }
        return value;
    }

    public BigDecimal readDec(BigDecimal lowerBound, BigDecimal upperBound) throws OutOfBoundsException {
        BigDecimal value;
        value = new Scanner(getNext()).nextBigDecimal(); // NoSuchElementException
        if (value.compareTo(lowerBound) < 0 || value.compareTo(upperBound) > 0) {
            throw new OutOfBoundsException();
        }
        return value;
    }

    public Boolean readBool() {
        return new Scanner(getNext()).nextBoolean();//NoSuchElementException

    }

    public abstract Object readObject();

    public Object readEnum(Class type) {
        String name = getNext();
        Enum<?> value;
        value = Enum.valueOf(type, name); // IllegalArgumentException
        return value;
    }

    public CommandsLauncher<?> getCollection() {
        return collection;
    }

    public void initCommands() {
        commands.put("help", new Help());
        commands.put("info", new Info(this));
        commands.put("show", new Show(this));
        commands.put("add", new Add(this));
        commands.put("update", new Update(this));
        commands.put("remove_by_id", new RemoveById(this));
        commands.put("clear", new Clear(this));
        commands.put("save", new Save(this));
        commands.put("execute_script", new ExecuteScript(this));
        commands.put("exit", new Exit());
        commands.put("add_if_min", new AddIfMin(this));
        commands.put("remove_greater", new RemoveGreater(this));
        commands.put("remove_Lower", new RemoveLower(this));
        commands.put("sum_of_impact_speed", new SumOfImpactSpeed(this));
        commands.put("group_counting_by_coordinates", new GroupCountingByCoordinates(this));
        commands.put("filter_contains_name", new FilterContainsName(this));
    }

    public Node getObjectTree() {
        return this.objectTree;
    }

    public boolean hasNext() {
        return scan.hasNext();
    }


    protected abstract boolean readNull(Node v);

    protected Object readTree(Node v) {
        tabs++;

        if (v.ifNullable()) {
            if (readNull(v)) {
                this.readLine();
                tabs--;
                return null;
            }
        }
        Class<?> cls = v.getType();
        Object result = null;
        if (cls == Float.class || cls == float.class) {
            result = this.readDec(v.getLowerBound(), v.getUpperBound()).floatValue();
        } else if (cls == Long.class || cls == long.class) {
            result = this.readInt(v.getLowerBound().toBigInteger(), v.getUpperBound().toBigInteger()).longValue();
        } else if (cls == Integer.class || cls == int.class) {
            result = this.readInt(v.getLowerBound().toBigInteger(), v.getUpperBound().toBigInteger()).intValue();
        } else if (cls == Boolean.class) {
            result = this.readBool();
        } else if (cls == String.class) {
            result = this.readString();
        } else if (cls.isEnum()) {
            result = this.readEnum(cls);
        } else {
            this.readLine();
            HashMap<String, Object> fields = new HashMap<>();
            for (Node i : v.getFields()) {
                fields.put(i.getName(), readTree(i));
            }
            result = v.getObjectGenerator().generate(fields);

        }
        tabs--;
        return result;
    }

    public void readLine() {
    }

    public Command readCommand() {
        String metName = getNext().trim();
        Command command = commands.get(metName);
        if (command == null) {
            throw new UnknownCommandException(metName);
        }
        return command;
    }

    public void closeStream() {
        scan.close();
    }
}


