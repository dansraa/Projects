#include <iostream>

int main() {

    int month;
    std::cout << "Enter a number (1-12) to represent a day a month: ";
    std::cin >> month;

    // Switch case is more efficient than if-else statements when there are multiple conditions to check.
    // Allows for more efficient execution and cleaner code when dealing with multiple cases.
    switch(month){
        case 1:
            std::cout << "You selected January." << '\n';
            break;
        case 2:
            std::cout << "You selected February." << '\n';
            break;
        case 3:
            std::cout << "You selected March." << '\n';
            break;
        case 4:
            std::cout << "You selected April." << '\n';
            break;
        case 5:
            std::cout << "You selected May." << '\n';
            break;
        case 6:
            std::cout << "You selected June." << '\n';
            break;
        case 7:
            std::cout << "You selected July." << '\n';
            break; 
        case 8:
            std::cout << "You selected August." << '\n';
            break;
        case 9:
            std::cout << "You selected September." << '\n';
            break;
        case 10:
            std::cout << "You selected October." << '\n';
            break;
        case 11:   
            std::cout << "You selected November." << '\n';
            break;
        case 12:
            std::cout << "You selected December." << '\n';
            break;
        default: // Default is executed if none of the cases match the value of month. It is optional but can be used to handle unexpected input.
            std::cout << "Invalid month number." << '\n';
        
    }

    return 0;

}