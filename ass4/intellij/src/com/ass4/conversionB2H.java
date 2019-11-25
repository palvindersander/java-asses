package com.ass4;
//package com.bham.pij.assignments.converters;

import java.util.ArrayList;

public class conversionB2H extends conversionSuper implements conversionInterface {
    public conversionB2H(String input) {
        super(input);
    }

    public void calcResult() {
        checkFormat();
        ArrayList<String> hexChars = getChars();
        String firstNibble = input.substring(0, 4);
        String secondNibble = input.substring(4, 8);
        result = hexChars.get(getNibbleDenary(firstNibble)) + hexChars.get(getNibbleDenary(secondNibble));
    }

    private int getNibbleDenary(String inp) {
        int value = 0;
        for (int i = inp.length() - 1; i >= 0; i--) {
            int val = Integer.parseInt(String.valueOf(inp.charAt(i)));
            value += val * Math.pow(2, inp.length() - 1 - i);
        }
        return value;
    }

    public void checkFormat() {
        if (input.length() != 8) {
            throw new InvalidFormatException();
        }
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (!(c == '1' || c == '0')) {
                throw new InvalidFormatException();
            }
        }
    }

    private ArrayList<String> getChars() {
        ArrayList<String> hexChars = new ArrayList<>();
        hexChars.add("0");
        hexChars.add("1");
        hexChars.add("2");
        hexChars.add("3");
        hexChars.add("4");
        hexChars.add("5");
        hexChars.add("6");
        hexChars.add("7");
        hexChars.add("8");
        hexChars.add("9");
        hexChars.add("A");
        hexChars.add("B");
        hexChars.add("C");
        hexChars.add("D");
        hexChars.add("E");
        hexChars.add("F");
        return hexChars;
    }
}
