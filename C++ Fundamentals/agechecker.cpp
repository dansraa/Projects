#include <iostream>

int main() {

    int age;

    std::cout << "Enter your age: ";
    std::cin >> age;

    // Age checker if statement.
    if(age > 100){
        std::cout << "Your unc man." << '\n';
    }
    else if(age < 0){
        std::cout << "Invalid age." << '\n';
    }
    else if(age >= 18){
        std::cout << "You are an adult." << '\n';
    }
    else {
        std::cout << "You are a minor." << '\n';
    }

    return 0;

}