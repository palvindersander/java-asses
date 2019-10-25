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
        Word currentWord;
        if (words == null) {
            return null;
        }
        if (words.size() == 0) {
            return null;
        }
        if (words.size() == 1) {
            currentWord = words.get(0);
        } else {
            int r = random.nextInt(words.size());
            currentWord = words.get(r);
        }
        tweetWords[0] = currentWord.getWord();
        for (int i = 1; i < numWords; i++) {
            if (currentWord.getFollowers().size() == 0) {
                break;
            }
            currentWord = getWord(currentWord.getRandomFollower(), words);
            tweetWords[i] = currentWord.getWord();
        }
        String tweet = "";
        for (int i = 0; i < tweetWords.length; i++) {
            String word = tweetWords[i];
            if (tweetWords.length == 0) {
                return null;
            }
            if (i == tweetWords.length - 1) {
                tweet = tweet + word;
            } else {
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
        ArrayList<Word> tweetWords = new ArrayList<>();
        if (TweetGenerator.words != null && TweetGenerator.words.size() > 0) {
            tweetWords = TweetGenerator.words;
        }
        if (cleaned.isEmpty() || cleaned == null) {
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
            if (getWord(currentWord, tweetWords) == null) {
                Word word = new Word(currentWord);
                if (followerWord != null) {
                    word.addFollower(followerWord);
                }
                tweetWords.add(word);
            }
            if (getWord(currentWord, tweetWords) != null) {
                Word word = getWord(currentWord, tweetWords);
                int index = tweetWords.indexOf(word);
                assert word != null;
                word.incrementFrequency();
                if (followerWord != null) {
                    if (!word.followerExists(followerWord)) {
                        word.addFollower(followerWord);
                    }
                }
                tweetWords.set(index, word);
            }
        }
        return tweetWords;
    }
}
