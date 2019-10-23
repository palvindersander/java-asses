//package com.bham.pij.assignments.twit;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
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
        //prelim checks
        if (input == null || input.isEmpty()) {
            return null;
        }
        String[] words = input.split(" ");
        ArrayList<String> cleanedWords = new ArrayList<>();

        //checking words
        for (String word : words) {
            if (word == null || word.isEmpty()) {
                continue;
            }
            String cleanedWord = word;
            //check if valid
            if (cleanedWord.contains("#") || cleanedWord.contains("@") || cleanedWord.contains("http") || cleanedWord.equals("RT") || cleanedWord.equals("rt")) {
                continue;
            }
            //check if contains digits
            boolean digits = false;
            for (int i = 0; i < cleanedWord.length(); i++) {
                char c = cleanedWord.charAt(i);
                if (checkNum(c)) {
                    digits = true;
                    break;
                }
            }
            if (digits) {
                continue;
            }
            //remove ellipsis
            cleanedWord = cleanedWord.replace("....", "");
            cleanedWord = cleanedWord.replace("...", "");
            //remove hyphen
            cleanedWord = cleanedWord.replace("-", "");
            //reform punctuation
            String removePuncWord = "";
            String reformedWord = "";
            for (int i = 0; i < cleanedWord.length(); i++) {
                char c = cleanedWord.charAt(i);
                //remove any unacceptable char
                if (c != 8217 && c != 39 && c != 33 && c != 63 && !checkLetter(c)) {
                    continue;
                }
                removePuncWord = removePuncWord + c;
            }
            cleanedWord = removePuncWord;
            for (int i = 0; i < cleanedWord.length(); i++) {
                char c = cleanedWord.charAt(i);
                //check exclamation
                if (c == 33) {
                    if (i == 0) {
                        continue;
                    }
                    if (!(i == cleanedWord.length() - 1)) {
                        boolean listofExclamations = true;
                        for (int x = i + 1; x < cleanedWord.length(); x++) {
                            char cExclamation = cleanedWord.charAt(x);
                            if (cExclamation != 33) {
                                listofExclamations = false;
                                break;
                            }
                        }
                        if (!listofExclamations) {
                            continue;
                        }
                    }
                }
                //check question
                if (c == 63) {
                    if (i == 0) {
                        continue;
                    }
                    if (!(i == cleanedWord.length() - 1)) {
                        boolean listofExclamations = true;
                        for (int x = i + 1; x < cleanedWord.length(); x++) {
                            char cExclamation = cleanedWord.charAt(x);
                            if (cExclamation != 63) {
                                listofExclamations = false;
                                break;
                            }
                        }
                        if (!listofExclamations) {
                            continue;
                        }
                    }
                }
                //check apostrophe
                if (c == 39 || c == 8217) {
                    if (cleanedWord.length() < 2) {
                        continue;
                    }
                    if (i != cleanedWord.length()-2 && i != cleanedWord.length()-1) {
                        continue;
                    }
                    if (i == cleanedWord.length()-2) {
                        char left = cleanedWord.charAt(i-1);
                        char right = cleanedWord.charAt(i+1);
                        if (!checkLetter(left) || !checkLetter(right)){
                            continue;
                        }
                    }
                    if (i == cleanedWord.length()-1) {
                        char left = cleanedWord.charAt(i-1);
                        if (!checkLetter(left)){
                            continue;
                        }
                    }
                }
                reformedWord = reformedWord + c;
            }
            cleanedWord = reformedWord;
            if (cleanedWord == null || cleanedWord.isEmpty()) {
                continue;
            }
            cleanedWords.add(cleanedWord);
        }

        //create cleaned string
        if (cleanedWords.size() == 0) {
            return null;
        }
        String cleanedInput = "";
        for (int i = 0; i < cleanedWords.size() - 1; i++) {
            String cleanWord = cleanedWords.get(i);
            cleanedInput = cleanedInput + cleanWord + " ";
        }
        cleanedInput = cleanedInput + cleanedWords.get(cleanedWords.size() - 1);
        return cleanedInput;
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
