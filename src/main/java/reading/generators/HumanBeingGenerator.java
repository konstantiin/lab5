package reading.generators;

import StoredClasses.Car;
import StoredClasses.Coordinates;
import StoredClasses.HumanBeing;
import StoredClasses.enums.Mood;
import StoredClasses.enums.WeaponType;
import StoredClasses.forms.HumanBeingForm;

import java.util.HashMap;

/**
 * class to generate HumanBeing
 */
public class HumanBeingGenerator implements Generator {
    /**
     * @param fields - object fields
     * @return new HumanBeing object
     */
    @Override
    public Object generate(HashMap<String, Object> fields) {
        var form = new HumanBeingForm((String) fields.get("name"), (Coordinates) fields.get("coordinates"), (Boolean) fields.get("realHero"),
                (Boolean) fields.get("hasToothpick"), (Float) fields.get("impactSpeed"), (WeaponType) fields.get("weaponType"),
                (Mood) fields.get("mood"), (Car) fields.get("car"));
        return new HumanBeing(form);
    }
}
