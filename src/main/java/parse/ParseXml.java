package parse;

import Exceptions.*;
import StoredClasses.HumanBeing;
import StoredClasses.forms.HumanBeingForm;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ParseXml {
    private final File xml;
    private final XStream stream;

    public ParseXml(String path) throws FIleDoesNotExistException, FileNotReadableException, FileNotWritableException {

        xml = new File(path);
        if (!xml.exists()) throw new FIleDoesNotExistException();
        if (!xml.canRead()) throw new FileNotReadableException();
        if (!xml.canWrite()) throw new FileNotWritableException();
        stream = new XStream();

        stream.addPermission(AnyTypePermission.ANY);                                //разобраться с разрешениями


        stream.alias("elements", List.class);
    }
    public void writeArr(List<HumanBeing> out){
        stream.processAnnotations(HumanBeing.class);
        String result = stream.toXML(out);
        try (FileOutputStream o = new FileOutputStream(xml)){
            o.write(result.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<HumanBeing> getArr() {
        stream.processAnnotations(HumanBeingForm.class);
        List<HumanBeingForm> beings;
        try{
             beings = (List<HumanBeingForm>) stream.fromXML(xml);// хз, что делать
        } catch (ConversionException e){
            if (e.getCause() instanceof IllegalArgumentException){
                System.out.print("Collection was left empty, because of wrong input in line ");
                var message = e.getMessage().split("\\s+");
                for (int i = 0; i < message.length; i++){
                    if (Objects.equals(message[i], "number")){
                        System.out.println(message[i+2]);
                        return null;
                    }
                }
                System.out.println();
                return null;
            } else throw e;
            //return null;
        }
        List<HumanBeing> result = new ArrayList<>();
        int count = 1;
        for (HumanBeingForm i : beings) {
            try{
                result.add(new HumanBeing(i));
            } catch (InputException e){
                System.out.println("Element " + count + " is not correct, because " + e.getMessage() + " It was skipped");
            }
            count++;
        }

        return result;
    }
}
