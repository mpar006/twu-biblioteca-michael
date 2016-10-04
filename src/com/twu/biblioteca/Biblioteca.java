package com.twu.biblioteca;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by root on 26/09/16.
 */
public class Biblioteca {

    private Library l;
    private PrintStream out;
    private InputStream in;

    public Biblioteca(Library library, OutputStream outStream, InputStream inputStream) {
        l = library;
        out = new PrintStream(outStream);
        in = inputStream;
    }

    public void start() {
        welcome();
        processCmds();
    }

    public void welcome() {
        out.println("Welcome to Biblioteca");
    }

    public void processCmds() {
        Scanner scanner = new Scanner(in);
        scanner.useDelimiter(System.getProperty("line.separator"));
        String input;
        while(scanner.hasNext()) {
            input = scanner.next();
            String cmd = getCmd(input);
            String args = getArguments(input);
            Command.retrieveByInput(cmd).process(out, l, args);
        }
    }

    private String getCmd(String input) {
        String[] split = input.split(" ");
        if(split.length <= 2) {
            return input;
        }
        return arrayToString(Arrays.copyOfRange(split, 0, 2));
    }

    private String getArguments(String input) {
        String[] split = input.split(" ");
        if(split.length > 2) {
            return arrayToString(Arrays.copyOfRange(split, 2, split.length));
        }
        return input;
    }

    private String arrayToString(String[] a) {
        String s = "";
        for(int i = 0; i < a.length; i++) {
            s += a[i] + " ";
        }
        return s.trim();
    }
}
