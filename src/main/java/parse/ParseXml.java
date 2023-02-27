package parse;

import StoredClasses.HumanBeing;
import com.thoughtworks.xstream.XStream;

import java.io.*;
import java.util.HashMap;
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
    public Map<String, Object> parseToMap() throws IOException {
        Map<String, Object> result = new HashMap<>();
        XStream stream = new XStream();
        HumanBeing ob = (HumanBeing) stream.fromXML(input);
        System.out.println(ob);

        return result;
    }
}
