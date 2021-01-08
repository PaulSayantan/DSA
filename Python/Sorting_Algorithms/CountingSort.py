# Implementation Of Counting Sort in Python

import unittest
from typing import List


def counting_sort(arr: List[int], limit: int):
    """
    Counting Sort Algorithm

    T(N):
            Best Case:  Ω(n + k)

            Avg. Case:  Θ(n + k)

            Worst Case: O(n + k)

    S(N):   O(k)       where, k -> the largest value exists in the array

    :param arr:     An array of unsorted integers
    :param limit:   k, largest value exists in the array
    :return:
    """
    output_arr = [0] * len(arr)
    counter_arr = [0] * limit

    # fill the counter array that counts the occurrences of integer in the array
    for j in range(len(arr)):
        counter_arr[arr[j]] = counter_arr[arr[j]] + 1

    for i in range(1, limit):
        counter_arr[i] += counter_arr[i - 1]

    for j in range(len(arr)):
        output_arr[counter_arr[arr[j]] - 1] = arr[j]
        counter_arr[arr[j]] = counter_arr[arr[j]] - 1

    return output_arr


class SortingTest(unittest.TestCase):

    def test1(self):
        self.assertEqual(counting_sort([4, 8, 3, 7, 9, 1], 10), [1, 3, 4, 7, 8, 9])

    def test2(self):
        self.assertEqual(counting_sort([1, 1, 1, 0, 0], 2), [0, 0, 1, 1, 1])

    def test3(self):
        self.assertEqual(counting_sort([2], 3), [2])

    def test4(self):
        self.assertEqual(counting_sort([], 0), [])


if __name__ == '__main__':
    unittest.main()
