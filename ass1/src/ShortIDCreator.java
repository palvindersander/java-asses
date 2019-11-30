package com.bham.pij.assignments.shortidcreator;

import java.util.Scanner;

public class ShortIDCreator {

    public static void main(String[] args) {
        ShortIDCreator sid = new ShortIDCreator();
        System.out.println(sid.createID("hello world"));
    }

    public String createID(String input) {
        if (!(checkValid(input))) {
            return null;
        }
        input = input.toLowerCase();
        String[] words = input.split(" ", -1);
        String id = "";
        for (String word : words) {
	    if (word == null) {
            id += word.charAt(0);
        }
        if (words.length == 2) {
            id = id.charAt(0) + "x" + id.charAt(1);
        }
        return id;
    }

    private boolean checkValid(String input) {
        String[] words = input.split(" ", -1);
        if (input == null || input.length() == 0 || words.length < 2 || words.length > 3) {
            return false;
        }
        return true;
    }
}
