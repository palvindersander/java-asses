package com.bham.pij.assignments.legacycleaner;

public class LegacyCleaner {

    // name id result postcode

    public static void main(String[] args) {
        LegacyCleaner lc = new LegacyCleaner();
        String x = "John Winston Lennon, PAS, N164BY, jwl6734 ";
        String[] y = lc.clean(x);
    }

    public String[] clean(String input) {
	if (!checkInput(input)) {
		return null;
	}
        String[] clean = new String[4];
        String[] words = input.split(",");
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (!word.isEmpty() && !word.equals(null) && word.length() > 0) {
                if (word.charAt(0) == 32) {
                    word = word.substring(1);
                }
                if (word.charAt(word.length()-1) == 32) {
                    word = word.substring(0,word.length()-1);
                }
                if (checkName(word)) {
		    if (clean[0] == null) {
		    	clean[0] = word;
		    }
                }
                if (checkID(word)) {
                    clean[1] = word;
                }
                if (checkResult(word)) {
                    clean[2] = word;
                }
                if (checkPostcode(word)) {
                    clean[3] = word;
                }
            }
        }
        return clean;
    }

    private boolean checkInput(String input){
	if (input == null) {
		return false;	
	}
	if (input.isEmpty()) {
		return false;	
	}
	int count = 0;
	for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            int ascii = (int) c;
            if (ascii == 44) {
                count += 1;
            }
        }
	if (count == input.length() || count == 0) {
		return false;	
	}
	return true;
    }

    private boolean checkPostcode(String word) {
        if (word.length() != 6) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int ascii = (int) c;
            if (!checkLetter(ascii) && !checkNum(ascii)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkResult(String word) {
        if (word.equals("FAIL") || word .equals("PASS") || word.equals("MERIT")) {
            return true;
        }
        return false;
    }

    private boolean checkName(String word) {
        if (word.equals("FAIL") || word .equals("PASS") || word.equals("MERIT")) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int ascii = (int) c;
            if (!checkLetter(ascii) && ascii != 32) {
                return false;
            }
        }
        return true;
    }

    private boolean checkID(String word) {
        if (word.length() != 7) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int ascii = (int) c;
            if (!checkLetter(ascii) && !checkNum(ascii)) {
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
