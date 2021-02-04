#   FibonacciMemoization : Overlapping Subproblems Property in Dynamic_Programming

# to memoize the value of the pth fibbonacci number, where p < n
fibb_memo = dict()


def fibonacciMemoization(n: int):
    """
    :param n: range numbers to be visited in fibonacci series
    :return: nth value in the fibonacci sequence
    """

    global fibb_memo

    if n in fibb_memo:
        return fibb_memo[n]

    if n <= 2:
        return 1

    fibb_memo[n] = fibonacciMemoization(n - 1) + fibonacciMemoization(n - 2)

    return fibb_memo[n]


if __name__ == '__main__':
    num = int(input("Enter a number: "))
    print("Fib: ", fibonacciMemoization(num))