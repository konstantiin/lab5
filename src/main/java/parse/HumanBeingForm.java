package parse;

import StoredClasses.Car;
import StoredClasses.Coordinates;
import StoredClasses.annotations.NotNull;
import StoredClasses.enums.Mood;
import StoredClasses.enums.WeaponType;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("human")
public class HumanBeingForm implements Copyable{
    private @NotNull String name;
    private @NotNull Coordinates coordinates ;
    private @NotNull Boolean realHero = true;
    private Boolean hasToothpick = true;
    private Float impactSpeed;
    private @NotNull WeaponType weaponType = WeaponType.SHOTGUN;
    private @NotNull Mood mood = Mood.CALM;
    private Car car;
    public HumanBeingForm(){
        this.coordinates = new Coordinates(0,0);
        this.car = new Car("", false);
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
