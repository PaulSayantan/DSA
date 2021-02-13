"""
Write a function canConstruct(target, wordBank) that accepts target as a string and wordbank as a array of strings.

The function should return a boolean indicating whether or not the 'target' can be constructed by concatenating the
elements of the wordBank. Reuse of words are allowed.

"""
from typing import List


# ------------------- Recursive Implementation -------------------


def canConstruct_recursive(target: str, word_bank: List[str]) -> bool:
    # base case
    if target == '':
        return True

    # iterate through the word_bank and check if word is a preffix of target
    for word in word_bank:
        if target.startswith(word):
            # preffix exists, extract suffix and attempt recursive call
            suffix = target.removeprefix(word)
            if canConstruct_recursive(suffix, word_bank):
                return True

    # if non of the words are preffix, return False
    return False


# ------------------------- Memoization Implementation-------------------


def canConstruct_memoized(target: str, word_bank: List[str], target_memo: dict) -> bool:
    if target in target_memo:
        return target_memo[target]
    if target == '':
        return True

    for word in word_bank:
        if target.startswith(word):
            suffix = target.removeprefix(word)
            if canConstruct_memoized(suffix, word_bank, target_memo):
                target_memo[target] = True
                return True

    target_memo[target] = False
    return False


if __name__ == '__main__':
    print(canConstruct_recursive('abcdef', ['ab', 'abc', 'def', 'cd']))
    print(canConstruct_recursive('skateboard', ['ate', 'sk', 'bo', 'ska', 'rd']))
    # print(canConstruct_recursive('eeeeeeeeeeeeeeeeeeeef', ['e', 'ee', 'eee', 'eeeeee', 'eeee',
    #                                                         'eeeeeeee'])) # slower execution

    print(canConstruct_memoized('eeeeeeeeeeeeeeeeeeeef', ['e', 'ee', 'eee', 'eeeeee', 'eeee',
                                                          'eeeeeeee'], dict()))  # faster execution
