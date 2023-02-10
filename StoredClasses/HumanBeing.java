package StoredClasses;

import StoredClasses.enums.*;

import java.time.LocalDateTime;

public class HumanBeing implements Comparable<HumanBeing> {
    public HumanBeing(){

    }
    private int id = 10; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name = "Petya"; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates = new Coordinates(1,2.0); //Поле не может быть null
    private LocalDateTime creationDate = LocalDateTime.now(); //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Boolean realHero = true; //Поле не может быть null
    private Boolean hasToothpick = true; //Поле может быть null
    private Integer impactSpeed = 10; //Поле может быть null
    private WeaponType weaponType = WeaponType.SHOTGUN; //Поле не может быть null
    private Mood mood = Mood.CALM; //Поле не может быть null
    private Car car = new Car("машина"); //Поле может быть null
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