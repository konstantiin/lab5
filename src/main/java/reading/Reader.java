package reading;

import Exceptions.UnknownCommandException;
import StoredClasses.HumanBeing;
import commands.*;
import commands.interfaces.Command;


import java.util.Scanner;
import java.util.TreeSet;

public class Reader {
    private final Scanner scan;
    private Node ObjectTree;

    private final TreeSet<HumanBeing> collection;
    public Reader (Scanner scan, TreeSet<HumanBeing> col){
        this.scan = scan;
        collection =col;
    }
    private Integer readInt(){
        return null;
    }
    private Boolean readBool(){
        return null;
    }
    private String readString(){
        return null;
    }
    private void readTree(Node v){
        var storage = v.getType().cast(v.getStorage());
        System.out.println("Type " + v.getName());
        if (storage.getClass() == Integer.class){
            storage = this.readInt();
        }
        else if (storage.getClass() == Boolean.class){
            storage = this.readBool();
        }
        else if (storage.getClass() == String.class){
            storage = this.readString();
        }
        else{
            for (Node i: v.getFields()){
                readTree(i);
            }
        }
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
