package StoredClasses;

import parse.Copyable;

public class Coordinates implements Copyable {
    public Coordinates(float x, long y){
        this.x = x;
        this.y = y;
    }
    private float x; //Максимальное значение поля: 570
    private long y; //Максимальное значение поля: 294
    @Override
    public Coordinates deepCopy(){
        return new Coordinates(this.x, this.y);
    }
    @Override
    public String toString(){
        return "(" + x + ", " + y + ")";
    }
}