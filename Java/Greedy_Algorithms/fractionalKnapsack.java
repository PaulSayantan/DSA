package Greedy_Algorithms;

import java.util.Arrays;
import java.util.Comparator;

class KnapSack {
    int weight;
    int price;

    public KnapSack(int weight, int price) {
        this.weight = weight;
        this.price = price;
    }
}

class SackCompare implements Comparator<KnapSack> {

    @Override
    public int compare(KnapSack o1, KnapSack o2) {
        return (o2.price / o2.weight) - (o1.price / o1.weight);
    }
}

public class fractionalKnapsack {
    public static void main(String[] args) {
        KnapSack[] sacks = {
                new KnapSack(50, 600),
                new KnapSack(20, 500),
                new KnapSack(30, 400)
        };

        int maxWeight = 70;

        System.out.println("Maximum Profit gained: "+maximumProfit(sacks, maxWeight));
    }

    public static int maximumProfit(KnapSack[] sacks, int weight) {

        //  Sort Sack in descending order of their P/W ratio
        Arrays.sort(sacks, new SackCompare());
        int profit = 0;

        for (KnapSack sack: sacks) {
            if (sack.weight <= weight) {
                weight -= sack.weight;
                profit += sack.price;
            } else {
                profit += (float)sack.price * ((float)weight / (float)sack.weight);
                return profit;
            }
        }

        return profit;
    }
}
