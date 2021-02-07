"""
Write a function howSum(targetSum, numbers) where it takes an integer targetSum and array of numbers as arguments.

The function should return an array containing any combination of elements that add upto exactly the targetSum.
If there is no combination that adds upto the targetSum, then return null.
"""
from typing import List, Optional


# ------------------------Recursive Implementation-------------------------------

def howSum_recursive(target_sum: int, numbers: List[int]) -> Optional[list]:
    if target_sum == 0:
        return []
    if target_sum < 0:
        return None

    for num in numbers:
        result_arr = howSum_recursive(target_sum - num, numbers)
        if result_arr is not None:
            result_arr.append(num)
            return result_arr

    return None


# ---------------------------Memoization Implementation-----------------------------

sumArray_memo = dict()


def howSum_memoize(target_sum: int, numbers: List[int]) -> Optional[list]:
    global sumArray_memo

    if target_sum in sumArray_memo:
        return sumArray_memo[target_sum]

    if target_sum == 0:
        return []
    if target_sum < 0:
        return None

    for num in numbers:
        result_arr = howSum_memoize(target_sum - num, numbers)
        if result_arr is not None:
            result_arr.append(num)
            sumArray_memo[target_sum] = result_arr
            return sumArray_memo[target_sum]

    sumArray_memo[target_sum] = None
    return None


if __name__ == '__main__':
    print(howSum_recursive(7, [2, 4, 1]))
    # print(howSum_recursive(300, [7, 14]))   # takes long to complete execution

    print(howSum_memoize(300, [7, 14]))       # faster execution due to memoization


