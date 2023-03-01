package parse;

import StoredClasses.Car;
import StoredClasses.Coordinates;
import StoredClasses.enums.Mood;
import StoredClasses.enums.WeaponType;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("human")
public class HumanBeingForm implements Copyable{
    private String name = "Petya"; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates ;
    private Boolean realHero = true; //Поле не может быть null
    private Boolean hasToothpick = true; //Поле может быть null
    private Float impactSpeed;//Поле может быть null
    private WeaponType weaponType = WeaponType.SHOTGUN; //Поле не может быть null
    private Mood mood = Mood.CALM; //Поле не может быть null
    private Car car; //Поле может быть null
    public String getName(){return name;}
    public Coordinates getCoordinates(){return coordinates;}
    public Boolean getRealHero(){return realHero;}
    public Boolean getHasToothPick(){return hasToothpick;}
    public WeaponType getWeaponType(){return weaponType;}
    public Mood getMood(){return mood;}
    public Car getCar(){return car;}
    public Float getImpactSpeed(){return impactSpeed;}
    public void setName(String name){this.name = name;}
    public void setCoordinates(Coordinates coordinates){this.coordinates = coordinates;}
    public void setRealHero(Boolean realHero){this.realHero = realHero;}
    public void setHasToothpick(Boolean hasToothpick){this.hasToothpick = hasToothpick;}
    public void setImpactSpeed(Float impactSpeed){this.impactSpeed = impactSpeed;}
    public void setWeaponType(WeaponType type){this.weaponType = type;}
    public void setMood(Mood mood){this.mood = mood;}
    public void setCar(Car car){this.car = car;}
    @Override
    public String toString(){
        return name;
    }
    @Override
    public HumanBeingForm deepCopy(){
        HumanBeingForm copy = new HumanBeingForm();
        copy.setCar(car.deepCopy());
        copy.setCoordinates(coordinates.deepCopy());
        copy.setMood(this.mood);
        copy.setName(this.name);
        copy.setHasToothpick(this.hasToothpick);
        copy.setImpactSpeed(this.impactSpeed);
        copy.setRealHero(realHero);
        copy.setWeaponType(this.weaponType);
        return copy;
    }
}
