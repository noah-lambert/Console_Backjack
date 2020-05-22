package com.javaCode.blackjack;

import java.util.Scanner;

public class Blackjack {
	
	public static void main(String[] args) {

		//Welcome Message
		System.out.println("Welcome to the Casino!!!");
		
		//Create the playing deck
		Deck playingDeck = new Deck();
		playingDeck.createDeck();
		playingDeck.shuffle();
		
		//Create player and dealer decks 
		Deck playerHand = new Deck();
		Deck dealerHand = new Deck();
		
		Scanner userInput = new Scanner(System.in);
		double playerMoney = 100.00;
		boolean continueGame = true;
		
		
		//Game Loop 
		while(continueGame) {
			boolean endRound = false;
			boolean continueHand = true;
			double playerBet = 0;
			
			//take player bet
			while(playerBet == 0) {
				System.out.println("You have $" + playerMoney + ", how much would you like to wager?");
				playerBet = userInput.nextDouble();
				userInput.nextLine();
				
				if(playerBet > playerMoney ) {
					System.out.println("You cannot bet more then what you have.");
					System.out.println("Please try again\n");
					playerBet = 0;
				}
			}
			
			//Start Dealing
			//Two Cards for Each
			playerHand.draw(playingDeck);
			playerHand.draw(playingDeck);
			dealerHand.draw(playingDeck);
			dealerHand.draw(playingDeck);
			
			//
			while(continueHand) {
				//Display player cards and current value
				System.out.println("Your Hand: " + playerHand.toString());
				System.out.println("Value of Your Hand: " + playerHand.handValue() + "\n");
				
				//Display the dealer cards 
				System.out.println("Dealer Hand: " + dealerHand.getCard(0).toString() + " and [hidden]\n");
				
				//Determine if the player wants to hit or stand
				System.out.println("(1)Hit or (2)Stand");
				int resp = userInput.nextInt();
				userInput.nextLine();
				if(resp != 1 && resp != 2) {
					System.out.println("Input Error. Try Again");
					break;
				}
				if(resp == 1) {
					playerHand.draw(playingDeck);
					System.out.println("You draw a: " + playerHand.getCard(playerHand.getSize() - 1).toString());
					if(playerHand.handValue() > 21) {
						System.out.println("Bust.\n Dealer Wins.\n Your Hand Value: " + playerHand.handValue());
						playerMoney -= playerBet;
						endRound = true;
						continueHand = false;
						break;
					}
				}
				if(resp == 2) {
					break;
				}
				
				//Dealer's Turn 
				while(!endRound) {
					
					//Reveal Dealer Cards and Value of Hand 
					System.out.println("Dealer Cards: " + dealerHand.toString());
					System.out.println("Value of Dealer Hand: " + dealerHand.handValue());
					
					//Check if the dealer busts
					if(dealerHand.handValue() > 21) {
						System.out.println("Dealer Busts. You Win!");
						playerMoney += playerBet;
						endRound = true;
						break;
					}
					
					//Check if the dealer wins
					if(dealerHand.handValue() > playerHand.handValue() && !endRound) {
						System.out.println("Dealer Wins: " + dealerHand.handValue() + " to " + playerHand.handValue());
						playerMoney -= playerBet;
						endRound = true;
						break;
					}
					
					//Check for a push
					if(dealerHand.handValue() == playerHand.handValue()) {
						System.out.println("Push.");
						endRound = true;
						break;
					}
					
					//Draw another card
					dealerHand.draw(playingDeck);
					System.out.println("Dealer Draws: " + dealerHand.getCard(dealerHand.getSize() - 1));
					
				}//endRound
				
				//put all cards back in the playing deck
				playerHand.moveAllToDeck(playingDeck);
				dealerHand.moveAllToDeck(playingDeck);
				System.out.println("End of Round.\n");
				
				//If player is out of money, Game Over
				if(playerMoney <= 0) {
					System.out.println("No money remaining");
					continueHand = false;
					continueGame = false;
					break;
				}
				
			}//continueHand
			
		//Determine if the player wants to keep playing
		System.out.println("Keep Playing? (1)Yes (2)No");
		int decision = userInput.nextInt();
		userInput.nextLine();
		if(decision != 1 && decision != 2) {
			System.out.println("Input Error. Playing Again\n");
			continue;
		}
		if(decision == 1) {
			System.out.println("--------------------------\n");
			continue;
		}
		if(decision == 2) {
			continueGame = false;
			break;
		}

		}//continueGame
		System.out.println("Game Over.");
		userInput.close();
		
	}

}
