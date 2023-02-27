package StoredClasses;

public class Car {
    public Car(String name){
        this.name = name;
    }
    private String name; //Поле не может быть null
    private Boolean cool = true; //Поле не может быть null
    @Override
    public String toString(){
        if (cool){
            return "cool " + name;
        }
        return "not cool" + name;
    }
}