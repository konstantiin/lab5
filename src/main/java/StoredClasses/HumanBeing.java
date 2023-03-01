package StoredClasses;

import StoredClasses.enums.*;
import parse.SmallHumanBeing;

import java.time.LocalDateTime;
import java.util.Random;

public class HumanBeing implements Comparable<HumanBeing> {
    public static int idCounter = 1;
    public HumanBeing(SmallHumanBeing h){
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
    private final int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private final String name; //Поле не может быть null, Строка не может быть пустой
    private final Coordinates coordinates; //Поле не может быть null
    private final LocalDateTime creationDate;  //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private final Boolean realHero; //Поле не может быть null
    private final Boolean hasToothpick; //Поле может быть null
    private final Integer impactSpeed; //Поле может быть null
    private final WeaponType weaponType; //Поле не может быть null
    private final Mood mood; //Поле не может быть null
    private final Car car; //Поле может быть null
    @Override
    public String toString(){
        return "HumanBeing {\n" +
                    "\tid = " + id + "\n" +
                    "\tname = " + name + "\n"+
                    "\tcoordinates = " + coordinates + "\n"+
                    "\tcreationDate = " + creationDate + "\n"+
                    "\trealHero = " + realHero + "\n"+
                    "\thasToothpick = " + hasToothpick + "\n"+
                    "\timpactSpeed = " + impactSpeed + "\n" +
                    "\tweaponType = " + weaponType + "\n"+
                    "\tmood = " + mood + "\n"+
                    "\tcar = " + car + "\n" +
                "}";

    }

    @Override
    public int compareTo(HumanBeing o) {
        return o.id - this.id;
    }
}