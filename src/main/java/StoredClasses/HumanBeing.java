package StoredClasses;

import StoredClasses.annotations.AutoGenerated;
import StoredClasses.annotations.NotNull;
import StoredClasses.enums.Mood;
import StoredClasses.enums.WeaponType;
import StoredClasses.forms.HumanBeingForm;

import java.time.LocalDateTime;

public class HumanBeing implements Comparable<HumanBeing> {
    public static long idCounter = 1;
    private final @AutoGenerated long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private final @AutoGenerated LocalDateTime creationDate;  //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private @NotNull String name; //Поле не может быть null, Строка не может быть пустой
    private @NotNull Coordinates coordinates; //Поле не может быть null
    private @NotNull Boolean realHero; //Поле не может быть null
    private Boolean hasToothpick; //Поле может быть null
    private Float impactSpeed; //Поле может быть null
    private @NotNull WeaponType weaponType; //Поле не может быть null
    private @NotNull Mood mood; //Поле не может быть null
    private Car car; //Поле может быть null
    public HumanBeing(HumanBeingForm h) {
        this.name = h.getName();
        this.mood = h.getMood();
        this.weaponType = h.getWeaponType();
        this.impactSpeed = h.getImpactSpeed();
        this.hasToothpick = h.getHasToothPick();
        this.realHero = h.getRealHero();
        this.coordinates = h.getCoordinates();
        this.car = h.getCar();
        this.id = idCounter;     /// возможно стоит переделать
        idCounter++;
        this.creationDate = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public long getId() {
        return id;
    }

    public Float getImpactSpeed() {
        return impactSpeed;
    }

    public Mood getMood() {
        return mood;
    }

    public boolean isRealHero() {
        return realHero;
    }

    public Boolean ifHasToothpick() {
        return hasToothpick;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public void update(HumanBeing obj) {
        this.name = obj.getName();
        this.car = obj.car;
        this.coordinates = obj.getCoordinates();
        this.mood = obj.getMood();
        this.realHero = obj.isRealHero();
        this.hasToothpick = obj.ifHasToothpick();
        this.impactSpeed = obj.getImpactSpeed();
        this.weaponType = obj.getWeaponType();
    }

    @Override
    public String toString() {
        return "HumanBeing {\n" +
                "\tid = " + id + "\n" +
                "\tname = " + name + "\n" +
                "\tcoordinates = " + coordinates + "\n" +
                "\tcreationDate = " + creationDate + "\n" +
                "\trealHero = " + realHero + "\n" +
                "\thasToothpick = " + hasToothpick + "\n" +
                "\timpactSpeed = " + impactSpeed + "\n" +
                "\tweaponType = " + weaponType + "\n" +
                "\tmood = " + mood + "\n" +
                "\tcar = " + car + "\n" +
                "}";

    }

    @Override
    public int compareTo(HumanBeing o) {
        return this.name.compareTo(o.name);
    }
}