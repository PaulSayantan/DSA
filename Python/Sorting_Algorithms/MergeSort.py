# Implementation of Merge Sort Algorithm in Python3


from typing import List
import unittest


def MergeSort(arr: List[int], front: int, rear: int):
    """
    Applying Divide and Conquer strategy for sorting
    This is an In-place Algorithm

    :param arr: Unsorted Array of integers
    :param front: the starting index of the array
    :param rear:  the ending index of the array
    :return: recursively, the function itself
    """
    if front < rear:
        middle = (rear + front) // 2
        MergeSort(arr, front, middle)
        MergeSort(arr, middle + 1, rear)

        Merge(arr, front, middle, rear)


def Merge(arr: List[int], front: int, middle: int, rear: int):
    """
    Performing Dividing and Merging Operation on array

    :param arr: array of integers
    :param front: starting index of the array
    :param middle: middle index of the array
    :param rear: ending index of the array
    :return: None
    """

    # dividing array into left and right subarray of lengths n1 and n2
    n1 = middle - front + 1
    n2 = rear - middle

    # create and fill-up arrays Left and Right with elements of the array
    left_subarray = [arr[front + i] for i in range(n1)]
    right_subarray = [arr[middle + j + 1] for j in range(n2)]

    # compare between elements in left and right subarray and place them in original array
    i, j, k = 0, 0, front
    while i < n1 and j < n2:
        if left_subarray[i] < right_subarray[j]:
            arr[k] = left_subarray[i]
            i += 1
        else:
            arr[k] = right_subarray[j]
            j += 1
        k += 1

    # append left over elements from left subarray to original array
    while i < n1:
        arr[k] = left_subarray[i]
        i += 1
        k += 1

    # append left over elements from right subarray to original array
    while j < n2:
        arr[k] = right_subarray[j]
        j += 1
        k += 1


if __name__ == '__main__':
    arr = [int(x) for x in input("Enter your unsorted array: ").split()]
    MergeSort(arr, 0, len(arr) - 1)
    print("Sorted Array is: ", arr)