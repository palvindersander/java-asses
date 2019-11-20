package com.bham.pij.assignments.converters.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.bham.pij.assignments.converters.Converter;
import com.bham.pij.assignments.converters.InvalidFormatException;
import com.bham.pij.assignments.converters.Converter.ConvertMode;

public class ConverterTest {
	
	@Test
    /*@TestParams 
    (
    	purpose = "This test tests your Converter class loads an input file and converts the values correctly.",
        fail = "Your Converter class does not load an input file and convert the values correctly.",
        marks = 20.0f
    )*/
	void test_ConverterFromFile() {
		Converter c = new Converter(ConvertMode.BIN2HEX);
		c.fromFile("input.txt");
		ArrayList<String> input = c.getInputValues();
		ArrayList<String> output = c.getOutputValues();
		assertTrue(input != null);
		assertTrue(input.size() == 3);
		assertTrue(input.get(0).equals("11111111"));
		assertTrue(input.get(1).equals("00001111"));
		assertTrue(input.get(2).equals("01010101"));
		assertTrue(output.get(0).equals("FF"));
		assertTrue(output.get(1).equals("0F"));
		assertTrue(output.get(2).equals("55"));
	}
	
	@Test
    /*@TestParams 
    (
    	purpose = "This test tests your convert() method for BIN2HEX.",
        fail = "Your convert() method for BIN2HEX does not convert at least one test input correctly.",
        marks = 10.0f
    )*/
	public void test_Bin2Hex() {
		
		Converter converter = new Converter(ConvertMode.BIN2HEX);
		
		try {
			assertTrue(converter.convert("11111111").equals("FF"));
			assertTrue(converter.convert("00000000").equals("00"));
			assertTrue(converter.convert("00001111").equals("0F"));
			assertTrue(converter.convert("11110000").equals("F0"));	
			assertTrue(converter.convert("00001110").equals("0E"));
			assertTrue(converter.convert("01110111").equals("77"));
			assertTrue(converter.convert("10101010").equals("AA"));
			assertTrue(converter.convert("10111011").equals("BB"));
			assertTrue(converter.convert("10101100").equals("AC"));
		}
		catch (InvalidFormatException e) {
			e.printStackTrace();	
		}
	}

	@Test
    /*@TestParams 
    (
    	purpose = "This test tests your convert() method for BIN2HEX throws an exception for an invalid value.",
        fail = "Your convert() method for BIN2HEX does not throw the correct exception for an invalid input.",
        marks = 10.0f
    )*/
	public void test_Bin2Hex_exception() {
		
		Converter converter = new Converter(ConvertMode.BIN2HEX);
		
		try {
			assertTrue(converter.convert("1111111").equals("FF"));
				}
		catch (InvalidFormatException e) {
			e.printStackTrace();	
		}
		
		try {
			assertTrue(converter.convert("11111121").equals("FF"));
				}
		catch (InvalidFormatException e) {
			e.printStackTrace();	
		}

		try {
			assertTrue(converter.convert("1111110A").equals("FF"));
				}
		catch (InvalidFormatException e) {
			e.printStackTrace();	
		}
	}
	
	@Test
    /*@TestParams 
    (
    	purpose = "This test tests your convert() method for HEX2BIN.",
        fail = "Your convert() method for HEX2BIN does not convert at least one test input correctly.",
        marks = 10.0f
    )
    */
	public void test_Hex2Bin() {
		
		Converter converter = new Converter(ConvertMode.HEX2BIN);
		
		try {
			assertTrue(converter.convert("88").equals("10001000"));
			assertTrue(converter.convert("FF").equals("11111111"));
			assertTrue(converter.convert("00").equals("00000000"));
			assertTrue(converter.convert("B7").equals("10110111"));
		}
		catch (InvalidFormatException e) {
			e.printStackTrace();	
		}
	}
	

