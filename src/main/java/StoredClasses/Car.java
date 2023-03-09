package StoredClasses;

import StoredClasses.annotations.NotNull;
import parse.Copyable;

public class Car implements Copyable {
    public Car(String name, boolean cool){
        this.name = name;
        this.cool = cool;
    }
    private final @NotNull String name;
    private final @NotNull Boolean cool;
    @Override
    public Car deepCopy(){
        return new Car(this.name, this.cool);
    }
    @Override
    public String toString(){
        if (cool){
            return "cool " + name;
        }
        return "not cool" + name;
    }
}