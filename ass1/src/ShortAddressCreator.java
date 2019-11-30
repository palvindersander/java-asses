package com.bham.pij.assignments.shortaddresscreator;

public class ShortAddressCreator {

    public static void main(String[] args) {
        ShortAddressCreator sac = new ShortAddressCreator();
        System.out.println(sac.createShortAddress(null));
    }

    public String createShortAddress(String input) {
        if (input == null || input.length() == 0) {
            return null;
        }
        String[] parts = input.split(", ");
        if (!checkPostcode(parts[parts.length - 1]) || parts.length < 2) {
            return null;
        }
        return parts[0] + " " + parts[parts.length - 1];
    }

    private boolean checkPostcode(String input) {
        if (input.length() != 6) {
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            int ascii = (int) c;
            if ((i == 0 || (i > 3)) && (!checkLetter(ascii))) {
                return false;
            }
            if ((i > 0 && i < 4) && (!checkNum(ascii))) {
                return false;
            }
        }
        return true;
    }

    private boolean checkLetter(int c) {
        return (c > 64 && c < 91) || (c > 96 && c < 123);
    }

    private boolean checkNum(int c) {
        return (c > 47 && c < 58);
    }
}
