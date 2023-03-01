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

import java.util.List;


public class ParseXml {
    private final InputStreamReader input;
    public ParseXml(String path){
        try {
            input = new InputStreamReader(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);//change rights
        }
    }
    public List<HumanBeing> parseToArr() {
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
        List<SmallHumanBeing> beings = ((HumanVec)stream.fromXML(input)).getArr();
        List<HumanBeing> result = new ArrayList<>();
        for (SmallHumanBeing i: beings){
            result.add(new HumanBeing(i));
        }
        System.out.println(result);
        return result;
    }
}
