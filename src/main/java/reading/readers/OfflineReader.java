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
    private String currentLine = null, nextLine = null;

    public OfflineReader(FileInputStream file, CommandsLauncher<?> col, Node tree) {
        super(file, col, tree);
        currentLine = scan.nextLine();
        nextLine = scan.nextLine();
    }

    @Override
    protected String getNext() {
        String buf = currentLine;
        this.readLine();
        Scanner s = new Scanner(buf);
        skip(s);
        try {
            return s.next();
        } catch (Exception e) {
            System.out.println(buf);
            throw e;
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
        currentLine = nextLine;
        try {
            nextLine = scan.nextLine();
        } catch (NoSuchElementException e) {
            nextLine = null;
        }
    }

    @Override
    public boolean hasNext() {
        return currentLine != null;
    }

    @Override
    protected boolean readNull(Node v) {
        Scanner s = new Scanner(currentLine);
        s.skip(skipPattern);
        boolean x;
        try {
            x = s.next().trim().equals("");
        } catch (NoSuchElementException e) {
            x = true;
        }
        boolean y = nextLine.startsWith(StringUtils.repeat("\t", tabs + 1));
        return x && !y;
    }

    @Override
    public Object readObject() {
        tabs = -1;
        return readTree(objectTree);
    }
}
