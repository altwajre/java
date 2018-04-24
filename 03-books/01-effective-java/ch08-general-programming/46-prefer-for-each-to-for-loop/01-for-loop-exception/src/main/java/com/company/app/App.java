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
        for(Iterator<Suit> i = suits.iterator(); i.hasNext();){
            for(Iterator<Rank> j = ranks.iterator(); j.hasNext();){
                deck.add(new Card(i.next(), j.next())); // suits iterator.next() is called too many times
            }
        }
    }
}
/*
outpout:
Exception in thread "main" java.util.NoSuchElementException
	at java.util.AbstractList$Itr.next(AbstractList.java:364)
	at com.company.app.App.main(App.java:33)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:497)
	at com.intellij.rt.execution.application.AppMain.main(AppMain.java:144)
 */