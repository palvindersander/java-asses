//package com.bham.pij.assignments.converters;

package com.ass4;

import java.util.ArrayList;

public class Converter {

    public static enum ConvertMode {BIN2HEX, HEX2BIN, BIN2DEC, DEC2BIN}

    private ConvertMode mode;

    public Converter(ConvertMode mode) {
        this.mode = mode;
    }

    public String convert(String input) throws InvalidFormatException {
        String output = null;
        switch (this.mode) {
            case BIN2HEX:
                output = new conversionB2H(input).result;
                break;
            case HEX2BIN:
                output = new conversionH2B(input).result;
                break;
            case BIN2DEC:
                output = new conversionB2D(input).result;
                break;
            case DEC2BIN:
                output = new conversionD2B(input).result;
                break;
            default:
                break;
        }
        return output;
    }

    public void fromFile​(String filename) {

    }

    public ArrayList<String> getInputValues(){return null;}

    public ArrayList<String> getOutputValues(){return null;}

}
