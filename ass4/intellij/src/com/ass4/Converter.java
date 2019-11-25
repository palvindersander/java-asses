//package com.bham.pij.assignments.converters;

package com.ass4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

enum ConvertMode {BIN2HEX, HEX2BIN, BIN2DEC, DEC2BIN}

public class Converter {

    private ConvertMode mode;

    private ArrayList<String> inputs;

    private ArrayList<String> outputs;

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

    public void fromFile(String filename) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            ArrayList<String> inputs = new ArrayList<>();
            String line = "";
            while ((line = in.readLine()) != null) {
                inputs.add(line);
            }
            in.close();
            this.inputs = inputs;
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String> outputs = new ArrayList<>();
        for (String value : inputs) {
            outputs.add(this.convert(value));
        }
        this.outputs = outputs;
    }

    public ArrayList<String> getInputValues() {
        return inputs;
    }

    public ArrayList<String> getOutputValues() {
        return outputs;
    }

}
