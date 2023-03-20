package StoredClasses;

import StoredClasses.annotations.NotNull;

public class Car {
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