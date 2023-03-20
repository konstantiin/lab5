import Exceptions.UnknownCommandException;
import commands.launcher.CommandsLauncher;
import StoredClasses.HumanBeing;
import commands.abstraction.Command;
import parse.ParseXml;
import reading.objectTree.Node;
import reading.readers.OnlineReader;


import java.util.List;
import java.util.TreeSet;

public class Main {
    static String getPath() {
        //get path
        return "input.xml";
    }

    public static void main(String[] args) {
        List<HumanBeing> list = new ParseXml(getPath()).parseToArr();
        TreeSet<HumanBeing> set = new TreeSet<>(list);
        Node tree = Node.generateTree(HumanBeing.class, "HumanBeing");

        OnlineReader console = new OnlineReader(System.in, new CommandsLauncher<>(set), tree);

        while (console.hasNext()) {
            Command met = null;
            try {
                met = console.readCommand();
            } catch (UnknownCommandException e) {
                System.out.println("Command not found, type help for more info");
            }
            if (met != null) met.execute();
        }

    }
}