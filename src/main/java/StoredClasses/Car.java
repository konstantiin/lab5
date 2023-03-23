package StoredClasses;

import Exceptions.EmptyStringException;
import Exceptions.NullObjectException;
import StoredClasses.annotations.NotNull;
import reading.generators.CarGenerator;
import reading.generators.Generator;

import java.lang.reflect.Field;

public class Car implements Checkable{
    private final @NotNull String name;
    private final @NotNull Boolean cool;
    public static Generator getGenerator(){
        return new CarGenerator();
    }
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