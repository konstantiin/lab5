
import StoredClasses.Car;
import StoredClasses.Coordinates;
import StoredClasses.HumanBeing;
import StoredClasses.enums.Mood;
import StoredClasses.enums.WeaponType;
import commands.interfaces.Command;
import parse.HumanBeingForm;
import parse.ParseXml;
import reading.Node;
import reading.Reader;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class Main{
    static String getPath(){
        //get path
        return "input.xml";
    }
    public static void main(String[] args) throws Exception {
        TreeSet<HumanBeing> set = new TreeSet<>();
        new ParseXml(getPath()).parseToArr();

        Reader<HumanBeingForm> console = new Reader<>(new Scanner(System.in), set, Node.generateTree(new HumanBeingForm()));


        while (true){
            Command met = console.readCommand();
            met.execute();
        }
    }
}