
import Exceptions.UnknownCommandException;
import Managers.CollectionManager;
import StoredClasses.HumanBeing;
import commands.interfaces.Command;
import parse.ParseXml;
import reading.Node;
import reading.Reader;

import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class Main{
    static String getPath(){
        //get path
        return "input.xml";
    }
    public static void main(String[] args) {

        List<HumanBeing> list = new ParseXml(getPath()).parseToArr();
        TreeSet<HumanBeing> set = new TreeSet<>(list);
        Node tree = Node.generateTree(HumanBeing.class, "HumanBeing");

        Reader console = new Reader(new Scanner(System.in), new CollectionManager<>(set), tree);

        while (console.hasNext()){
            Command met = null;
            try {
                met = console.readCommand();
            } catch (UnknownCommandException e) {
                System.out.println("Command not found, type help for more info");
            }
            if(met != null) met.execute();
        }

    }
}