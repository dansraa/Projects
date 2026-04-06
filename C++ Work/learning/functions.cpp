#include <iostream>

// User declared function.
void happyBirthday(std::string name, int age){ // Passing name into happyBirthday function as a parameter.

    std::cout << "Happy Birthday to you!\n";
    std::cout << "Happy Birthday to " << name << "\n";
    std::cout << "Happy Birthday! You are " << age << " years old!\n";

}

int main() {

    int age = 26;
    std::string name = "Daniel"; // Declare a variable to hold the name.
    happyBirthday(name, age); // Call the function to print the birthday message.

    return 0;

}