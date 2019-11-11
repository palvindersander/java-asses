package com.bham.pij.assignments.pontoon;

public class Deck {
	
	//attributes
	private static String[] cardVals = { "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN", "JACK", "QUEEN", "KING", "ACE" };
	private static String[] cardSuits = { "HEARTS", "SPADES", "CLUBS", "DIAMONDS"};

	private Card[] deck;

	//constructor
	public Deck() {
		this.deck = new Card[52];
		for (int i = 0; i < cardSuits.length; i++) {
			String suit = cardSuits[i];
			for (int j = 0; j < cardVals.length; j++) {
				String value = cardVals[j];
				int index = (i * 13) + j;
				deck[index] = new Card(suit, value);
			}
		}
	}

	//methods
	//getters
	public int getDeckSize() {
		return deck.length;
	}
	public void reset() {
		for (Card c : deck) {
			c.setDealt(false);
		}
	}
	public Card getCard(int i) {
		if (i > (deck.length - 1) || i < 0) {
			return null;
		}
		return deck[i];
	}
	public Card dealCard(int i) {
		if (i > (deck.length - 1) || i < 0) {
			return null;
		}
		Card card = deck[i];
		if (card.getDealt() == true) {
			return null;
		}
		card.setDealt(true);
		return card;
	}
}
