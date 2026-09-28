#include <iostream>
// Required for using 2d vectors.
#include <vector>

// Includes the queue library and algorithm library.
#include <queue>
#include <algorithm>

// Allows for time based functions, such as sleep_for, which pauses the program for a specified duration.
#include <chrono>
#include <thread>

// Stores coordinates.
struct Robot {
    int x;
    int y;
};

struct Coordinate {
    int x;
    int y;
};

// Grid map
// 0 = free space
// 1 = obstacle
// std::vector is a dynamic array that can change size, and its elements are stored in memory locations.
std::vector<std::vector<int>> grid = {
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}

};

// const means the variable cannot be changed whatsoever.
void printGrid(const std::vector<std::vector<int>>& grid, const Robot& robot, const Coordinate& coord);
void moveRobot(Robot& robot, char direction);
bool isValid(const Robot& robot, const std::vector<std::vector<int>>& grid);
void findPath(Robot& robot, std::vector<std::vector<int>>& grid, const Coordinate& goal);
void markPath(std::vector<std::vector<int>>& grid, const Robot& robot);

int main() {

    Robot robot = {0, 0}; // Initialize robot at (0, 0)

    Coordinate goal = {0, 0};

    do{
        std::cout << "Enter robot's starting coordinates (x, y)";
        std::cin >> robot.x >> robot.y;

        if (!isValid(robot, grid)) {
            std::cout << "Invalid starting coordinates. Please try again. \n";
        }

        std::cout << "Enter the goal coordinates (x, y)";
        std::cin >> goal.x >> goal.y;

        if (!isValid(Robot{goal.x, goal.y}, grid)) {
            std::cout << "Invalid goal coordinates. Please try again. \n";
        }
    } while (!isValid(robot, grid) || !isValid(Robot{goal.x, goal.y}, grid));

    findPath(robot, grid, goal);
};

// Checks if the robot's coordinates are within the grid boundaries and are not an obstacle. Returns true if valid, false otherwise.
bool isValid(const Robot& robot, const std::vector<std::vector<int>>& grid) {
    // Check if robot's coordinates are within grid boundaries and are not an obstacle.
    if (robot.x < 0 || robot.x >=grid[0].size() || robot.y < 0 || robot.y >= grid.size()) {
        return false; // Out of bounds.
    }

    if (grid[robot.y][robot.x] == 1) {
        return false; // Hit an obstacle.
    }

    return true;
}

// Breadth-first search (BFS) algorithm to find the shortest path to the X on the vector.
void findPath(Robot& robot, std::vector<std::vector<int>>& grid, const Coordinate& goal) {

    // Creating another 2D vector which tracks the visited cells in the grid. All initial cells are unmarked.
    std::vector<std::vector<bool>> visited( // This is essentially a 2D array of true or false values. If visited becomes true.
        grid.size(), // The grid size is the number of rows in the grid.
        std::vector<bool>(grid[0].size(), // This grabs all the elements within the row, and creates a vector of bools with the same size as the row.
        false) // This initializes all the elements in the vector as false.
    );

    // Queue for BFS, storing robot's coordinates and the path taken to reach them.
    std::queue<std::pair<Robot, std::vector<char>>> q;
    // std::queue initializes the queue.
    // std::pair is a container holding two values.
    // The first value is the robot's coordinates (Robot) like above.
    // The second is an array of characters (std::vector<char>) representing the path taken to reach that position.

    // Initialize the queue with the robot's starting position and an empty path.
    q.push({robot, {}});
    // The push() function adds the robot's starting position with an empty path to the queue.

    // Continue BFS until the queue is empty.
    while (!q.empty()) { // While the queue is empty.
        auto [currentRobot, path] = q.front();
        // The front() function retrieves the first element in the queue without removing it.
        // auto is a keyword that allows the compiler to automatically deduce the type of variable inside.
        // [currentRobot, path] stores the robot's current coordinates and the path taken to reach that position.
        q.pop();
        // the pop() function removes the first element from the queue.
        // Essentially this while loop is saying while the queue is empty, grab the next step in the path, and remove it from the queue.

        // If the robot coordinates match with the goal coordinates, then print the path to the goal.
        if (currentRobot.x == goal.x && currentRobot.y == goal.y) {
            for (char move : path) { // For each character "move" in path.
                std::this_thread::sleep_for(
                    std::chrono::milliseconds(300)
                );
                moveRobot(robot, move); // The robot is then moved to the goal.
                markPath(grid, robot); // The path taken is marked on the grid.
                printGrid(grid, robot, goal);
                std::cout << std::endl;
                std::cout << "Path to goal: ";
                std::cout << move << " ";
                std::cout << std::endl;
            }
            std::cout << std::endl;
            return;
        }

        // If the current position is invalid or already visited, skip it.
        if (!isValid(currentRobot, grid) || visited[currentRobot.y][currentRobot.x]) {
            continue;
        }

        // Mark the current position as visited.
        visited[currentRobot.y][currentRobot.x] = true;

        // Explore all possible moves (Up, Down, Left, Right).
        std::vector<std::pair<char, Robot>> moves = {
            {'U', {currentRobot.x, currentRobot.y + 1}},
            {'D', {currentRobot.x, currentRobot.y - 1}},
            {'L', {currentRobot.x - 1, currentRobot.y}},
            {'R', {currentRobot.x + 1, currentRobot.y}}
        };

        // Add valid moves to the queue with the updated path.
        for (const auto& [direction, newRobot] : moves) {
            if (isValid(newRobot, grid)) {
                std::vector<char> newPath = path;
                newPath.push_back(direction);
                q.push({newRobot, newPath});
            }
        }
    }
}

// Prints the grid and the robot's position.
// Takes in the 2D vector grid and prints it to the console.
void printGrid(const std::vector<std::vector<int>>& grid, const Robot& robot, const Coordinate& coord) {
    // For every row in the grid, print each cell.
    for (int y = grid.size() - 1; y >= 0; --y) {
        // For every column in the row, print the cell.
        for (int x = 0; x < grid[y].size(); ++x) {
            if (robot.x == x && robot.y == y) {
                std::cout << "R "; // Print robot position
            } else if (coord.x == x && coord.y == y) {
                std::cout << "X "; // Print goal position
            } else if (grid[y][x] == 1) {
                std::cout << "# "; // Print obstacle
            } else if (grid[y][x] == 2) {
                std::cout << "* "; // Print path taken, if grid value is a 2, print *.
            } else {
                std::cout << ". "; // Print free space
            }
        }
        std::cout << '\n'; // New line after each row
    }
}

void moveRobot(Robot& robot, char direction) {
    switch(direction) {
        case 'U':
            robot.y += 1;
            break;
        case 'D':
            robot.y -= 1;
            break;
        case 'L':
            robot.x -= 1;
            break;
        case 'R':
            robot.x += 1;
            break;
    }
}

// Flips the grids 0's into 2's.
void markPath(std::vector<std::vector<int>>& grid, const Robot& robot) {
    int x = robot.x;
    int y = robot.y;

    grid[y][x] = 2;
}