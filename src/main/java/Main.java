
import Managers.CollectionManager;
import StoredClasses.HumanBeing;
import commands.interfaces.Command;
import StoredClasses.forms.HumanBeingForm;
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

        Reader console = new Reader(new Scanner(System.in), new CollectionManager<HumanBeing>(set), tree);

        while (true){
            Command met = console.readCommand();
            met.execute();
        }

    }
}