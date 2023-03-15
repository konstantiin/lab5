package reading;

import StoredClasses.annotations.Boundaries;
import StoredClasses.annotations.NotNull;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Node {
    public static List<Class<?>>leavesClasses = Arrays.asList(Boolean.class, Integer.class, Float.class, String.class, Double.class, Long.class, Short.class);
    private static Generator getGenerator(Class <?> type){
        String name;
        var temp = type.getName().split("[.]");
        name = "reading." + temp[temp.length-1] + "Generator";
        try {
            return (Generator) Class.forName(name).newInstance();// временно
        } catch (InstantiationException|IllegalAccessException|ClassNotFoundException e) {
            System.out.println(name);
            throw new RuntimeException(e);
        }
    }
    public static Node generateTree(Class<?> type, String name){
        Node root = new Node(type, name);
        root.setObjectGenerator(getGenerator(type));
        return create(root);
    }
    private static Node create(Node root){
        for (var field: root.getType().getDeclaredFields()) {

            field.setAccessible(true);
            Node next = new Node(field.getType(), field.getName());

            if (field.isAnnotationPresent(NotNull.class) || field.getType().isPrimitive()) {next.setNullable(false);}
            else next.setNullable(true);
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
                    if (field.getType() == Long.class || field.getType() == long.class) u = BigDecimal.valueOf(Long.MAX_VALUE);
                    if (field.getType() == Float.class || field.getType()==float.class) u = BigDecimal.valueOf(Float.MAX_VALUE);
                    if (field.getType() == Integer.class || field.getType() == int.class) u = BigDecimal.valueOf(Integer.MAX_VALUE);
                    if (field.getType() == Short.class || field.getType() == short.class) u = BigDecimal.valueOf(Short.MAX_VALUE);
                }
                next.setLowerBound(l);
                next.setUpperBound(u);
            }

            root.addField(next);
            if (field.getType().isEnum() || leavesClasses.contains(field.getType()) || field.getType().isPrimitive()) {
                continue;
            }

            next.setObjectGenerator(getGenerator(next.getType()));
            create(next);

        }
        return root;

    }
    private final String name;
    private final Class<?> type;
    private Generator objectGenerator;
    private BigDecimal lowerBound= null;
    private BigDecimal upperBound=null;
    private boolean nullable = false;

    private final List<Node> fields = new ArrayList<>();
    public Node(Class<?> type, String name){
        this.type = type;
        this.name = name;
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
    public void setObjectGenerator(Generator obj){
        this.objectGenerator = obj;
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
    public BigDecimal getLowerBound(){
        return this.lowerBound;
    }
    public BigDecimal getUpperBound(){
        return this.upperBound;
    }
    public Generator getObjectGenerator(){return objectGenerator;}
    public boolean ifNullable(){return nullable;}
    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(objectGenerator);
        res.append("\n");
        for (var x: fields){
            res.append("\t").append(x.toString());
        }
        return res.toString();
    }

}
