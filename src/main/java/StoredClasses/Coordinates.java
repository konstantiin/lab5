package StoredClasses;

import StoredClasses.annotations.Boundaries;

public class Coordinates {
    public Coordinates(float x, long y){
        this.x = x;
        this.y = y;
    }
    private final @Boundaries(lowerBound = "-570", upperBound = "570") float x; //Максимальное значение поля: 570
    private final @Boundaries(lowerBound = "-294", upperBound = "294") long y; //Максимальное значение поля: 294

    @Override
    public String toString(){
        return "(" + x + ", " + y + ")";
    }
}