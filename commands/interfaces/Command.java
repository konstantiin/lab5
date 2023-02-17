package commands.interfaces;

public interface Command {
    default public void execute(){
        throw new RuntimeException("Not implemented method!");
    }
    default public String getName(){
        throw new RuntimeException("Not implemented method!");
    }
}
