package commands.launcher;


import Exceptions.IdException;
import Exceptions.InputException;
import Exceptions.UnknownCommandException;
import StoredClasses.Coordinates;
import StoredClasses.HumanBeing;

import commands.abstraction.Command;
import reading.readers.OfflineReader;

import java.io.File;
import java.util.*;

import main.Main;


public class CommandsLauncher<T extends Comparable<T>> {
    public static ArrayList<File> currentScripts = new ArrayList<>();
    private final TreeSet<T> collection;

    public CommandsLauncher(TreeSet<T> collection) {
        this.collection = collection;
    }

    public void add(Object element) {
        collection.add((T) element);
    }

    public boolean addIfMin(Object element) {
        T value = (T) element;

        if (value.compareTo(collection.first()) < 0) {
            this.add(value);
            return true;
        }
        return false;
    }

    public List<HumanBeing> filterContainsName(String pattern) {
        List<HumanBeing> result = new ArrayList<>();
        for (T element : collection) {
            HumanBeing human = (HumanBeing) element;
            if (human.getName().contains(pattern)) result.add(human);
        }
        return result;
    }

    public HashMap<Coordinates, List<HumanBeing>> groupCountingByCoordinates() {
        HashMap<Coordinates, List<HumanBeing>> groups = new HashMap<>();
        for (T element : collection) {
            HumanBeing human = (HumanBeing) element;
            if (groups.containsKey(human.getCoordinates())) {
                groups.get(human.getCoordinates()).add(human);
                continue;
            }
            var value = new ArrayList<HumanBeing>();
            value.add(human);
            groups.put(human.getCoordinates(), value);
        }
        return groups;

    }

    public void info() {
        System.out.println("TreeSet " + collection + " of size " + collection.size());// возможно стоит вывести еще какую-то информацию
    }

    public void removeById(long id) throws IdException {
        for (var x : collection) {
            HumanBeing human = (HumanBeing) x;
            if (human.getId() == id) {
                collection.remove(x);
                return;
            }
        }
        throw new IdException("Not valid id");
    }

    public void removeLower(Object obj) {
        T element = (T) obj;
        T lower = collection.lower(element);
        while (lower != null) {
            collection.remove(lower);
            lower = collection.lower(element);
        }
    }

    public void removeGreater(Object obj) {
        T element = (T) obj;
        T greater = collection.higher(element);
        while (greater != null) {
            collection.remove(greater);
            greater = collection.higher(element);
        }
    }

    public void show() {
        System.out.println("Collection:");
        for (var item : collection) {
            System.out.println(item);
        }
        System.out.println("Collection ended");
    }

    public double sumOfImpactSpeed() {
        double sum = 0.0;
        for (var element : collection) {
            HumanBeing human = (HumanBeing) element;
            sum += human.getImpactSpeed();
        }
        return sum;
    }

    public void update(long id, Object element) throws IdException {
        for (var item : collection) {
            HumanBeing human = (HumanBeing) item;
            if (human.getId() == id) {
                human.update((HumanBeing) element);
                return;
            }
        }
        throw new IdException("Not valid id");
    }

    public void clear() {
        collection.clear();
    }
    public void execute_script(OfflineReader reader){

        while (reader.hasNext()) {
            Command met = null;
            try {
                met = reader.readCommand();
            } catch (UnknownCommandException e) {
                System.out.println("No such command: " + e.getMessage() + " command was skipped");
            }
            try{
                if (met != null) met.execute();
            } catch (InputException e){
                System.out.println(e.getMessage() + " Command " + met + " was skipped");
                reader.skipTillNextCommand();
            }
        }
    }

    public void save() {
        Main.XMLInput.writeArr(new ArrayList<HumanBeing>((Collection<? extends HumanBeing>) collection));
    }
}
