package com.company.app;

import java.util.*;

/*
To fix the exception, addd a variable in the scope of the outer loop to hold the outer element
 */
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
        for(Iterator<Suit> i = suits.iterator(); i.hasNext();){
            Suit suit = i.next();
            for(Iterator<Rank> j = ranks.iterator(); j.hasNext();){
                deck.add(new Card(suit, j.next())); // suits iterator.next() is called too many times
            }
        }
    }
}
