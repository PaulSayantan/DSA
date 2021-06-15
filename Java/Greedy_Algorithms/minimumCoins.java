package Greedy_Algorithms;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class minimumCoins {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the amount of money: ");
        int amount = scan.nextInt();

        System.out.println("Enter how many types of coins you have: ");
        int types = scan.nextInt();

        Integer[] coins = new Integer[types];
        System.out.println("Enter the types of coins: ");
        for (int i = 0; i < types; i++) {
            coins[i] = scan.nextInt();
        }

        System.out.println("Minimum number of coins required to obtain the amount: "+minCoins(amount, coins));

    }

    public static int minCoins(int amount, Integer[] coins) {

        //  Sort the coins in descending order
        Arrays.sort(coins, Collections.reverseOrder());
        int min = 0;
        for (Integer coin: coins) {
            if (amount >= coin) {
                int nCoins = (int) Math.floor((double)(amount / coin));
                min = min + nCoins;
                amount -= coin * nCoins;
            }
        }

        return min;
    }
}