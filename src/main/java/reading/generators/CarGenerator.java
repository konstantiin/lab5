package reading.generators;

import StoredClasses.Car;

import java.util.HashMap;

public class CarGenerator implements Generator {
    @Override
    public Object generate(HashMap<String, Object> fields) {
        return new Car((String) fields.get("name"), (Boolean) fields.get("cool"));
    }
}
