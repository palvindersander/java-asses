package com.ass4;
//package com.bham.pij.assignments.converters;

public class conversionB2D extends conversionSuper implements conversionInterface {
    public conversionB2D(String input) {
        super(input);
    }


    public void calcResult() {
        checkFormat();
        int value = 0;
        for (int i = input.length() - 1; i >= 0; i--) {
            int val = Integer.parseInt(String.valueOf(input.charAt(i)));
            value += val * Math.pow(2, input.length() - 1 - i);
        }
        result = String.valueOf(value);
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
}
