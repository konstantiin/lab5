package StoredClasses;

public class Coordinates {
    public Coordinates(float x, Double y){
        this.x = x;
        this.y = y;
    }
    private float x; //Максимальное значение поля: 570
    private Double y; //Максимальное значение поля: 294, Поле не может быть null
    @Override
    public String toString(){
        return "(" + x + ", " + y + ")";
    }
}