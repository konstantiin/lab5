
import StoredClasses.HumanBeing;
import commands.interfaces.Command;
import parse.ParseXml;
import reading.Reader;

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


        Reader console = new Reader(new Scanner(System.in), set);
        while (true){
            Command met = console.readCommand();
            met.execute();
        }
    }
}