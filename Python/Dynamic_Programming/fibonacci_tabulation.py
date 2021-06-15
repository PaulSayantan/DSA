# Calculating Nth Fibonacci Number using Tabulation

def fibonacci_tabualtion(n: int) -> int:
    fibo_tab = [0] * (n + 1)
    fibo_tab[1] = 1

    ptr0, ptr1, ptr2 = 0, 1, 2

    for _ in range(n - 1):
        fibo_tab[ptr1] += fibo_tab[ptr0]
        fibo_tab[ptr2] += fibo_tab[ptr0]

        ptr0 += 1
        ptr1 += 1
        ptr2 += 1

    return fibo_tab[-1] + fibo_tab[-2]


if __name__ == '__main__':
    print(fibonacci_tabualtion(50))
