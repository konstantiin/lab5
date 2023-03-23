package StoredClasses;

import Exceptions.EmptyStringException;
import Exceptions.NullObjectException;
import StoredClasses.annotations.NotNull;

import java.lang.reflect.Field;

public class Car implements Checkable{
    private final @NotNull String name;
    private final @NotNull Boolean cool;
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