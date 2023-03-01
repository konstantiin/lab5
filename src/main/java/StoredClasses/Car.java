package StoredClasses;

import parse.Copyable;

public class Car implements Copyable {
    public Car(String name, boolean cool){
        this.name = name;
        this.cool = cool;
    }
    private final String name; //Поле не может быть null
    private final Boolean cool; //Поле не может быть null
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