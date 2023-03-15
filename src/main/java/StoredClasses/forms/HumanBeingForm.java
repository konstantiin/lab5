package StoredClasses.forms;

import StoredClasses.Car;
import StoredClasses.Coordinates;
import StoredClasses.annotations.NotNull;
import StoredClasses.enums.Mood;
import StoredClasses.enums.WeaponType;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("human")
public class HumanBeingForm {
    private @NotNull String name;
    private @NotNull Coordinates coordinates ;
    private @NotNull Boolean realHero;
    private Boolean hasToothpick;
    private Float impactSpeed;
    private @NotNull WeaponType weaponType ;
    private @NotNull Mood mood;
    private Car car;
    public HumanBeingForm(String name, Coordinates coordinates, Boolean realHero, Boolean hasToothpick,  Float impactSpeed, WeaponType weaponType,  Mood mood, Car car){
        this.name = name;
        this.coordinates = coordinates;
        this.realHero = realHero;
        this.hasToothpick = hasToothpick;
        this.impactSpeed = impactSpeed;
        this.weaponType = weaponType;
        this.mood = mood;
        this.car = car;
    }
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
        return "HumanBeing {\n" +
                "\tname = " + name + "\n"+
                "\tcoordinates = " + coordinates + "\n"+
                "\trealHero = " + realHero + "\n"+
                "\thasToothpick = " + hasToothpick + "\n"+
                "\timpactSpeed = " + impactSpeed + "\n" +
                "\tweaponType = " + weaponType + "\n"+
                "\tmood = " + mood + "\n"+
                "\tcar = " + car + "\n" +
                "}";
    }

}
