package main;


import StoredClasses.HumanBeing;
import commands.abstraction.Command;
import commands.launcher.CommandsLauncher;
import exceptions.inputExceptions.UnknownCommandException;
import parse.ParseXml;
import reading.objectTree.Node;
import reading.readers.OnlineReader;

import java.util.List;
import java.util.TreeSet;


public class Main {
    public static ParseXml XMLInput;

    /**
     * gets path to input/output file
     * from the environment variable
     *
     * @return xml file path
     */
    static String getPath() {
        return System.getenv("LAB5");
    }


    /**
     * main method
     * creates managed collection, parses xml file and execute commands from System.in
     */
    public static void main(String[] args) {
        XMLInput = ParseXml.getXMLInput(getPath());
        List<HumanBeing> list = XMLInput.getArr();
        System.out.println("XML file was read successfully!");


        TreeSet<HumanBeing> set = new TreeSet<>();
        if (list != null) set = new TreeSet<>(list);

        Node tree = Node.generateTree(HumanBeing.class, "HumanBeing");

        OnlineReader console = new OnlineReader(System.in, new CommandsLauncher<>(set), tree);

        while (console.hasNext()) {
            Command met = null;
            try {
                met = console.readCommand();
            } catch (UnknownCommandException e) {
                System.out.println("Command not found, type \"help\" for more info");
            }
            if (met != null) met.execute();
        }

    }
}