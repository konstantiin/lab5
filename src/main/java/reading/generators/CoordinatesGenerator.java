package reading.generators;

import StoredClasses.Coordinates;

import java.util.HashMap;

public class CoordinatesGenerator implements Generator {
    @Override
    public Object generate(HashMap<String, Object> fields) {
        return new Coordinates((float) fields.get("x"), (long) fields.get("y"));
    }
}
