//package com.bham.pij.assignments.twit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
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

        while ((line = br.readLine()) != null) {

            String[] tokens = line.split(" ");

            for (String t : tokens) {
                data.add(t);
            }
        }

        br.close();

        return data;

    }

    public String createTweet(int numWords) {
        String[] tweetWords = new String[numWords];
        Word currentWord = null;
        for (int i = 0; i < numWords; i++) {
            if (currentWord == null) {
                if (words.size() == 0) {
                    return null;
                }
                if (words.size() == 1) {
                    currentWord =  words.get(0);
                }
                else {
                    int r = random.nextInt(words.size() - 1);
                    currentWord = words.get(r);
                }
            }
            tweetWords[i] = currentWord.getWord();
            currentWord = getWord(currentWord.getRandomFollower(),words);
        }
        String tweet = "";
        for (int i = 0; i < tweetWords.length; i++) {
            String word = tweetWords[i];
            if (tweetWords.length == 0) {
                return null;
            }
            if (i == tweetWords.length-1) {
                tweet = tweet + word;
            }
            else {
                tweet = tweet + word + " ";
            }
        }
        return tweet;
    }

    private Word getWord(String word) {

        for (Word w : words) {
            if (w.getWord().equalsIgnoreCase(word)) {
                return w;
            }
        }
        return null;
    }

    private Word getWord(String word, ArrayList<Word> words) {
        for (Word w : words) {
            if (w.getWord().equalsIgnoreCase(word)) {
                return w;
            }
        }
        return null;
    }

    public ArrayList<Word> findWords(ArrayList<String> cleaned) {
        /* Loop through each word in cleaned
        get current word and next word (up to len-1)
        if word does not exist create word obj and add it
        check if follower exists
                add follower
        increment frequency
         */
        ArrayList<Word> words = new ArrayList<>();
        if (cleaned.isEmpty() | cleaned == null) {
            return null;
        }
        for (int i = 0; i < cleaned.size(); i++) {
            String currentWord = cleaned.get(i);
            String followerWord;
            if (i == cleaned.size() - 1) {
                followerWord = null;
            } else {
                followerWord = cleaned.get(i + 1);
            }
            if (getWord(currentWord, words) == null) {
                Word word = new Word(currentWord);
                word.incrementFrequency();
                word.incrementFrequency();
                if (followerWord != null) {
                    word.addFollower(followerWord);
                }
                words.add(word);
            }
            if (getWord(currentWord, words) != null) {
                Word word = getWord(currentWord, words);
                int index = words.indexOf(word);
                word.incrementFrequency();
                if (followerWord != null) {
                    if (!word.followerExists(followerWord)) {
                        word.addFollower(followerWord);
                    }
                }
                words.set(index, word);
            }
        }
        return words;
    }
}
