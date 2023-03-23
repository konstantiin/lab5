package main;

import Exceptions.*;
import commands.launcher.CommandsLauncher;
import StoredClasses.HumanBeing;
import commands.abstraction.Command;
import parse.ParseXml;
import reading.objectTree.Node;
import reading.readers.OnlineReader;


import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static ParseXml XMLInput;
    static String getPath() {
        //get path
        return "inpt.xml";
    }

    public static void main(String[] args) {
        String path = getPath();
        while (true) {
            try {
                XMLInput = new ParseXml(path);
                break;
            } catch (FIleDoesNotExistException e) {
                System.out.println("Your file does not exist! Type correct file path");
                path = new Scanner(System.in).next();
            } catch (FileNotReadableException e) {
                System.out.println("Your file can't be read! Type correct file path");
                path = new Scanner(System.in).next();
            } catch (FileNotWritableException e) {
                System.out.println("Your file isn't writable! Type correct file path");
                path = new Scanner(System.in).next();
            }
        }
        System.out.println("XML file was read successfully!");
        List<HumanBeing> list = XMLInput.getArr();
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