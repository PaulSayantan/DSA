# Binary Search Algorithm


from typing import List
import unittest


def binary_search(arr: List[int], mini: int, maxi: int, target: int) -> int:
    """
    :param arr: Sorted Array of integers
    :param mini: a pointer marking the starting index of subarray
    :param maxi: a pointer marking the ending index of subarray
    :param target: the element to find in the array
    :return: the position of the target in the array
    """

    while mini <= maxi:
        mid = (maxi + mini) // 2

        if arr[mid] > target:
            """
            the target is present at the left part of the array
            so, concentrate the search at the leftside
            """
            maxi = mid - 1
        elif arr[mid] < target:
            """
            the target is present at the right part of the array
            so, concentrate the search on the rightside
            """
            mini = mid + 1
        else:
            return mid


class SearchTest(unittest.TestCase):
    def test1(self):
        self.assertEqual(binary_search([5, 9, 16, 25, 34], 0, 4, 9), 1)

    def test2(self):
        self.assertEqual(binary_search([9, 12, 28, 39, 49, 51], 0, 5, 39), 3)


if __name__ == '__main__':
    unittest.main()