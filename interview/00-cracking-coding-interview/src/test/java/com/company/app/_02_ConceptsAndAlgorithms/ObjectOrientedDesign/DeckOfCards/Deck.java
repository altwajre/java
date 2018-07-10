package com.company.app._02_ConceptsAndAlgorithms.ObjectOrientedDesign.DeckOfCards;

import java.util.ArrayList;
import java.util.Random;

public class Deck<T extends Card> {
    private ArrayList<T> cards; // all cards, dealt or not
    private int dealtIndex = 0; // marks first undealt card

    private int randInt(int min, int max) {
        Random random = new Random();
        // nextInt is normally exclusive of the top value, so add 1 to make it inclusive
        int randomeNum = random.nextInt((max - min) + 1) + min;
        return randomeNum;
    }

    public void setDeckOfCards(ArrayList<T> deckOfCards) {
        cards = deckOfCards;
    }

    public void shuffle() {
        for (int i = 0; i < cards.size(); i++) {
            int j = randInt(0, cards.size() - 1);
            T card1 = cards.get(i);
            T card2 = cards.get(j);
            cards.set(i, card2);
            cards.set(j, card1);
        }
    }

    public int remainingCards() {
        return cards.size() - dealtIndex;
    }

    public T[] dealHand(int number) {
        if (remainingCards() < number) return null;
        T[] hand = (T[]) new Card[number];
        int count = 0;
        while (count < number) {
            T card = dealCard();
            if (card != null) {
                hand[count] = card;
                count++;
            }
        }
        return hand;
    }

    public T dealCard() {
        if (remainingCards() == 0) return null;
        T card = cards.get(dealtIndex);
        card.markUnavailable();
        dealtIndex++;
        return card;
    }

    public void print() {
        for (Card card : cards) {
            card.print();
        }
    }
}
