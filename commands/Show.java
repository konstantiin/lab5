package commands;


import commands.interfaces.Command;

public class Show implements Command {
    private final Iterable<?> collection;
    public Show(Iterable<?> col){
        collection = col;
    }
    @Override
    public boolean execute() {
        for (Object i: collection){
            System.out.println(i);
            System.out.println("_________________________________________________________");
        }
        return true;
    }
    @Override
    public String getName(){
        return "show";
    }
}
