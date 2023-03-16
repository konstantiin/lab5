package Managers;


import Exceptions.IdException;
import StoredClasses.Coordinates;
import StoredClasses.HumanBeing;

import java.util.*;
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
    public void info(){
        System.out.println("TreeSet " + collection + " of size " + collection.size());// возможно стоит вывести еще какую-то информацию
    }
    public void removeById(long id) throws IdException {
        for (var x: collection){
            HumanBeing human = (HumanBeing) x;
            if (human.getId() == id){
                collection.remove(x);
                return;
            }
        }
        throw new IdException("Not valid id");
    }
    public void removeLower(Object obj){
        T element = (T)obj;
        T lower = collection.lower(element);
        while (lower != null) {
            collection.remove(lower);
            lower = collection.lower(element);
        }
    }
    public void removeGreater(Object obj){
        T element = (T)obj;
        T greater = collection.lower(element);
        while (greater != null) {
            collection.remove(greater);
            greater = collection.higher(element);
        }
    }
    public void save(){}
    public void show(){
        System.out.println("Collection:");
        for (var item: collection){
            System.out.println(item);
        }
        System.out.println("Collection ended");
    }
    public void sumOfImpactSpeed(){
        Double sum = 0.0;
        for (var element: collection){
            HumanBeing human = (HumanBeing) element;
            sum += human.getImpactSpeed();
        }
        System.out.println("sum of impactSpeed:" + sum);
    }
    public void update(long id, Object element) throws IdException {
        for (var item: collection){
            HumanBeing human = (HumanBeing) item;
            if (human.getId() == id){
                human.update((HumanBeing)element);
                return;
            }
        }
        throw new IdException("Not valid id");
    }
    public void clear(){collection.clear();}
}
