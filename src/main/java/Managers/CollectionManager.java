package Managers;


import StoredClasses.Coordinates;
import StoredClasses.HumanBeing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;
import java.util.function.DoubleToIntFunction;

public class CollectionManager<T extends Comparable<T>> {
    TreeSet<T> collection;
    public CollectionManager(TreeSet<T> collection){
        this.collection = collection;
    }
    public void add(Object element){
        collection.add((T)element);
        System.out.println("element added");
    }
    public void addIfMin(Object element){
        T value = (T) element;

        if (value.compareTo(collection.first()) < 0) this.add(value);
    }
    public void filterContainsName(String pattern){
        for (T element: collection){
            HumanBeing human = (HumanBeing) element;
            if (human.getName().contains(pattern)) System.out.println(human);
        }
    }
    public void groupCountingByCoordinates(){
        HashMap<Coordinates, List<HumanBeing>> groups = new HashMap<>();
        for (T element: collection){
            HumanBeing human = (HumanBeing) element;
            if (groups.containsKey(human.getCoordinates())){
                groups.get(human.getCoordinates()).add(human);
                continue;
            }
            var value = new ArrayList<HumanBeing>();
            value.add(human);
            groups.put(human.getCoordinates(), value);
        }
        int count = 1;
        for (Coordinates key: groups.keySet()){
            System.out.println("Group №" + count + ":");
            for (HumanBeing human: groups.get(key)){
                System.out.println(human);
                System.out.println();
            }
            System.out.println("Group №" + count + " ended");
        }
    }
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
