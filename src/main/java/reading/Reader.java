package reading;

import Exceptions.UnknownCommandException;
import StoredClasses.HumanBeing;
import commands.*;
import commands.interfaces.Command;
import org.apache.commons.lang3.StringUtils;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class Reader <T>{
    public static List<Class<?>> numbers = new ArrayList<>(Arrays.asList(Long.class, long.class, Integer.class, int.class,
                                                                        Float.class, float.class, Double.class, double.class,
                                                                        Short.class, short.class));

    private final Scanner scan;
    private final Node objectTree;
    private int tabs = 0;

    private final TreeSet<HumanBeing> collection;
    public Reader (Scanner scan, TreeSet<HumanBeing> col, Node tree){
        this.scan = scan;
        collection =col;
        objectTree = tree;
    }
    private BigInteger readInt(BigInteger lowerBound, BigInteger upperBound){
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
    private BigDecimal readDec(BigDecimal lowerBound, BigDecimal upperBound){
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
    private Boolean readBool(){
        try {
            return new Scanner(scan.nextLine()).nextBoolean();
        } catch (NoSuchElementException e){
            System.out.print(StringUtils.repeat("\t",tabs) + "Type true/false\n" + StringUtils.repeat("\t",tabs) );
            return readBool();
        }
    }
    private String readString(){
        return scan.nextLine();
    }

    private Enum readEnum(Class type){ // говнокод и я хз как его исправить
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
    private T readObject(){
        tabs = -1;
        System.out.println("Type object");
        T result = (T)readTree(objectTree); // hz chto delat
        System.out.println("Input ended");

        return result;
    }

    public Command readCommand() throws Exception {
        String metName = this.scan.nextLine();
        switch (metName){
            case "help":
                return new Help();
            case "info":
                return new Info(collection);
            case "show":
                return new Show(collection);
            case "add":
                System.out.println(this.readObject());
                return new Add(collection);
            case "update_id":
                return new UpdateId(collection);
            case "remove_by_id":
                return new RemoveById(collection);
            case "clear":
                return new Clear(collection);
            case "save":
                return new Save(collection);
            case "execute_script":
                return new ExecuteScript();
            case "exit":
                return new Exit();
            case "sum_of_impact_speed":
                return new SumOfImpactSpeed(collection);
            case "add_if_min":
                return new AddIfMin(collection);
            case "remove_greater":
                return new RemoveGreater(collection);
            case "filter_contains_name":
                return new FilterContainsName(collection);
            case "remove_lower":
                return new RemoveLower(collection);
            case "group_counting_by_coordinates":
                return new GroupCountingByCoordinates(collection);
        }
        throw new UnknownCommandException("Command not found");
    }
    public void closeStream(){
        scan.close();
    }
}


