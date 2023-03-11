package reading;

import Exceptions.UnknownCommandException;
import StoredClasses.HumanBeing;
import commands.*;
import commands.interfaces.Command;
import parse.Copyable;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class Reader <T extends Copyable>{
    public static List<Class<?>> numbers = new ArrayList<>(Arrays.asList(Long.class, long.class, Integer.class, int.class,
                                                                        Float.class, float.class, Double.class, double.class,
                                                                        Short.class, short.class));

    private final Scanner scan;
    private final Node objectTree;

    private final TreeSet<HumanBeing> collection;
    public Reader (Scanner scan, TreeSet<HumanBeing> col, Node tree){
        this.scan = scan;
        collection =col;
        objectTree = tree;
    }
    private BigInteger readInt(BigInteger lowerBound, BigInteger upperBound){
        BigInteger value = scan.nextBigInteger();
        while (value.compareTo(lowerBound) < 0 || value.compareTo(upperBound) > 0){
            System.out.println("This field should be between " + lowerBound + " and " + upperBound + " please type it correctly\n");
            value = scan.nextBigInteger();
        }
        return value;
    }
    private BigDecimal readDec(BigDecimal lowerBound, BigDecimal upperBound){
        BigDecimal value = scan.nextBigDecimal();
        while (value.compareTo(lowerBound) < 0 || value.compareTo(upperBound) > 0){
            System.out.println("This field should be between " + lowerBound + " and " + upperBound + " please type it correctly\n");
            value = scan.nextBigDecimal();
        }
        return value;
    }
    private Boolean readBool(){
        return scan.nextBoolean();
    }
    private String readString(){
        return scan.next();
    }
    private <E> E readEnum(Class<E> type){
        return type.cast(null);
    }
    private void mulTab(int x){
        while (x != 0) {
            System.out.println("\t");
            x--;
        }
    }
    private void readTree(Node v, int count){
        var storage = v.getType().cast(v.getStorage());
        System.out.print(v.getName() + ": ");
        if (v.ifNullable()){
            System.out.println("Leave this field null?(y/n) ");
            if(scan.nextLine().charAt(0) == 'y') return;
        }
        Class<?> cls = v.getType();
        if (cls == Float.class){
            storage = this.readDec(v.getLowerBound(), v.getUpperBound()).floatValue();
        }
        else if (cls == Integer.class || cls == Long.class){
            storage = this.readInt(v.getLowerBound().toBigInteger(), v.getUpperBound().toBigInteger());
        }
        else if (cls == Boolean.class){
            storage = this.readBool();
        }
        else if (cls == String.class){
            storage = this.readString();
        }
        else if (cls.isEnum()){
            storage = this.readEnum(cls);
        }
        else{
            for (Node i: v.getFields()){
                readTree(i, count+1);
            }
        }
    }
    private T readObject(){
        readTree(objectTree, 0);
        return (T)((Copyable)objectTree.getStorage()).deepCopy();//hz chto delat
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
                this.readObject();
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


