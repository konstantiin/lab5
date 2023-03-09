package reading;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Node {
    public static Node generateTree(Object o){
        Node root = new Node(o.getClass(), o, o.getClass().getName(), null, null, false);

        return root;

    }
    private final String name;
    private final Class<?> type;
    private final Object storage;
    private final BigDecimal lowerBound;
    private final BigDecimal upperBound;
    private final boolean nullable;
    private final List<Node> fields = new ArrayList<>();
    public Node(Class<?> type, Object storage, String name, BigDecimal l, BigDecimal u, boolean nullable){
        this.type = type;
        this.storage = storage;
        this.name = name;
        this.lowerBound = l;
        this.upperBound = u;
        this.nullable = nullable;
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
    public BigDecimal getLowerBound(){
        return this.lowerBound;
    }
    public BigDecimal getUpperBound(){
        return this.upperBound;
    }
    public boolean ifNullable(){return nullable;}

}
