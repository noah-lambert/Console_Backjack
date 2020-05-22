package com.javaCode.blackjack;

public class Card {
	
	//instances
	private Suit suit;
	private Value value;
	
	//constructor
	public Card(Suit suit, Value value) {
		this.suit = suit;
		this.value = value;
	}
	
	public String toString() {
		return "[" + this.value.toString() + "-" + this.suit.toString() + "]";
	}
	
	public Suit getSuit() {
		return this.suit;
	}
	
	public Value getValue() {
		return this.value;
	}
}
