package com.ass4;
//package com.bham.pij.assignments.converters;

import java.util.ArrayList;

public class conversionH2B extends conversionSuper implements conversionInterface {
    public conversionH2B(String input) {
        super(input);
    }

    public void calcResult() {
        checkFormat();
        ArrayList<String> hexChars = getChars();
        int valueD = 0;
        for (int i = input.length() - 1; i >= 0; i--) {
            int val = hexChars.indexOf(String.valueOf(input.charAt(i)));
            valueD += val * Math.pow(16, input.length() - 1 - i);
        }
        String binary = "11111111";
        int denary = valueD;
        for (int i = 0; i < binary.length(); i++) {
            int binarySignificantVal = Integer.parseInt(String.valueOf(binary.charAt(i))) * (int) Math.pow(2, binary.length() - i - 1);
            if (denary == 0) {
                binary = binary.substring(0, i) + '0' + binary.substring(i + 1);
                continue;
            }
            if (denary - binarySignificantVal >= 0) {
                denary -= binarySignificantVal;
                binary = binary.substring(0, i) + '1' + binary.substring(i + 1);
            } else if (denary - binarySignificantVal < 0) {
                binary = binary.substring(0, i) + '0' + binary.substring(i + 1);
            }
        }
        result = binary;
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

    public void checkFormat() {
        if (input.length() != 2) {
            throw new InvalidFormatException();
        }
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (!(c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9' || c == 'A' || c == 'B' || c == 'C' || c == 'D' || c == 'E' || c == 'F')) {
                throw new InvalidFormatException();
            }
        }
    }
}
