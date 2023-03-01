package parse;

import StoredClasses.Car;
import StoredClasses.Coordinates;
import StoredClasses.HumanBeing;
import StoredClasses.enums.Mood;
import StoredClasses.enums.WeaponType;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("human")
public class SmallHumanBeing {
    private String name = "Petya"; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates = new Coordinates(1,2.0);
    private Boolean realHero = true; //Поле не может быть null
    private Boolean hasToothpick = true; //Поле может быть null
    private Integer impactSpeed = 10; //Поле может быть null
    private WeaponType weaponType = WeaponType.SHOTGUN; //Поле не может быть null
    private Mood mood = Mood.CALM; //Поле не может быть null
    private Car car = new Car("машина"); //Поле может быть null
    public String getName(){return name;}
    public Coordinates getCoordinates(){return coordinates;}
    public Boolean getRealHero(){return realHero;}
    public Boolean getHasToothPick(){return hasToothpick;}
    public WeaponType getWeaponType(){return weaponType;}
    public Mood getMood(){return mood;}
    public Car getCar(){return car;}
    public Integer getImpactSpeed(){return impactSpeed;};
    @Override
    public String toString(){
        return name;
    }
}
