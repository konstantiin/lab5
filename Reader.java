import StoredClasses.HumanBeing;

import java.lang.reflect.Method;
import java.util.Scanner;

public class Reader {
    private final Scanner scan;
    private final MyTreeSet<HumanBeing> collection;
    public Reader (Scanner scan, MyTreeSet<HumanBeing> col){
        this.scan = scan;
        collection =col;
    }
    public Method Read() throws Exception {
        String metName = this.scan.nextLine();
        switch (metName){
            case "help":
                System.out.println("help your self");
                return null;
            case "show":
                Method met = collection.getClass().getMethod("show");
                met.invoke(collection);
                return met;
        }
        throw new Exception("Command not found");
    }
    public void closeStream(){
        scan.close();
    }
}
