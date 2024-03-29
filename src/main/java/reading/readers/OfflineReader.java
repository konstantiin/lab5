package reading.readers;


import commands.launcher.CommandsLauncher;
import org.apache.commons.lang3.StringUtils;
import reading.objectTree.Node;

import java.io.FileInputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class OfflineReader extends Reader {
    /**
     * pattern to be skipped while reading
     */
    private final Pattern skipPattern = Pattern.compile("\\s*[a-zA-Z_0-9ёЁа-яА-Я]*:");
    /**
     * stores next line
     */
    private String nextLine;
    /**
     * Scanner of current line
     */
    private Scanner currentLineScanner;

    /**
     * @param file - input file
     * @param col  - managed collection
     * @param tree - object tree to read
     */
    public OfflineReader(FileInputStream file, CommandsLauncher<?> col, Node tree) {
        super(file, col, tree);
        currentLineScanner = new Scanner(scan.nextLine());
        skip(currentLineScanner);
        try {
            nextLine = scan.nextLine();
        } catch (NoSuchElementException e) {
            nextLine = null;
        }
    }

    @Override
    protected String getNext() {
        if (currentLineScanner.hasNext()) {
            var res = currentLineScanner.next();
            if (!currentLineScanner.hasNext()) readLine();
            return res;
        } else {
            return "";
        }
    }

    /**
     * applies skipPattern to certain Scanner
     * @param s - input scanner
     */
    private void skip(Scanner s) {
        try {
            s.skip(skipPattern);
        } catch (NoSuchElementException ignored) {
        }
    }

    @Override
    protected void readLine() {
        try {
            currentLineScanner = new Scanner(nextLine);
            skip(currentLineScanner);
        } catch (Exception e) {
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

    /**
     * skips input until next command appears, or file ends
     */
    public void skipTillNextCommand() {
        if (currentLineScanner == null) return;
        for (String c : commands.keySet()) {
            if (currentLineScanner.hasNext(c)) return;
        }
        this.readLine();
        skipTillNextCommand();
    }

    @Override
    protected boolean readNull(Node v) {
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
