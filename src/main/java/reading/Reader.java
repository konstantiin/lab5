package reading;

import Managers.CollectionManager;
import StoredClasses.HumanBeing;
import StoredClasses.forms.HumanBeingForm;
import commands.*;
import commands.interfaces.Command;
import org.apache.commons.lang3.StringUtils;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class Reader {
    public static List<Class<?>> numbers = new ArrayList<>(Arrays.asList(Long.class, long.class, Integer.class, int.class,
                                                                         Float.class, float.class, Double.class, double.class,
                                                                         Short.class, short.class));
    private final HashMap<String, Command> commands = new HashMap<>();

    private final Scanner scan;
    private final Node objectTree;
    private int tabs = 0;

    private final CollectionManager<?> collection;
    public Reader (Scanner scan, CollectionManager<?> col, Node tree){
        this.scan = scan;
        collection =col;
        objectTree = tree;
        this.initCommands();
    }
    public BigInteger readInt(BigInteger lowerBound, BigInteger upperBound){
        BigInteger value;
        try{
            value = new Scanner(scan.nextLine()).nextBigInteger();
        } catch (NoSuchElementException e){
            System.out.print(StringUtils.repeat("\t",tabs) + "Please enter integer number\n" + StringUtils.repeat("\t",tabs));
            return readInt(lowerBound, upperBound);
        }
        if (value.compareTo(lowerBound) < 0 || value.compareTo(upperBound) > 0){
            System.out.print(StringUtils.repeat("\t",tabs) +"This field should be between " + lowerBound + " and "
                    + upperBound + " please type it correctly\n" + StringUtils.repeat("\t",tabs) );
            value = readInt(lowerBound, upperBound);
        }
        return value;
    }
    public BigDecimal readDec(BigDecimal lowerBound, BigDecimal upperBound){
        BigDecimal value;
        try{
            value = new Scanner(scan.nextLine()).nextBigDecimal();
        } catch (NoSuchElementException e){
            System.out.print(StringUtils.repeat("\t",tabs) +"Please enter decimal number\n" + StringUtils.repeat("\t",tabs));
            return readDec(lowerBound, upperBound);
        }
        if (value.compareTo(lowerBound) < 0 || value.compareTo(upperBound) > 0){
            System.out.print(StringUtils.repeat("\t",tabs) +"This field should be between " + lowerBound + " and "
                    + upperBound + " please type it correctly\n" + StringUtils.repeat("\t",tabs));
            value = readDec(lowerBound, upperBound);
        }
        return value;
    }
    public Boolean readBool(){
        try {
            return new Scanner(scan.nextLine()).nextBoolean();
        } catch (NoSuchElementException e){
            System.out.print(StringUtils.repeat("\t",tabs) + "Type true/false\n" + StringUtils.repeat("\t",tabs) );
            return readBool();
        }
    }
    public String readString(){
        String input = scan.nextLine().trim();
        if (input.equals("")) return readString();
        return input;
    }

    public Object readEnum(Class type){
        String name = scan.nextLine();
        Enum<?> value;
        try{
            value = Enum.valueOf(type, name);
        } catch (IllegalArgumentException e){
            System.out.print(StringUtils.repeat("\t",tabs) + "Type one of " + type.getName() + " values\n" + StringUtils.repeat("\t",tabs));
            return readEnum(type);
        }
        return value;
    }

    private Object readTree(Node v){
        tabs++;
        System.out.print(StringUtils.repeat("\t",tabs) + v.getName() + ": ");
        if (v.ifNullable()){
            System.out.print(StringUtils.repeat("\t",tabs) +"Leave this field null?(y/n) ");
            String l = scan.nextLine();
            if(l.charAt(0) == 'y'){
                tabs--;
                return null;
            }
            if (Node.leavesClasses.contains(v.getType()) || v.getType().isPrimitive() || v.getType().isEnum()){
                System.out.print(StringUtils.repeat("\t",tabs));
            }

        }
        Class<?> cls = v.getType();
        Object result = null;
        if (cls == Float.class || cls == float.class){
            result = this.readDec(v.getLowerBound(), v.getUpperBound()).floatValue();
        }
        else if ( cls == Long.class || cls == long.class ){
            result = this.readInt(v.getLowerBound().toBigInteger(), v.getUpperBound().toBigInteger()).longValue();
        }
        else if (cls == Integer.class || cls == int.class){
            result = this.readInt(v.getLowerBound().toBigInteger(), v.getUpperBound().toBigInteger()).intValue();
        }
        else if (cls == Boolean.class){
            result =  this.readBool();
        }
        else if (cls == String.class){
            result = this.readString();
        }
        else if (cls.isEnum()){
            result = this.readEnum(cls);
        }
        else{
            if (!v.ifNullable())System.out.println();
            HashMap<String, Object> fields = new HashMap<>();
            for (Node i: v.getFields()){
                fields.put(i.getName(), readTree(i));
            }

            try{
                result =  v.getObjectGenerator().generate(fields);
            } catch (NullPointerException e){
                System.out.println(v.getObjectGenerator());
            }
        }
        tabs--;
        return result;

    }
    public Object readObject(){
        tabs = -1;
        System.out.println("Type object");
        var result = readTree(objectTree);
        System.out.println("Input ended");
        return result;
    }
    public CollectionManager<?> getCollection(){return collection;}
    public void initCommands(){
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

    public Command readCommand() {
        String metName = this.scan.next().trim();
        Command command = commands.get(metName);
        if (command == null){
            //System.out.println("Command does not Exist");
            throw new RuntimeException("Not valid command");
        }
        return command;
    }
    public void closeStream(){
        scan.close();
    }
}


