"""
Write a function bestSum(targetSum, numbers) where it takes an integer targetSum and array of numbers as arguments.

The function should return an array of integers containing the shortest combination of numbers that add upto
targetSum.
"""
from typing import List, Optional


# ------------------------Recursive Implementation-------------------------------

def bestSum_recursive(target_sum: int, numbers: List[int]) -> Optional[List[int]]:
    if target_sum == 0:
        return []
    if target_sum < 0:
        return None

    shortest_comb: Optional[List[int]] = None

    for num in numbers:
        comb = bestSum_recursive(target_sum - num, numbers)
        if comb is not None:
            comb.append(num)
            if shortest_comb is None or len(shortest_comb) > len(comb):
                shortest_comb = comb

    return shortest_comb


# ---------------------------Memoization Implementation-----------------------------

shortestComb_memo = dict()


def bestSum_memoize(target_sum: int, numbers: List[int]) -> Optional[List[int]]:
    global shortestComb_memo

    if target_sum in shortestComb_memo:
        return shortestComb_memo[target_sum]

    if target_sum == 0:
        return []
    if target_sum < 0:
        return None

    shortest_comb: Optional[List[int]] = None

    for num in numbers:
        comb = bestSum_memoize(target_sum - num, numbers)
        if comb is not None:
            comb = [*comb, num]
            if shortest_comb is None or len(shortest_comb) > len(comb):
                shortest_comb = comb

    shortestComb_memo[target_sum] = shortest_comb
    return shortest_comb


if __name__ == '__main__':
    print(bestSum_recursive(8, [2, 4, 3, 5]))
    # # print(bestSum_recursive(100, [1, 2, 5, 25]))    # takes long to complete execution

    print(bestSum_memoize(100, [25, 5, 1]))