	@Test
    /*@TestParams 
    (
    	purpose = "This test tests your convert() method for HEX2BIN throws an exception for invalid input.",
        fail = "Your convert() method for HEX2BIN does not throw an exception for an invalid input.",
        marks = 10.0f
    )*/
	public void test_Hex2Bin_exception() {
		
		Converter converter = new Converter(ConvertMode.HEX2BIN);
		
		try {
			assertTrue(converter.convert("8").equals("10001000"));
			assertTrue(converter.convert("FFF").equals("11111111"));
			assertTrue(converter.convert("8G").equals("00000000"));
			assertTrue(converter.convert("H1").equals("10110111"));
			assertTrue(converter.convert("ZZ").equals("10110111"));
		}
		catch (InvalidFormatException e) {
			e.printStackTrace();
		}
	}

	@Test
    /*@TestParams 
    (
    	purpose = "This test tests your convert() method for DEC2BIN.",
        fail = "Your convert() method for DEC2BIN does not convert at least one test input correctly.",
        marks = 10.0f
    )*/
	public void test_Dec2Bin() {
		
		Converter converter = new Converter(ConvertMode.DEC2BIN);
		
		try {
			assertTrue(converter.convert("255").equals("11111111"));
			assertTrue(converter.convert("00").equals("00000000"));
			assertTrue(converter.convert("0").equals("00000000"));
			assertTrue(converter.convert("127").equals("01111111"));
			assertTrue(converter.convert("10").equals("00001010"));
			assertTrue(converter.convert("9").equals("00001001"));	
		}
		catch (InvalidFormatException e) {
			e.printStackTrace();	
		}
	}

	@Test
    /*@TestParams 
    (
    	purpose = "This test tests your convert() method for DEC2BIN.",
        fail = "Your convert() method for DEC2BIN does not convert at least one test input correctly.",
        marks = 10.0f
    )*/
	public void test_Dec2Bin_exception() {
		
		Converter converter = new Converter(ConvertMode.DEC2BIN);
		
		try {
			assertTrue(converter.convert("2555").equals("11111111"));
			assertTrue(converter.convert("A2").equals("00000000"));
			assertTrue(converter.convert("2B").equals("01111111"));
			assertTrue(converter.convert("10111011").equals("00001010"));
			assertTrue(converter.convert("1111").equals("00001001"));			
		}
		catch (InvalidFormatException e) {
			e.printStackTrace();	
		}
	}
	
	@Test
    /*@TestParams 
    (
    	purpose = "This test tests your convert() method for BIN2DEC.",
        fail = "Your convert() method for BIN2DEC does not convert at least one test input correctly.",
        marks = 10.0f
    )*/
	public void test_Bin2Dec() {
		
		Converter converter = new Converter(ConvertMode.BIN2DEC);
				
		try {
			assertTrue(converter.convert("11111111").equals("255"));
			assertTrue(converter.convert("11111110").equals("254"));
			assertTrue(converter.convert("11111111").equals("255"));
			assertTrue(converter.convert("00000000").equals("0"));
			assertTrue(converter.convert("10000001").equals("129"));
		}
		catch (InvalidFormatException e) {
			e.printStackTrace();	
		}
	}

	@Test
    /*@TestParams 
    (
    	purpose = "This test tests your convert() method for BIN2DEC.",
        fail = "Your convert() method for BIN2DEC does not convert at least one test input correctly.",
        marks = 10.0f
    )*/
	public void test_Bin2Dec_exception() {
		
		Converter converter = new Converter(ConvertMode.BIN2DEC);
		
		try {
			assertTrue(converter.convert("1111111").equals("255"));
			assertTrue(converter.convert("111111101").equals("254"));
			assertTrue(converter.convert("255").equals("255"));
			assertTrue(converter.convert("2A").equals("0"));
			assertTrue(converter.convert("0").equals("129"));
		}
		catch (InvalidFormatException e) {
			e.printStackTrace();	
		}
	}
}
