import StoredClasses.HumanBeing;
import commands.interfaces.Command;
import reading.Reader;

import java.util.Scanner;
import java.util.TreeSet;

public class Main{

    public static void main(String[] args) throws Exception {
        TreeSet<HumanBeing> set = new TreeSet<>();

        set.add(new HumanBeing());
        set.add(new HumanBeing());
        set.add(new HumanBeing());
        set.add(new HumanBeing());
        set.add(new HumanBeing());
        set.add(new HumanBeing());


        Reader console = new Reader(new Scanner(System.in), set);
        while (true){
            Command met = console.ReadCommand();
            met.execute();
        }
    }
}