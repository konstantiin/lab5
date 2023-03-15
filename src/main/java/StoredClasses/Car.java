package StoredClasses;

import StoredClasses.annotations.NotNull;

public class Car {
    public Car(String name, boolean cool){
        this.name = name;
        this.cool = cool;
    }
    private final @NotNull String name;
    private final @NotNull Boolean cool;
    @Override
    public String toString(){
        if (cool){
            return "cool " + name;
        }
        return "not cool" + name;
    }
}