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
			boolean continueDealerTurn = true;
			boolean continuePlayerTurn = true;
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
			
			//Player Turn
			while(continuePlayerTurn) {
				//Display player cards and current value
				System.out.println("Your Hand: " + playerHand.toString());
				System.out.println("Value of Your Hand: " + playerHand.handValue() + "\n");
				
				//Display the dealer cards 
				System.out.println("Dealer Hand: " + dealerHand.getCard(0).toString() + " and [hidden]\n");
				
				//Determine if the player wants to hit or stand
				System.out.print("(1)Hit or (2)Stand:  ");
				int resp = userInput.nextInt();
				
				if(resp != 1 && resp != 2) {
					System.out.println("Input Error. Try Again \n");
				}

				if(resp == 1) {
					playerHand.draw(playingDeck);
					System.out.println("\nYou draw a: " + playerHand.getCard(playerHand.getSize() - 1).toString() + "\n");
					if(playerHand.handValue() > 21) {
						System.out.println("Bust.\nDealer Wins.\nYour Hand Value: " + playerHand.handValue());
						playerMoney -= playerBet;
						continueDealerTurn = false;
						continuePlayerTurn = false;
					}
				}

				if(resp == 2) {
					System.out.println("\n---------------------------\n");
					continuePlayerTurn = false;
				}
			} //Player Turn
				
			//Dealer's Turn 
			while(continueDealerTurn) {
			
				//Reveal Dealer Cards and Value of Hand 
				System.out.println("Dealer Cards: " + dealerHand.toString());
				System.out.println("Value of Dealer Hand: " + dealerHand.handValue() + "\n");

				//Check if the Dealer has 21
				if(dealerHand.handValue() == 21) {
					System.out.println("Dealer Wins with 21. \n");
					playerMoney -= playerBet;
					continueDealerTurn = false;
					break;
				}
					
				//Check if the dealer busts
				if(dealerHand.handValue() > 21) {
					System.out.println("Dealer Busts. You Win!");
					playerMoney += playerBet;
					continueDealerTurn = false;
					break;
				}
					
				//Check if the dealer wins
				if(dealerHand.handValue() > playerHand.handValue()) {
					System.out.println("Dealer Wins: " + dealerHand.handValue() + " to " + playerHand.handValue());
					playerMoney -= playerBet;
					continueDealerTurn = false;
					break;
				}
					
				//Check for a push
				if(dealerHand.handValue() == playerHand.handValue()) {
					System.out.println("Push.");
					continueDealerTurn = false;
					break;
				}
				
				//Draw another card
				if(continueDealerTurn) {
					dealerHand.draw(playingDeck);
					System.out.println("Dealer Draws: " + dealerHand.getCard(dealerHand.getSize() - 1) + "\n");
				}

			} //continueDealerTurn
				
			//put all cards back in the playing deck
			playerHand.moveAllToDeck(playingDeck);
			dealerHand.moveAllToDeck(playingDeck);
			System.out.println("End of Round.\nYou have $" + playerMoney);
			System.out.println("\n----------------------------\n");
			
			//If player is out of money, Game Over
			if(playerMoney <= 0) {
				System.out.println("No money remaining");
				continuePlayerTurn = false;
				continueGame = false;
				break;
			}
			
			
			//Determine if the player wants to keep playing
			System.out.println("Keep Playing? (1)Yes (2)No");
			int decision = userInput.nextInt();
			userInput.nextLine();
			if(decision != 1 && decision != 2) {
				System.out.println("Input Error. Playing Again\n");
			}
			if(decision == 1) {
				System.out.println("\n--------------------------\n");
			}
			if(decision == 2) {
				continueGame = false;
				break;
			}

		} //continueGame

		System.out.println("Game Over.");
		userInput.close();
		
	} //main

} //Blackjack
