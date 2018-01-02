package com.company.app.DataStructure.ArraysAndStrings;

import org.junit.Test;

import java.util.Random;

public class ShuffleCards {

    @Test
    public void Test() {
        int[] cards = {1, 2, 3, 4, 5};

        System.out.println("before shuffle");
        for (int i = 0; i < cards.length; i++) {
            System.out.print(cards[i] + " ");
        }

        shuffle(cards);

        System.out.println("\nafter shuffle");
        for (int i = 0; i < cards.length; i++) {
            System.out.print(cards[i] + " ");
        }
    }

    static void shuffle(int[] cards) {

        for (int i = 0; i < cards.length; i++) {
            Random rand = new Random();
            int randomNum = rand.nextInt(cards.length - 1);
            int temp = cards[i];
            cards[i] = cards[randomNum];
            cards[randomNum] = temp;
        }
    }
}
/*
output:
before shuffle
1 2 3 4 5
after shuffle
2 1 4 5 3
 */
