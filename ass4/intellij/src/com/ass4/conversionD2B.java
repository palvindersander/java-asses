package com.ass4;
//package com.bham.pij.assignments.converters;

public class conversionD2B extends conversionSuper implements conversionInterface {
    public conversionD2B(String input) {
        super(input);
    }

    public void calcResult() {
        checkFormat();
        String binary = "11111111";
        int denary = Integer.parseInt(input);
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

    public void checkFormat() {
        int i = Integer.parseInt(input);
        if (!(i > -1 && i < 256)) {
            throw new InvalidFormatException();
        }
    }
}
