package parse;

import StoredClasses.Car;
import StoredClasses.Coordinates;
import StoredClasses.HumanBeing;
import StoredClasses.enums.Mood;
import StoredClasses.enums.WeaponType;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;

public class ParseXml {
    private final InputStreamReader input;
    public ParseXml(String path){
        try {
            input = new InputStreamReader(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);//change rights
        }
    }
    public Map<String, Object> parseToMap() throws IOException {
        Map<String, Object> result = new HashMap<>();
        XStream stream = new XStream();

        stream.addPermission(AnyTypePermission.ANY);//разобраться с исключениями
        SmallHumanBeing ob = (SmallHumanBeing) stream.fromXML(input);
        System.out.println(ob);

        return result;
    }
}
