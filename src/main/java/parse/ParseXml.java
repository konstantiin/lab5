package parse;

import StoredClasses.Car;
import StoredClasses.Coordinates;
import StoredClasses.HumanBeing;
import StoredClasses.enums.Mood;
import StoredClasses.enums.WeaponType;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ParseXml {
    private final InputStreamReader input;
    public ParseXml(String path){
        try {
            input = new InputStreamReader(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);//change rights
        }
    }
    public List<SmallHumanBeing> parseToMap() {
        @XStreamAlias("elements")
        class HumanVec {
            private List<SmallHumanBeing> arr = new ArrayList<>();
            public List<SmallHumanBeing> getArr(){return arr;}
        }

        XStream stream = new XStream();
        stream.alias("human", SmallHumanBeing.class);
        stream.addPermission(AnyTypePermission.ANY);//разобраться с разрешениями
        stream.processAnnotations(SmallHumanBeing.class);
        stream.processAnnotations(HumanVec.class);
        stream.addImplicitCollection(HumanVec.class, "arr");
        List<SmallHumanBeing> result = ((HumanVec)stream.fromXML(input)).getArr();
        System.out.println(result);
        return result;
    }
}
