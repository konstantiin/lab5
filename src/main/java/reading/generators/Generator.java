package reading.generators;

import java.util.HashMap;

public interface Generator {
    Object generate(HashMap<String, Object> fields);
}
