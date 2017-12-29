package com.company.app;

import java.util.Random;

public class App {
    static void shuffle(int[] cards) {
        System.out.println("before shuffle");
        for (int i = 0; i < cards.length; i++) {
            System.out.print(cards[i] + " ");
        }

        for (int i = 0; i < cards.length; i++) {
            Random rand = new Random();
            int randomNum = rand.nextInt(cards.length - 1);
            int temp = cards[i];
            cards[i] = cards[randomNum];
            cards[randomNum] = temp;
        }

        System.out.println("\nafter shuffle");
        for (int i = 0; i < cards.length; i++) {
            System.out.print(cards[i] + " ");
        }
    }

    public static void main(String[] args) {
        shuffle(new int[]{1, 2, 3, 4, 5});
    }
}
/*
output:
before shuffle
1 2 3 4 5
after shuffle
5 2 3 1 4
 */
