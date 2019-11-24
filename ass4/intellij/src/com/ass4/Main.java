package com.ass4;

public class Main {

    public static void main(String[] args) {
	    Converter c = new Converter(Converter.ConvertMode.DEC2BIN);
        System.out.println(c.convert("h"));
    }
}
