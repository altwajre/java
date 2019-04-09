package _02_ConceptsAndAlgorithms.ObjectOrientedDesign.DeckOfCards;

import org.junit.Test;

import java.util.ArrayList;

/*
Q: Design the data structures for a generic deck of cards.
   Explain how you would subclass the data structures to implement blackjack.

 */
public class DeckOfCardsTest {
    @Test
    public void Test() {
        int numHands = 5;
        BlackJackGameAutomator automator = new BlackJackGameAutomator(numHands);
        automator.initializeDeck();
        boolean success = automator.dealInitial();
        if (!success) {
            System.out.println("Error. Out of cards.");
        } else {
            System.out.println("-- Initial --");
            automator.printHandsAndScore();
            ArrayList<Integer> blackjacks = automator.getBlackJacks();
            if (blackjacks.size() > 0) {
                System.out.print("Blackjack at ");
                for (int i : blackjacks) {
                    System.out.print(i + ", ");
                }
                System.out.println("");
            } else {
                success = automator.playAllHands();
                if (!success) {
                    System.out.println("Error. Out of cards.");
                } else {
                    System.out.println("\n-- Completed Game --");
                    automator.printHandsAndScore();
                    ArrayList<Integer> winners = automator.getWinners();
                    if (winners.size() > 0) {
                        System.out.print("Winners: ");
                        for (int i : winners) {
                            System.out.print(i + ", ");
                        }
                        System.out.println("");
                    } else {
                        System.out.println("Draw. All players have busted.");
                    }
                }
            }
        }
    }
}
/*
output:
-- Initial --
Hand 0 (13):
Hand 1 (18):
Hand 2 (19):
Hand 3 (21):
Hand 4 (13):
Blackjack at 3,
 */
