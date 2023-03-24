package reading.readers;

import Exceptions.inputExceptions.*;
import commands.abstraction.Command;
import commands.concreteCommands.*;
import commands.launcher.CommandsLauncher;
import reading.objectTree.Node;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public abstract class Reader {
    /**
     * List of classes representing numbers
     */
    public static List<Class<?>> numbers = new ArrayList<>(Arrays.asList(Long.class, long.class, Integer.class, int.class,
            Float.class, float.class, Double.class, double.class,
            Short.class, short.class));
    protected final HashMap<String, Command> commands = new HashMap<>();
    protected final Scanner scan;
    protected final Node objectTree;
    protected final CommandsLauncher<?> collection;
    protected int tabs = 0;

    /**
     * @param source - input stream
     * @param col    - managed collection
     * @param tree   - tree to read
     */
    public Reader(InputStream source, CommandsLauncher<?> col, Node tree) {
        this.scan = new Scanner(source);
        collection = col;
        objectTree = tree;
        this.initCommands();
    }

    protected abstract String getNext();

    /**
     * reads string
     *
     * @return next string
     */
    public String readString() {
        String input = getNext().trim();
        if (input.equals("")) throw new EmptyStringException();
        return input;
    }

    /**
     * reads integer
     *
     * @return integer value between lowerBound and upperBound
     * @throws OutOfBoundsException - if input is out of bounds
     */
    public BigInteger readInt(BigInteger lowerBound, BigInteger upperBound) throws OutOfBoundsException {
        BigInteger value;
        try {
            value = new Scanner(getNext()).nextBigInteger();
        } catch (NoSuchElementException e) {
            throw new WrongInputException("value should be an integer number!");
        }
        if (value.compareTo(lowerBound) < 0 || value.compareTo(upperBound) > 0) {
            throw new OutOfBoundsException();
        }
        return value;
    }

    /**
     * reads decimal
     *
     * @return decimal value between lowerBound and upperBound
     * @throws OutOfBoundsException - if input is out of bounds
     */
    public BigDecimal readDec(BigDecimal lowerBound, BigDecimal upperBound) throws OutOfBoundsException {
        BigDecimal value;
        try {
            value = new Scanner(getNext()).nextBigDecimal();
        } catch (NoSuchElementException e) {
            throw new WrongInputException("value should be a decimal number!");
        }
        if (value.compareTo(lowerBound) < 0 || value.compareTo(upperBound) > 0) {
            throw new OutOfBoundsException();
        }
        return value;
    }

    /**
     * reads boolean
     *
     * @return next boolean value
     */
    public Boolean readBool() {
        try {
            return new Scanner(getNext()).nextBoolean();
        } catch (NoSuchElementException e) {
            throw new WrongInputException("value should be true or false!");
        }
    }

    /**
     * reads next object, represented by object tree
     *
     * @return next object
     */
    public abstract Object readObject();

    /**
     * reads Enum value
     *
     * @param type - Enum to read
     * @return next value of Enum type
     */
    public Object readEnum(Class<?> type) {
        String name = getNext();
        try {
            return type.getMethod("valueOf", String.class).invoke(null, name);
        } catch (IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(type.getName() + " is not Enum");
        } catch (InvocationTargetException e) {
            throw new EnumInputException(e);
        }
        /*Enum<?> value;
        value = Enum.valueOf(type, name); // IllegalArgumentException
        return value;*/
    }

    /**
     * returns managed collection
     *
     * @return collection
     */
    public CommandsLauncher<?> getCollection() {
        return collection;
    }

    private void initCommands() {
        commands.put("help", new Help());
        commands.put("info", new Info(this));
        commands.put("show", new Show(this));
        commands.put("add", new Add(this));
        commands.put("update", new Update(this));
        commands.put("remove_by_id", new RemoveById(this));
        commands.put("clear", new Clear(this));
        commands.put("save", new Save(this));
        commands.put("execute_script", new ExecuteScript(this));
        commands.put("exit", new Exit(this));
        commands.put("add_if_min", new AddIfMin(this));
        commands.put("remove_greater", new RemoveGreater(this));
        commands.put("remove_Lower", new RemoveLower(this));
        commands.put("sum_of_impact_speed", new SumOfImpactSpeed(this));
        commands.put("group_counting_by_coordinates", new GroupCountingByCoordinates(this));
        commands.put("filter_contains_name", new FilterContainsName(this));
    }

    /**
     * returns object tree
     *
     * @return tree root
     */
    public Node getObjectTree() {
        return this.objectTree;
    }

    /**
     * @return checks if stream has next value
     */
    public abstract boolean hasNext();


    protected abstract boolean readNull(Node v);

    protected abstract boolean checkNotNullObject();

    protected Object readTree(Node v) {
        try {
            tabs++;
            if (v.ifNullable()) {
                if (readNull(v)) {
                    this.readLine();
                    tabs--;
                    return null;
                }
            }
            Class<?> cls = v.getType();
            Object result;
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

                if (checkNotNullObject()) {
                    throw new NullObjectException("Field \"" + v.getName() + "\" can't be null!");
                }
                this.readLine();
                HashMap<String, Object> fields = new HashMap<>();
                for (Node i : v.getFields()) {
                    fields.put(i.getName(), readTree(i));
                }
                result = v.getObjectGenerator().generate(fields);

            }
            tabs--;
            return result;
        } catch (EmptyStringException e) {
            throw new InputException("Field \"" + v.getName() + "\" should not be empty!");
        } catch (EnumInputException e) {
            throw new InputException("Field \"" + v.getName() + "\" should be one of " + v.getType() + " values!");
        } catch (OutOfBoundsException e) {
            throw new InputException("Field \"" + v.getName() + "\" should be between " + v.getLowerBound() + " and " + v.getUpperBound() + "!");
        } catch (WrongInputException e) {
            throw new InputException("Field \"" + v.getName() + "\" " + e.getMessage());
        }
    }

    protected void readLine() {                                // мб стоит с этим что-то сделать, но пока что пусть будет так
    }

    /**
     * reads command
     *
     * @return next command
     */
    public Command readCommand() {
        String metName = getNext().trim();
        Command command = commands.get(metName);
        if (command == null) {
            throw new UnknownCommandException(metName);
        }
        return command;
    }

    /**
     * closes stream
     */
    public void closeStream() {
        scan.close();
    }
}


