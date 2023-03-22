package reading.readers;


import commands.launcher.CommandsLauncher;
import org.apache.commons.lang3.StringUtils;
import reading.objectTree.Node;

import java.io.FileInputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class OfflineReader extends Reader {
    private final Pattern skipPattern = Pattern.compile("\\s*\\w*:");
    private String nextLine;
    private Scanner currentLineScanner;
    public OfflineReader(FileInputStream file, CommandsLauncher<?> col, Node tree) {
        super(file, col, tree);
        currentLineScanner = new Scanner(scan.nextLine());
        try{
            nextLine = scan.nextLine();
        } catch (NoSuchElementException e) {
            nextLine = null;
        }
    }

    @Override
    protected String getNext() {
        skip(currentLineScanner);//мб не надо
        if (currentLineScanner.hasNext()) {
            var res = currentLineScanner.next();
            if (!currentLineScanner.hasNext()) readLine();
            return res;
        }
        else{
            return "";
        }
    }

    private void skip(Scanner s) {
        try {
            s.skip(skipPattern);
        } catch (NoSuchElementException ignored) {
            //ignored.printStackTrace();
        }
    }

    @Override
    public void readLine() {
        try{
            currentLineScanner = new Scanner(nextLine);
        } catch (Exception e){
            currentLineScanner = null;
        }
        try {
            nextLine = scan.nextLine();
        } catch (NoSuchElementException e) {
            nextLine = null;
        }

    }

    @Override
    public boolean hasNext() {
        return currentLineScanner != null;
    }
    public void skipTillNextCommand(){
        if (currentLineScanner == null) return;
        for (String c: commands.keySet()){
            if (currentLineScanner.hasNext(c)) return;
        }
        this.readLine();
        skipTillNextCommand();
    }

    @Override
    protected boolean readNull(Node v) {
        skip(currentLineScanner);//мб не надо
        boolean x = currentLineScanner.hasNext();
        boolean y = nextLine.replaceAll(" {4}", "\t").startsWith(StringUtils.repeat("\t", tabs + 1));
        return !x && !y;
    }

    @Override
    protected boolean checkNotNullObject() {
        return !nextLine.replaceAll(" {4}", "\t").startsWith(StringUtils.repeat("\t", tabs + 1));
    }


    @Override
    public Object readObject() {
        tabs = -1;
        return readTree(objectTree);
    }
}
