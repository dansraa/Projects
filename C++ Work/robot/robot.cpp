#include <iostream>
// Required for using 2d vectors.
#include <vector>
#include <queue>
#include <algorithm>

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

void printGrid(const std::vector<std::vector<int>>& grid, const Robot& robot, const Coordinate& coord);
void moveRobot(Robot& robot, char direction);
bool isValid(const Robot& robot, const std::vector<std::vector<int>>& grid);
void findPath(Robot& robot, const std::vector<std::vector<int>>& grid, const Coordinate& goal);

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

    printGrid(grid, robot, goal);
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
void findPath(Robot& robot, const std::vector<std::vector<int>>& grid, const Coordinate& goal) {

    // Tracks visited coordinates to avoid cycles.
    std::vector<std::vector<bool>> visited(grid.size(), std::vector<bool>(grid[0].size(), false));

    // Queue for BFS, storing robot's coordinates and the path taken to reach them.
    std::queue<std::pair<Robot, std::vector<char>>> q;

    // Initialize the queue with the robot's starting position and an empty path.
    q.push({robot, {}});

    // Continue BFS until the queue is empty.
    while (!q.empty()) {
        auto [currentRobot, path] = q.front();
        q.pop();

        // If the robot has reached the goal (X), print the path and return.
        if (currentRobot.x == goal.x && currentRobot.y == goal.y) {
            std::cout << "Path to goal: ";
            for (char move : path) {
                std::cout << move << " ";
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
            } else {
                std::cout << ". "; // Print free space
            }
        }
        std::cout << '\n'; // New line after each row
    }
}