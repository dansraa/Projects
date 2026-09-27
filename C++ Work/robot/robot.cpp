

// Stores coordinates.
struct Robot {
    int x;
    int y;
};


void moveRobot(Robot& robot, char direction) {
    // Coordinates are updated based on the direction provided.
    switch (direction) {
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