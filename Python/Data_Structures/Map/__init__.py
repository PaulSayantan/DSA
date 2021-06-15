from abc import ABC
from collections import MutableMapping


class Map(MutableMapping, ABC):
    """An abstract base class that includes a nonpublic _Item class"""

    def __init__(self, key, value):
        self.key = key
        self.value = value

    def __eq__(self, other):
        return self.key == other.key

    def __ne__(self, other):
        return not self == other

    def __lt__(self, other):
        return self.key < other.key
