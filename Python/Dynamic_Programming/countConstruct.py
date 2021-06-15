"""
Write a function countConstruct(target, wordBank) that accepts target as a string and wordbank as a array of strings.

The function returns the number of ways the 'target' can be constructed by concatenating the elements of the 'word_bank'

Reuse of words from wordbank is allowed.
"""

from typing import List


# ------------------- Recursive Implementation -------------------


def countConstruct_recursive(target: str, word_bank: List[str]) -> int:
    if target == '':
        return 1

    count = 0

    for word in word_bank:
        if target.startswith(word):
            count += countConstruct_recursive(target.removeprefix(word), word_bank)

    return count


# ------------------------- Memoization Implementation-------------------


count_memo = dict()


def countConstruct_memoize(target: str, word_bank: List[str]) -> int:
    global count_memo

    if target in count_memo:
        return count_memo[target]

    if target == '':
        return 1

    count = 0

    for word in word_bank:
        if target.startswith(word):
            count += countConstruct_memoize(target.removeprefix(word), word_bank)

    count_memo[target] = count
    return count_memo[target]


if __name__ == '__main__':
    print("Ways to construct \'purple\': ",
          countConstruct_recursive('purple', ['pur', 'p', 'pu', 'r', 'le', 'purp']))

    print("Ways to construct \'enterapotentpot\': ",
          countConstruct_memoize('enterapotentpot', ['a', 'p', 'ent', 'enter', 'ot', 'o', 't']))

