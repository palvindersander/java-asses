package com.bham.pij.assignments.fullidcreator;

public class FullIDCreator {

    private short[] base26Counter = new short[(26 * 26 * 26)];
    //a->0
    //z->25

    public static void main(String[] args) {
        FullIDCreator fidc = new FullIDCreator();
        for (int i=0;i<20000;i++){
            System.out.println(fidc.createFullID("dum rob job"));
            System.out.println(fidc.createFullID("ram rob"));
        }
    }

    public String createFullID(String input) {
        String smallID = createSmallID(input);
        if (smallID == null) {
            return null;
        }
        int pos = getPos(smallID);
        String id = smallID+formatNumber(base26Counter[pos]);
        if (base26Counter[pos] < 9999){
            base26Counter[pos] += 1;
        }
        return id;
    }

    private String formatNumber(int val) {
        String stringVal = String.valueOf(val);
        int numZeros = 5 - stringVal.length();
        String zeros = "";
        for (int i = 0; i<numZeros-1; i++){
            zeros += "0";
        }
        return zeros + stringVal;
    }

    private int getPos(String input) {
        int pso = 0;
        //offset 97
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            int ascii = (int) c;
            ascii = ascii - 97;
            pso += ascii * (Math.pow(26, 2 - i));
        }
        return pso;
    }

    public String createSmallID(String input) {
        if (!(checkValid(input))) {
            return null;
        }
        input = input.toLowerCase();
        String[] words = input.split(" ", -1);
        String id = "";
        for (String word : words) {
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
