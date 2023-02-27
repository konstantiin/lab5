package parse;

import StoredClasses.Car;
import StoredClasses.Coordinates;
import StoredClasses.enums.Mood;
import StoredClasses.enums.WeaponType;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("root")
public class SmallHumanBeing {
    //private String name = "Petya"; //Поле не может быть null, Строка не может быть пустой
    //private Coordinates coordinates = new Coordinates(1,2.0);
    private boolean realHero = true; //Поле не может быть null
    private boolean hasToothpick = true; //Поле может быть null
    private int impactSpeed = 10; //Поле может быть null
    //private WeaponType weaponType = WeaponType.SHOTGUN; //Поле не может быть null
    //private Mood mood = Mood.CALM; //Поле не может быть null
    //private Car car = new Car("машина"); //Поле может быть null
    @Override
    public String toString(){
        return "name";
    }
}
