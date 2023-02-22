package parse;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ParseXml {
    private final BufferedReader input;
    public ParseXml(String path){
        try {
            input = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);//change rights
        }
    }
    public Map<String, Object> parseToMap() throws IOException {
        Map<String, Object> result = new HashMap<>();
        while(input.ready()){
            String nextLine = input.readLine();
            System.out.println(nextLine);
        }
        return result;
    }
}
