package com.company.app;

import java.util.ArrayList;
import java.util.Random;

/*
Q: Design the data structures for a generic deck of cards.
   Explain how you would subclass the data structures to implement blackjack.

 */
public class App 
{
    public static void main( String[] args ){
        int numHands = 5;
        BlackJackGameAutomator automator = new BlackJackGameAutomator(numHands);
        automator.initializeDeck();
        boolean success = automator.dealInitial();
        if (!success) {
            System.out.println("Error. Out of cards.");
        }
        else {
            System.out.println("-- Initial --");
            automator.printHandsAndScore();
            ArrayList<Integer> blackjacks = automator.getBlackJacks();
            if (blackjacks.size() > 0) {
                System.out.print("Blackjack at ");
                for (int i : blackjacks) {
                    System.out.print(i + ", ");
                }
                System.out.println("");
            }
            else {
                success = automator.playAllHands();
                if (!success) {
                    System.out.println("Error. Out of cards.");
                }
                else {
                    System.out.println("\n-- Completed Game --");
                    automator.printHandsAndScore();
                    ArrayList<Integer> winners = automator.getWinners();
                    if (winners.size() > 0) {
                        System.out.print("Winners: ");
                        for (int i : winners) { System.out.print(i + ", "); }
                        System.out.println("");
                    }
                    else {
                        System.out.println("Draw. All players have busted.");
                    }
                }
            }
        }
    }
}
