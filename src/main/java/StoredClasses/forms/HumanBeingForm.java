package StoredClasses.forms;

import StoredClasses.Car;
import StoredClasses.Coordinates;
import StoredClasses.annotations.NotNull;
import StoredClasses.enums.Mood;
import StoredClasses.enums.WeaponType;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import reading.generators.CarGenerator;
import reading.generators.Generator;
import reading.generators.HumanBeingFormGenerator;

@XStreamAlias("human")
public class HumanBeingForm {
    public static Generator getGenerator(){
        return new HumanBeingFormGenerator();
    }
    private final @NotNull String name;
    private final @NotNull Coordinates coordinates;
    private final @NotNull Boolean realHero;
    private final Boolean hasToothpick;
    private final Float impactSpeed;
    private final @NotNull WeaponType weaponType;
    private final @NotNull Mood mood;
    private final Car car;

    public HumanBeingForm(String name, Coordinates coordinates, Boolean realHero, Boolean hasToothpick, Float impactSpeed, WeaponType weaponType, Mood mood, Car car) {
        this.name = name;
        this.coordinates = coordinates;
        this.realHero = realHero;
        this.hasToothpick = hasToothpick;
        this.impactSpeed = impactSpeed;
        this.weaponType = weaponType;
        this.mood = mood;
        this.car = car;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Boolean getRealHero() {
        return realHero;
    }

    public Boolean getHasToothPick() {
        return hasToothpick;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public Mood getMood() {
        return mood;
    }

    public Car getCar() {
        return car;
    }

    public Float getImpactSpeed() {
        return impactSpeed;
    }

    @Override
    public String toString() {
        return "HumanBeing {\n" +
                "\tname = " + name + "\n" +
                "\tcoordinates = " + coordinates + "\n" +
                "\trealHero = " + realHero + "\n" +
                "\thasToothpick = " + hasToothpick + "\n" +
                "\timpactSpeed = " + impactSpeed + "\n" +
                "\tweaponType = " + weaponType + "\n" +
                "\tmood = " + mood + "\n" +
                "\tcar = " + car + "\n" +
                "}";
    }

}
