package reading;

import StoredClasses.annotations.Boundaries;
import StoredClasses.annotations.NotNull;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Node {
    public static List<Class<?>>leavesClasses = Arrays.asList(Boolean.class, Integer.class, Float.class, String.class, Double.class, Long.class, Short.class);
    public static Node generateTree(Object storage){
        return create(new Node(storage.getClass(), storage));
    }
    private static Node create(Node root){
        for (var field: root.getStorage().getClass().getDeclaredFields()) {

            field.setAccessible(true);
            Node next;
            try {
                next = new Node(field.getType(), field.get(root.getStorage()));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            if (field.isAnnotationPresent(NotNull.class) || field.getType().isPrimitive()) {next.setNullable(false);}
            if (field.isAnnotationPresent(Boundaries.class) || Reader.numbers.contains(field.getType())){
                var b = field.getAnnotation(Boundaries.class);
                BigDecimal l = null, u = null;
                if (b != null) {
                    l = new BigDecimal(b.lowerBound());
                    u = new BigDecimal(b.upperBound());
                }
                if (l == null || b.lowerBound().equals("")){
                    if (field.getType() == Long.class || field.getType() == long.class) l = BigDecimal.valueOf(Long.MIN_VALUE);
                    if (field.getType() == Float.class || field.getType()==float.class) l = BigDecimal.valueOf(Float.MIN_VALUE);
                    if (field.getType() == Integer.class || field.getType() == int.class) l = BigDecimal.valueOf(Integer.MIN_VALUE);
                    if (field.getType() == Short.class || field.getType() == short.class) l = BigDecimal.valueOf(Short.MIN_VALUE);
                }
                if (u == null || b.upperBound().equals("")){
                    if (field.getType() == Long.class || field.getType() == long.class) l = BigDecimal.valueOf(Long.MAX_VALUE);
                    if (field.getType() == Float.class || field.getType()==float.class) l = BigDecimal.valueOf(Float.MAX_VALUE);
                    if (field.getType() == Integer.class || field.getType() == int.class) l = BigDecimal.valueOf(Integer.MAX_VALUE);
                    if (field.getType() == Short.class || field.getType() == short.class) l = BigDecimal.valueOf(Short.MAX_VALUE);
                }
                next.setLowerBound(l);
                next.setUpperBound(u);
            }
            root.addField(next);

            if (field.getType().isEnum() || leavesClasses.contains(field.getType()) || field.getType().isPrimitive()) {
                continue;
            }
            create(next);

        }
        return root;

    }
    private final String name;
    private final Class<?> type;
    private final Object storage;
    private BigDecimal lowerBound= null;
    private BigDecimal upperBound=null;
    private boolean nullable = false;
    private boolean isNull = false;
    private final List<Node> fields = new ArrayList<>();
    public Node(Class<?> type, Object storage){
        this.type = type;
        this.storage = storage;
        this.name = type.getName();
    }
    public void setLowerBound(BigDecimal lowerBound){
        this.lowerBound = lowerBound;
    }
    public void setUpperBound(BigDecimal upperBound){
        this.upperBound = upperBound;
    }
    public void setNullable(boolean nullable){
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
