package commands;

import commands.interfaces.Command;

import reading.Reader;


public class History implements Command {
    private final Reader reader;
    public History(Reader reader){
        this.reader = reader;
    }
    @Override
    public void execute(){
        for (Command c: reader.getHistory()){
            System.out.println(c.getName());
        }
    }
    @Override
    public String getName(){
        return "history";
    }
}
