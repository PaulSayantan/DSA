# Rat in a Maze is a Problem where, we need to find out out the shortest path to be traversed to reach the endpoint.
# This problem can be solved by using Backtracking technique.


def maze_solved(maze, x_cord, y_cord, output_maze) -> bool:
    # base case: if we have reached the solution, return true, path to endpoint has been found
    if x_cord == num_rows - 1 and y_cord == num_cols - 1:
        output_maze[x_cord][y_cord] = 1
        return True

    # if current position is Safe for next iteration, continue to move right or left, else mark position as 0
    if isSafe(maze, x_cord, y_cord):
        output_maze[x_cord][y_cord] = 1
        # move towards right
        if maze_solved(maze, x_cord + 1, y_cord, output_maze):
            return True
        # move towards down
        if maze_solved(maze, x_cord, y_cord + 1, output_maze):
            return True
        # if cannot move in either left or right, mark the position 0 and backtrack
        output_maze[x_cord][y_cord] = 0
        return False


def isSafe(maze, x_cord, y_cord):
    # check if x,y not outside the maze and x,y not in forbidden block
    return 0 <= x_cord < num_rows and 0 <= y_cord < num_cols and maze[x_cord][y_cord]


def path_solution(maze: 'a 2D array'):
    global output_maze

    if maze_solved(maze, 0, 0, output_maze):
        print('Path Found !!')
        for rows in output_maze:
            print(rows)
    else:
        print('Path Not Found !!')


if __name__ == '__main__':
    input_maze = [[1, 0, 1, 1, 1],
                  [1, 1, 0, 0, 0],
                  [1, 1, 1, 1, 0],
                  [1, 0, 1, 1, 0],
                  [0, 1, 1, 0, 1]]

    output_maze = [[0, 0, 0, 0, 0],
                   [0, 0, 0, 0, 0],
                   [0, 0, 0, 0, 0],
                   [0, 0, 0, 0, 0],
                   [0, 0, 0, 0, 0]]

    num_rows = len(input_maze)
    num_cols = len(input_maze[0])

    path_solution(input_maze)
