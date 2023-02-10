import java.util.TreeSet;

public class MyTreeSet<E> extends TreeSet<E> {
    public void show(){
        for (E el: this){
            System.out.println(el);
            System.out.println("______________________________________________________\n");
        }
    }
}
