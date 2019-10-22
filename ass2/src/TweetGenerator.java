//package com.bham.pij.assignments.twit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class TweetGenerator {
    
    private static final int TWEET_LENGTH = 30;
    private static ArrayList<Word> words;
    private static Random random = new Random();
    
    public static void main(String[] args) throws IOException {

    	new TweetGenerator();
    	
        System.out.println("Done.");
    }
    
    public TweetGenerator() throws IOException {

        ArrayList<String> cleaned = loadData();
        
        words = findWords(cleaned);
        
        System.out.println(createTweet(TWEET_LENGTH));
    }
    
    private ArrayList<String> loadData() throws IOException {
        
        ArrayList<String> data = new ArrayList<String>();
        
        BufferedReader br = new BufferedReader(new FileReader(new File("cleaned.txt")));
        
        String line = "";
        
        while ((line = br.readLine())!= null) {
            
            String[] tokens = line.split(" ");
            
            for (String t: tokens) {
                data.add(t);
            }
        }
        
        br.close();
        
        return data;
        
    }
    
    public String createTweet(int numWords) {
        
        return null;
    }
    
    private Word getWord(String word) {
        
        for (Word w: words) {
            if (w.getWord().equalsIgnoreCase(word)) {
                return w;
            }
        }
        return null;
    }
    
    public ArrayList<Word> findWords(ArrayList<String> cleaned) {
        
        return null;
    }    
}
