#include <iostream>

void walk (int steps);

int main() {

    walk(5);
    return 0;
}

// Iterative approach to functions.
//void walk (int steps) {
//    for (int i = 0; i < steps; i++) {
//        std::cout << "You take a step!.\n";
//    }
//}

// Recursive approach to functions.
// A recursive function is a function that calls itself. It has a base case that stops the recursion and a recursive case that continues to call the function until it reaches the base case. 
// In this example, the base case is when steps is 0, and the recursive case is when steps is greater than 0. The function will print the message and call itself with one less step until it reaches the base case.
// Recursion is good for algorithms.
void walk (int steps) {
    if (steps > 0) {
            std::cout << "You take a step!.\n"; // Recursive case: print the message and call the function again with one less step.
            walk(steps - 1);
    }
}