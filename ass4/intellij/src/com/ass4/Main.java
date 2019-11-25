package com.ass4;

public class Main {

    public static void main(String[] args) {
        Converter c = new Converter(ConvertMode.DEC2BIN);
        c.fromFile("inputs.txt");
    }
}
