package reading;

import Exceptions.UnknownCommandException;
import StoredClasses.HumanBeing;
import commands.*;
import commands.interfaces.Command;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.TreeSet;

public class Reader {
    private final Scanner scan;
    private final Deque<Command> history = new ArrayDeque<>();
    private final TreeSet<HumanBeing> collection;
    public Reader (Scanner scan, TreeSet<HumanBeing> col){
        this.scan = scan;
        collection =col;
    }
    private Command historyUpd(Command c){
        history.addFirst(c);
        if (history.size() > 9) history.removeLast();
        return c;
    }

    public Deque<Command> getHistory() {
        return history;
    }

    public Command ReadCommand() throws Exception {
        String metName = this.scan.nextLine();
        switch (metName){
            case "help":
                return historyUpd(new Help());
            case "info":
                return historyUpd(new Info(collection));
            case "show":
                return historyUpd(new Show(collection));
            case "add":
                return historyUpd(new Add(collection));
            case "update_id":
                return historyUpd(new UpdateId(collection));
            case "remove_by_id":
                return historyUpd(new RemoveById(collection));
            case "clear":
                return historyUpd(new Clear(collection));
            case "save":
                return historyUpd(new Save(collection));
            case "execute_script":
                return historyUpd(new ExecuteScript());
            case "exit":
                return historyUpd(new Exit());
            case "remove_first":
                return historyUpd(new RemoveFirst(collection));
            case "add_if_max":
                return historyUpd(new AddIfMax(collection));
            case "history":
                return historyUpd(new History(this));
            case "filter_greater_than_command":
                return historyUpd(new FilterGreaterThanHeight(collection));
            case "print_ascending":
                return historyUpd(new PrintAscending(collection));
            case "print_unique_nationality":
                return historyUpd(new PrintUniqueNationality(collection));
        }
        throw new UnknownCommandException("Command not found");
    }
    public void closeStream(){
        scan.close();
    }
}
