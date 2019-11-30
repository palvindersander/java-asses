package com.ass4;

public class Main {

    public static void main(String[] args) {
        Converter c = new Converter(Converter.ConvertMode.BIN2DEC);
        c.fromFile("inputs.txt");
    }
}
