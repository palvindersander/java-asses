package com.bham.pij.assignments.pontoon;

import java.util.ArrayList;

public class Hand {

	//attributes
	private ArrayList<Card> hand;

	//constructor
	public Hand() {
		hand = new ArrayList<Card>();
	}

	//methods
	public void addCard(Card c) {
		if (hand.contains(c) == false) {
			hand.add(c);
		}
	}
	//getters
	public int getHandSize() {
		return hand.size();
	}
	public Card getCard(int i) {
		return hand.get(i);
	}
	public String showHand() {
		String returnString = "";
		if (getHandSize() > 0) {
			for (Card c : hand) {
				returnString += c.toString() + "\n";
			}
		}
		return returnString;
	}
	public ArrayList<Integer> getNumericalValue() {
		ArrayList<Integer> totalValues = new ArrayList<Integer>();
		totalValues.add(0);
		for (Card card : hand) {
			ArrayList<Integer> cardVals = card.getNumericalValue();
			if (cardVals.size() == 1) {
				for (int i = 0; i < totalValues.size(); i++) {
					totalValues.set(i, totalValues.get(i) + cardVals.get(0));
				}
			} else if (cardVals.size() == 2) {
				int firstValue = cardVals.get(0);
				int secondValue = cardVals.get(1);
				int initialSize = totalValues.size();
				for (int i = 0; i < initialSize; i++) {
					int initialValue = totalValues.get(i);
					totalValues.set(i, initialValue + firstValue);
					totalValues.add(initialValue + secondValue);
					
				}
			}
		}
		ArrayList<Integer> valuesUnique = new ArrayList<Integer>();
		for (int vals : totalValues) {
			if (!(valuesUnique.contains(vals))) {
				valuesUnique.add(vals);
			}
		}
		return valuesUnique;
	}
}
