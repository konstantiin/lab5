package Managers;


import java.util.TreeSet;

public class CollectionManager<T> {
    TreeSet<T> collection;
    public CollectionManager(TreeSet<T> collection){
        this.collection = collection;
    }
    public void add(Object element){
        collection.add((T)element);
        System.out.println("element added");
    }
    public void addIfMin(Object element){}
    public void filterContainsName(String pattern){}
    public void groupCountingByCoordinates(){}
    public void info(){}
    public void removeById(int id){}
    public void removeLower(Object element){}
    public void removeGreater(Object element){}
    public void save(){}
    public void show(){}
    public void sumOfImpactSpeed(){}
    public void update(int id, Object element){}
    public void clear(){collection.clear();}
}
