#include <iostream>
#include <ctime>

int main() {

    srand(time(NULL)); // Seed the random number generator with the current time.


    // RNG (Random Number Generation)
    // Generate a random number between 0 and 5 + 1 (inclusive).
    int num1 = (rand() % 6) + 1; // Modulo (value) operator gives the remainder of the division. So, rand() % 6 will give a number between 0 and 5.
    int num2 = (rand() % 20) + 1;
    int num3 = (rand() % 100) + 1;
    
    std::cout << num1 << '\n';
    std::cout << num2 << '\n';
    std::cout << num3 << '\n';

    //Random Event generation example:
    srand(time(0));
    int randNum = rand() % 5 + 1; // Generate a random number between 1 and 5.

    // Use a switch statement to determine the event based on the random number (randNum).
    switch(randNum){
        case 1:
            std::cout << "You found a treasure chest!\n";
            break;
        case 2:
            std::cout << "You encountered a wild monster!\n";
            break;
        case 3:
            std::cout << "You found a hidden passage!\n";
            break;
        case 4:
            std::cout << "You found a magical potion!\n";
            break;
        case 5:
            std::cout << "You found a trap!\n";
            break;
        case 6:
            std::cout << "You found a secret door!\n";
            break;
    }

    return 0;
}