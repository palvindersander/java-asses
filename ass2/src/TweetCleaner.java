//package com.bham.pij.assignments.twit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TweetCleaner {

    private static ArrayList<String> raw = new ArrayList<String>();
    private static ArrayList<String> cleaned = new ArrayList<String>();

    public static void main(String[] args) throws IOException {

        new TweetCleaner();

        System.out.println("Done.");
    }

    public TweetCleaner() throws IOException {

        loadRaw();

        clean();

        saveClean();
    }

    private void clean() {
        for (String line : raw) {

            String cln = clean(line);

            if (cln != null) {

                String[] toks = cln.split(" ");

                for (String s : toks) {
                    addClean(s);
                }
            }
        }
    }

    public String clean(String input) {
        //do stuff here
        return null;
    }


    private void addClean(String clean) {

        cleaned.add(clean);
    }

    private void saveClean() throws FileNotFoundException {

        PrintWriter pw = new PrintWriter("cleaned.txt");

        for (String s : cleaned) {
            pw.print(s + " ");
        }

        pw.close();

    }

    private void loadRaw() throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(new File("donald.txt")));

        String line = "";

        while ((line = br.readLine()) != null) {

            raw.add(line);
        }

        br.close();
    }
}
