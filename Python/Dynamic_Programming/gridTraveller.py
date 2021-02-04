"""
    Say that there is a traveller on a 2D grid.

    Traveller starts moving from the top-left corner and to travel bottom-right corner.
    Constraint: Traveller can only move down or right.

    In how many ways can the traveller travel to the goal on a grid of dimensions m * n.

"""

# to memoize all the sub-paths visited
grid_memo = dict()


def gridTraveller(row: int, column: int):
    """

    :param row: number of rows in the grid
    :param column: number of columns in the grid
    :return: return 1 if the end cell can be visited if starting from a particular cell in grid else return 0
    """
    global grid_memo

    grid_cell = str(row) + ',' + str(column)

    if grid_cell in grid_memo:
        return grid_memo[grid_cell]

    if row == 1 and column == 1:
        return 1

    if row == 0 or column == 0:
        return 0

    grid_memo[grid_cell] = gridTraveller(row - 1, column) + gridTraveller(row, column - 1)
    return grid_memo[grid_cell]


if __name__ == '__main__':
    m, n = (int(x) for x in input("Enter the rows and column size of the grid: ").split(' '))
    print('Number of ways to travel to end: ', gridTraveller(m, n))
