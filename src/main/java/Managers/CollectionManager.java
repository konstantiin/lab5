package Managers;


import java.util.TreeSet;

public class CollectionManager<T> {
    TreeSet<T> collection;
    public CollectionManager(TreeSet<T> collection){
        this.collection = collection;
    }
    public void Add(Object element){
        collection.add((T)element);
        System.out.println("element added");
    }
    public void Clear(){collection.clear();}
}
