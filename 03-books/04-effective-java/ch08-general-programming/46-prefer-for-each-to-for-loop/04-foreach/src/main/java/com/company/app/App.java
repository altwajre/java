package com.company.app;

import java.util.*;

enum Suit{
    CLUB, DIAMOND, HEART, SPADE
}
enum Rank{
    ACE, DEUCE, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
}
class Card{
    final Suit suit;
    final Rank rank;
    Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }
    @Override
    public String toString() {
        return "(" + suit + " " + rank + ")";
    }
}
public class App 
{
    public static void main( String[] args )
    {
        Collection<Suit> suits = Arrays.asList(Suit.values());
        Collection<Rank> ranks = Arrays.asList(Rank.values());

        List<Card> deck = new ArrayList<Card>();
        // Preferred idiom for nested iteration on collections and arrays
        for(Suit suit : suits){
            for(Rank rank : ranks){
                deck.add(new Card(suit, rank));
            }
        }

        for(Card card : deck){
            System.out.print(card + " ");
        }
    }
}
/*
output:
(CLUB ACE) (CLUB DEUCE) (CLUB THREE) (CLUB FOUR) (CLUB FIVE) (CLUB SIX) (CLUB SEVEN) (CLUB EIGHT) (CLUB NINE) (CLUB TEN) (CLUB JACK) (CLUB QUEEN) (CLUB KING) (DIAMOND ACE) (DIAMOND DEUCE) (DIAMOND THREE) (DIAMOND FOUR) (DIAMOND FIVE) (DIAMOND SIX) (DIAMOND SEVEN) (DIAMOND EIGHT) (DIAMOND NINE) (DIAMOND TEN) (DIAMOND JACK) (DIAMOND QUEEN) (DIAMOND KING) (HEART ACE) (HEART DEUCE) (HEART THREE) (HEART FOUR) (HEART FIVE) (HEART SIX) (HEART SEVEN) (HEART EIGHT) (HEART NINE) (HEART TEN) (HEART JACK) (HEART QUEEN) (HEART KING) (SPADE ACE) (SPADE DEUCE) (SPADE THREE) (SPADE FOUR) (SPADE FIVE) (SPADE SIX) (SPADE SEVEN) (SPADE EIGHT) (SPADE NINE) (SPADE TEN) (SPADE JACK) (SPADE QUEEN) (SPADE KING)
 */
