package parse;

import Exceptions.FIleDoesNotExistException;
import Exceptions.FileException;
import Exceptions.FileNotReadableException;
import Exceptions.FileNotWritableException;
import StoredClasses.HumanBeing;
import StoredClasses.forms.HumanBeingForm;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


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

        List<HumanBeingForm> beings = (List<HumanBeingForm>) stream.fromXML(xml);// хз, что делать
        List<HumanBeing> result = new ArrayList<>();
        for (HumanBeingForm i : beings) {
            result.add(new HumanBeing(i));
        }

        return result;
    }
}
