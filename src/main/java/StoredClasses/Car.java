package StoredClasses;

import StoredClasses.annotations.NotNull;
import reading.generators.CarGenerator;
import reading.generators.Generator;
/**
 * stored class
 */
public class Car implements Checkable{
    private final @NotNull String name;
    private final @NotNull Boolean cool;

    /**
     * returns Car Generator
     * @return Generator
     */
    public static Generator getGenerator(){
        return new CarGenerator();
    }

    /**
     * @param name name of car
     * @param cool true if car is cool
     */
    public Car(String name, boolean cool) {
        this.name = name;
        this.cool = cool;
    }

    @Override
    public String toString() {
        if (cool) {
            return "cool " + name;
        }
        return "not cool" + name;
    }
}