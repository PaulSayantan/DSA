"""
Write a function allConstruct(target, wordBank) that accepts target as a string and wordbank as a array of strings.

The function returns a 2D array containing all the ways that the 'target' can be constructed by concatenating the elements of the 'word_bank'
Each subarray consists of the combination of strings from 'wordbank' that constructs the 'target'
Reuse of words from wordbank is allowed.
"""

from typing import List

# ------------------------- Recursive Implementation-------------------


def allConstruct_recursive(target: str, word_bank: List[str]) -> List[List[str]]:
    if target == '':
        return [[]]

    all_target_combs = list()

    for word in word_bank:
        if target.startswith(word):
            suffix = target.removeprefix(word)
            suffix_combs = allConstruct_recursive(suffix, word_bank)

            for comb in suffix_combs:
                comb = [word, *comb]
                all_target_combs.append(comb)

    return all_target_combs


# ------------------------- Memoization Implementation-------------------

comb_memo = dict()


def allConstruct_memoize(target: str, word_bank: List[str]) -> List[List[str]]:
    global comb_memo

    if target in comb_memo:
        return comb_memo[target]

    if target == '':
        return [[]]

    all_target_combs = list()

    for word in word_bank:
        if target.startswith(word):
            suffix = target.removeprefix(word)
            suffix_combs = allConstruct_memoize(suffix, word_bank)
            for comb in suffix_combs:
                comb = [word, *comb]
                all_target_combs.append(comb)

    comb_memo[target] = all_target_combs
    return all_target_combs


if __name__ == '__main__':
    print(allConstruct_memoize('purple', ['purp', 'p', 'ur', 'le', 'purpl']))
