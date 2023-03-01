package parse;

import StoredClasses.HumanBeing;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.security.AnyTypePermission;

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
            private List<HumanBeingForm> arr = new ArrayList<>();
            public List<HumanBeingForm> getArr(){return arr;}
        }

        XStream stream = new XStream();
        stream.alias("human", HumanBeingForm.class);
        stream.addPermission(AnyTypePermission.ANY);//разобраться с разрешениями
        stream.processAnnotations(HumanBeingForm.class);
        stream.processAnnotations(HumanVec.class);
        stream.addImplicitCollection(HumanVec.class, "arr");
        List<HumanBeingForm> beings = ((HumanVec)stream.fromXML(input)).getArr();
        List<HumanBeing> result = new ArrayList<>();
        for (HumanBeingForm i: beings){
            result.add(new HumanBeing(i));
        }
        System.out.println(result);
        return result;
    }
}
