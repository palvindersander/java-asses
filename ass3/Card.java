package com.bham.pij.assignments.pontoon;

import java.util.ArrayList;
import java.util.Random;

public class Card {
	
	//attributes
	private static String[] cardVals = { "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN", "JACK", "QUEEN", "KING", "ACE" };
	private static String[] cardSuits = { "HEARTS", "SPADES", "CLUBS", "DIAMONDS" };

	private String suit;
	private String value;
	private Boolean dealt;

	//constructor
	public Card(String suit, String value) {
		this.suit = suit;
		this.value = value;
		this.dealt = false;
	}

	//methods
	public ArrayList<Integer> getNumericalValue() {
		ArrayList<String> cardStrings = new ArrayList<String>();
		ArrayList<Integer> numValue = new ArrayList<Integer>();
		for (String str : cardVals) {
			cardStrings.add(str);
		}
		String value = getValue();
		if (value == "ACE") {
			numValue.add(11);
			numValue.add(1);
		} else if (value == "JACK" || value == "QUEEN" || value == "KING") {
			numValue.add(10);
		} else {
			int indexofArray = cardStrings.indexOf(value);
			numValue.add(indexofArray + 2);
		}
		return numValue;
	}
	//getters
	public static String getRandomSuit() {
		Random random = new Random();
		int randIndex = random.nextInt(cardSuits.length);
		return cardSuits[randIndex];
	}
	public static String getRandomValue() {
		Random random = new Random();
		int randIndex = random.nextInt(cardVals.length);
		return cardVals[randIndex];
	}
	public String getSuit() {
		return suit;
	}
	public String getValue() {
		return value;
	}
	public Boolean getDealt() {
		return dealt;
	}
	//setter
	public void setDealt(Boolean dealt) {
		this.dealt = dealt;
	}
	public String toString() {
		return ("Value : " + getValue() + ", Suit : " + getSuit());
	}
}
