package reading.generators;

import java.util.HashMap;

public interface Generator {
    public Object generate(HashMap<String, Object> fields);
}
