package StoredClasses;

import StoredClasses.annotations.Boundaries;
import reading.generators.CoordinatesGenerator;
import reading.generators.Generator;


/**
 * stored class
 */
public class Coordinates implements Checkable {
    private final @Boundaries(lowerBound = "-570", upperBound = "570") float x; //Максимальное значение поля: 570
    private final @Boundaries(lowerBound = "-294", upperBound = "294") long y; //Максимальное значение поля: 294

    /**
     * @param x - x coordinate
     * @param y - y coordinate
     */
    public Coordinates(float x, long y) {
        this.x = x;
        this.y = y;
    }

    /**
     * returns Coordinates generator
     *
     * @return Generator
     */
    public static Generator getGenerator() {
        return new CoordinatesGenerator();
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Coordinates)) return false;
        Coordinates cord2 = (Coordinates) o;
        return cord2.x == this.x && cord2.y == this.y;
    }

    @Override
    public int hashCode() {
        return Float.valueOf(x).hashCode() + Long.valueOf(y).hashCode();
    }
}