"""
Write a function canSum(targetSum, numbers) that takes in a targetSum and an array of numbers as arguments
and return a boolean value indicating whether it's possible to generate the targetSum using numbers from the array

An element can be used as many times as needed.
Assume that all elements of the array are positive only
"""
from typing import List


# ------------------------Recursive Implementation-------------------------------

def canSum_recursive(target_sum: int, numbers: List[int]) -> bool:
    # if target_sum get reduced to zero -> numbers chosen in the current tree path
    # add upto 7. Example: 7 (-4)-> 3 (-3)-> 0, so 4 + 3 = 7
    if target_sum == 0:
        return True

    # if target_sum goes below zero -> numbers chosen in the current tree path
    # doesnt add upto 7. Example: 7 (-5)-> 2 (-3)-> -1, so 5 + 3 != 7
    if target_sum < 0:
        return False

    # iterate over all numbers
    for num in numbers:
        if canSum_recursive(target_sum - num, numbers):
            return True

    # if nothing works
    return False


# ---------------------------Memoization Implementation-----------------------------

sum_memo = dict()


def canSum_memoized(target_sum: int, numbers: List[int]) -> bool:
    global sum_memo

    if target_sum in sum_memo:
        return sum_memo[target_sum]

    if target_sum == 0:
        return True

    if target_sum < 0:
        return False

    for num in numbers:
        if canSum_memoized(target_sum - num, numbers):
            sum_memo[target_sum] = True
            return sum_memo[target_sum]

    sum_memo[target_sum] = False
    return False


if __name__ == '__main__':
    print(canSum_recursive(8, [2, 3, 6, 7, 1]))
    print(canSum_recursive(4, [3, 6]))
    print(canSum_recursive(100, [2, 1, 10, 20]))  # takes longer time to complete execution

    print(canSum_memoized(2000, [10, 15]))        # faster execution
