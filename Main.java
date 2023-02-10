import StoredClasses.HumanBeing;

import java.lang.reflect.Method;
import java.util.Scanner;
import java.util.TreeSet;

public class Main{

    public static void main(String[] args) throws Exception {
        MyTreeSet<HumanBeing> set = new MyTreeSet<>();

        set.add(new HumanBeing());
        set.add(new HumanBeing());
        set.add(new HumanBeing());
        set.add(new HumanBeing());
        set.add(new HumanBeing());
        set.add(new HumanBeing());


        Reader reader = new Reader(new Scanner(System.in), set);

        while (true){
            Method met = reader.Read();

        }
    }
}