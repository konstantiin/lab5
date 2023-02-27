package parse;

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
        StringBuilder res = new StringBuilder();
        while(input.ready()) res.append((char)input.read());
        String xml = res.toString();
        return result;
    }
}
