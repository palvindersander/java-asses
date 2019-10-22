package com.bham.pij.assignments.passwordchecker;

public class PasswordChecker {
    public static void main(String[] args) {
        PasswordChecker pc = new PasswordChecker();
        System.out.println(pc.checkPassword("aD"));
        System.out.println(pc.checkPassword("abcdefDhiklmn"));
        System.out.println(pc.checkPassword("abcDfifh&"));
        System.out.println(pc.checkPassword("1abcDefhi"));
        System.out.println(pc.checkPassword("abcdefghik"));
    }

    public String checkPassword(String input){
        boolean lower = false;
        boolean upper = false;
        if (input.length() < 8){
            return "TOO SHORT";
        }
        if (input.length() > 12){
            return "TOO LONG";
        }
        for (int i = 0; i < input.length(); i++){
            char c = input.charAt(i);
            int ascii = (int) c;
            if (!(ascii == 95 || (ascii > 47 && ascii < 58) || (ascii > 64 && ascii < 91) || (ascii > 96 && ascii < 123))){
                return "WRONG CHARACTERS";
            }
            if ((i == 0) && (ascii > 47 && ascii < 58)){
                return "LEADING DIGIT";
            }
            if (ascii > 64 && ascii < 91){
                upper = true;
            }
            if (ascii > 96 && ascii < 123){
                lower = true;
            }
        }
        if (!(lower && upper)){
            return "NOT MIXED CASE";
        }
        return "OK";
    }
}
