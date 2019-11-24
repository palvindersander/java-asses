//package com.bham.pij.assignments.converters;

//b2h
//h2b
//b2d
//d2b

interface conversionInterface {
    private String calcResult();
}

public class conversionSuper {
    private String result;
    private String input;
	private String input2;
    public void conversionSuper(String input){
        input2 = input;
		this.input = input;
    }
}

public class conversionB2H extends conversionSuper implements conversion{
    public void conversionB2H(String input){
        super(String input);
    }

    private String calcResult(){

    }
}

public class conversionH2B extends conversionSuper implements conversion{
    public void conversionH2B(String input){
        super(String input);
    }

    private String calcResult(){
        
    }
}

public class conversionB2D extends conversionSuper implements conversion{
    public void conversionB2D(String input){
        super(String input);
    }
    
    private String calcResult(){
        
    }
}

public class conversionD2B extends conversionSuper implements conversion{
    public void conversionD2B(String input){
        super(String input);
    }
    
    private String calcResult(){
        
    }
}
  

public class Converter{

    public static enum ConvertMode {BIN2HEX, HEX2BIN, BIN2DEC, DEC2BIN};
    private ConvertMode mode;


    public void Converter(ConvertMode mode){
        //constructor
        this.mode = mode 
    }

    public String convert(String input){
        String output;
        switch (this.mode) {
            case BIN2HEX:
                conversionB2H conversion = new conversionB2H(input);
                output =  conversion.calcResult();
            case HEX2BIN:
                conversionH2B conversion = new conversionH2B(input);
                output =  conversion.calcResult();
            case BIN2DEC:
                conversionB2D conversion = new conversionB2D(input);
                output =  conversion.calcResult();
            case DEC2BIN:
                conversionD2B conversion = new conversionD2B(input);
                output =  conversion.calcResult();
        
        }
        return output;
    }

}
