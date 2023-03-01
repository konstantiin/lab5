
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
import java.util.Scanner;
import java.util.TreeSet;

public class Main{

    static String getPath(){
        //get path
        return "input.xml";
    }
    static Node getTree(){
        HumanBeingForm storage = new HumanBeingForm();
        Node tree = new Node(HumanBeingForm.class, storage, "HumanBeing", null, null, false);
        Node coordinates = new Node(Coordinates.class, storage.getCoordinates(), "coordinates", null, null, false);
        coordinates.addField(new Node(Float.class, 0, "x", BigDecimal.valueOf(0), BigDecimal.valueOf(570), false));
        coordinates.addField(new Node(Long.class, 0, "y", BigDecimal.valueOf(0), BigDecimal.valueOf(294), false));
        tree.addField(coordinates);

        Node car = new Node(Car.class, storage.getCar(), "car", null, null, true);
        car.addField(new Node(String.class, "", "name", null, null, false));
        car.addField(new Node(Boolean.class, "", "isCool", null, null, false));
        tree.addField(car);

        tree.addField(new Node(String.class, "", "name", null, null, false));
        tree.addField(new Node(Mood.class, Mood.CALM, "mood", null, null, false));
        tree.addField(new Node(WeaponType.class, WeaponType.SHOTGUN, "weaponType", null, null, false));
        tree.addField(new Node(Float.class, 0, "impactSpeed", BigDecimal.valueOf(Float.MIN_VALUE), BigDecimal.valueOf(Float.MAX_VALUE), true));
        tree.addField(new Node(Boolean.class, false, "realHero", null, null, false ));
        tree.addField(new Node(Boolean.class, false, "hasToothpick", null, null, true));

        return tree;

    }
    public static void main(String[] args) throws Exception {
        TreeSet<HumanBeing> set = new TreeSet<>();
        new ParseXml(getPath()).parseToArr();

        Reader<HumanBeingForm> console = new Reader<>(new Scanner(System.in), set, getTree());


        while (true){
            Command met = console.readCommand();
            met.execute();
        }
    }
}