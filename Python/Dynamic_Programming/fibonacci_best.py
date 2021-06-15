# Calculating Nth Fibonacci Number (Best Method)

# Time -> O(N)
# Space -> O(3)

def fibonacci_best(n: int) -> int:
    val1, val2 = 0, 1

    for _ in range(n):
        val3 = 0
        val2 += val1
        val3 += val1

        val1 = val2
        val2 = val3

    print(val1)


if __name__ == '__main__':
    fibonacci_best(10)
