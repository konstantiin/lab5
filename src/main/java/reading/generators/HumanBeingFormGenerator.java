package reading.generators;

import StoredClasses.Car;
import StoredClasses.Coordinates;
import StoredClasses.enums.Mood;
import StoredClasses.enums.WeaponType;
import StoredClasses.forms.HumanBeingForm;

import java.util.HashMap;

public class HumanBeingFormGenerator implements Generator {
    @Override
    public Object generate(HashMap<String, Object> fields) {
        return new HumanBeingForm((String) fields.get("name"), (Coordinates) fields.get("coordinates"), (Boolean) fields.get("realHero"),
                (Boolean) fields.get("HasToothpick"), (Float) fields.get("impactSpeed"), (WeaponType) fields.get("weaponType"),
                (Mood) fields.get("mood"), (Car) fields.get("car"));
    }
}