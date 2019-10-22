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
        String clean = "";
        String[] inputSplit = input.split(" ");
        for (int i = 0; i < inputSplit.length; i++) {
            String s = inputSplit[i];
            if (checkTag(s) || checkHash(s) || checkURL(s) || checkRT(s) || checkrt(s) || checkNum(s)) {
                continue;
            }
            s = removeElipsis4(s);
            s = removeElipsis3(s);
            s = removeHyphen(s);
            s = removePunct(s);
            if (i == inputSplit.length - 1) {
                clean += s;
            } else {
                clean += s + " ";
            }
        }
        if (clean == null || clean.isEmpty()) {
            return null;
        }
        return clean;
    }

    private Boolean checkTag(String s) {
        return s.contains("@");
    }

    private Boolean checkHash(String s) {
        return s.contains("#");
    }

    private Boolean checkURL(String s) {
        return s.contains("https://");
    }

    private Boolean checkRT(String s) {
        return s.contains("RT");
    }

    private Boolean checkrt(String s) {
        return s.contains("rt");
    }

    private Boolean checkNum(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (checkNum(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private String removeElipsis3(String s) {
        if (s.contains("...")) {
            return s.replace("...", "");
        }
        return s;
    }

    private String removeElipsis4(String s) {
        if (s.contains("....")) {
            return s.replace("....", "");
        }
        return s;
    }

    private String removeHyphen(String s) {
        if (s.contains("-")) {
            return s.replace("-", "");
        }
        return s;
    }

    private String removePunct(String s) {
        String  x = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // ' ! ? letter
            if (c==32) {
                continue;
            }
            if (c != 39 && c != 33 && c != 63 && !checkLetter(c) && c!= 8217) {
                continue;
            }
            if (c == 33 || c == 63) {
                if (!(i == s.length() - 1)) {
                    continue;
                }
            }
            if (c == 39 || c == 8217) {
                if (i!=s.length()-2 || i!=s.length()-1) {
                    continue;
                }
            }
            x += c;
        }
        return x;
    }

    private boolean checkLetter(int c) {
        return (c > 64 && c < 91) || (c > 96 && c < 123);
    }

    private boolean checkNum(int c) {
        return (c > 47 && c < 58);
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
