package main

import (
	"fmt"
	"log"
	"math"
	"sort"
)

/*
Consider infinite supply of coins: 10	5	2	1

If someone ask for an amount, how will we give that amount using minimum coins ?
 */

func minCoin(amount int, coins []int) (int, error) {
	if len(coins) == 0 {
		return 0, fmt.Errorf("you didn't specify what coins you have")
	}
	if amount == 0 {
		return 0, fmt.Errorf("you didn't specify an amount")
	}

	var result = 0
	for _, coin := range coins {
		if coin <= amount {
			nCoins := int(math.Floor(float64(amount / coin)))
			result = result + nCoins
			amount -= nCoins * coin
		}
		if amount == 0 {
			break
		}
	}

	return result, nil
}

func main() {
	var amount int
	fmt.Println("Enter the amount: ")
	_, err := fmt.Scanln(&amount)
	if err != nil || amount == 0 {
		log.Fatal(err)
	}

	var types int
	fmt.Println("How many types of coins you have: ")
	_, err = fmt.Scanln(&types)
	if err != nil || types == 0 {
		log.Fatal(err)
	}

	fmt.Println("Enter the types of coins you have: ")
	coins := make([]int, types)
	for i := range coins {
		_, _ = fmt.Scanf("%d", &coins[i])
	}

	// sorting the coins in descending order
	sort.Slice(coins, func(i, j int) bool {
		return coins[i] > coins[j]
	})

	count, err2 := minCoin(amount, coins)
	if err2 != nil {
		log.Fatal(err2)
	}
	fmt.Println("Minimum No of coins required: ", count)

}
