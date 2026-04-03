#include <iostream>

int main() {
    std::string name;
    int number = -1;

    // While name is empty, keep asking the user to enter their name.
    while(name.empty()){
        std::cout << "Please enter your name: ";
        std::getline(std::cin, name);
    }

    std::cout << "Hello, " << name << "!" << '\n';

    // Do while loop. Keep asking the user to enter a positive number until they do.
    do { // Do this code. Executes once. Then check the condition. If the condition is true, do the code again. If the condition is false, exit the loop.
        std::cout << "Please enter a positive number: ";
        std::cin >> number;
    } while(number < 0);

    // For loop. The loop will continue until index reaches 10. Loop will execute code inside curly braces until index is equal to 10.
    for(int index = 1; index <= 10; index++){
        std::cout << index << "\n";
    }

    return 0;
}