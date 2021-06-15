class HashSet:
    def __init__(self, contents):
        if contents is None:
            contents = []
        self.items = [None] * 10
        self.num_items = 0

        for item in contents:
            self.add(item)

    def __add(item, items):
        idx = hash(item) % len(items)
        loc = -1

        while items[idx] is not None:
            if items[idx] == item:
                # item already in the set
                return False

            if loc < 0 and type(items[idx]) == HashSet.__Placeholder:
                loc = idx

            idx = (idx + 1) % len(items)

        if loc < 0:
            loc = idx

        items[loc] = item

        return True
