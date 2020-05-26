package com.javaCode.blackjack;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

	//instance
	private ArrayList<Card> cards;
	
	//constructor
	public Deck() {
		this.cards = new ArrayList<Card>();
	}
	
	//generate cards 
	public void createDeck() {
		for( Suit suit : Suit.values() ) {
			for( Value value : Value.values() ) {
				this.cards.add(new Card(suit, value));
			}
		}
	}
	
	//Knuth shuffle
	public void shuffle() {
		
		Card temp_card;
		
		for( int i = 0; i < this.cards.size(); i++) {
			Random rand = new Random();
			
			//pick random card and copy it to temp_card
			int j = rand.nextInt((this.cards.size()-1 - 0) + 1);
			temp_card = new Card(cards.get(j).getSuit(), cards.get(j).getValue());
			
			//pick another random card to swap with the first
			int k = rand.nextInt((this.cards.size()-1 - 0) + 1);
			this.cards.set(j, this.cards.get(k));
			
			//set second card to temp_card
			this.cards.set(k, temp_card);
		}
	}
	
	public void removeCard(int i) {
		this.cards.remove(i);
	}
	
	public Card getCard(int i) {
		return this.cards.get(i);
	}
	
	public void addCard(Card card) {
		this.cards.add(card);
	}
	
	public void draw(Deck deck) {
		this.cards.add(deck.getCard(0));
		deck.removeCard(0);
	}
	
	public int getSize() {
		return this.cards.size();
	}
	
	public void moveAllToDeck(Deck moveTo){
		int thisDeckSize = this.cards.size();
		//put cards in moveTo deck
		for(int i = 0; i < thisDeckSize; i++){
			moveTo.addCard(this.getCard(i));
		}
		//empty out the deck
		for(int i = 0; i < thisDeckSize; i++){
			this.removeCard(0);
		}
	}
	
	public int handValue() {
		int total_value = 0;
		int ace = 0;
		int ace_iter = 0;
	
		for( Card card : this.cards ) {
			switch(card.getValue()) {
			case TWO: 
				total_value += 2; 
				break;
			case THREE: 
				total_value += 3; 
		    	break;
			case FOUR: 
				total_value += 4; 
		       	break;
			case FIVE: 
				total_value += 5; 
				break;
			case SIX: 
				total_value += 6; 
		       	break;
			case SEVEN: 
				total_value += 7; 
		       	break;
			case EIGHT: 
				total_value += 8; 
		       	break;
			case NINE: 
				total_value += 9; 
		       	break;
			case TEN: 
				total_value += 10; 
		       	break;
			case JACK: 
				total_value += 10; 
		       	break;
			case QUEEN: 
				total_value += 10; 
		       	break;
			case KING: 
				total_value += 10; 
		       	break;
			case ACE: 
				ace += 1;
				break;
			}//switch
			
			if(ace == 1) {
				//Check if an ace worth 11 will bust the hand, if so an ace is worth 1
				if (total_value > 10){
					total_value += 1;
				}
				else{
					total_value += 11;
				}
				ace = ace - 1;
			}
		}//for
		
		return total_value;
	}
	
	
	public String toString() {
		String cardList = "";
		for( Card card : this.cards) {
			cardList += "\n" + card.toString();
			
		}
		return cardList;
	}
	
}
