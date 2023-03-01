package reading;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private final String name;
    private final Class<?> type;
    private final Object storage;
    private final List<Node> fields = new ArrayList<>();
    public Node(Class<?> type, Object storage, String name){
        this.type = type;
        this.storage = storage;
        this.name = name;
    }
    public void addField(Node field){
        fields.add(field);
    }
    public List<Node> getFields(){
        return fields;
    }
    public String getName(){
        return this.name;
    }
    public Class<?> getType(){return type;}
    public Object getStorage(){return storage;}

}
