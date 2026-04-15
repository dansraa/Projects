#include <iostream>

// Enums are a user-defined data type that consists of a set of named constants.
// They are used to represent a fixed set of values. Known as enumerations.
// Great of there is a set of potential options.
enum DaysOfWeek {
    Sunday,
    Monday,
    Tuesday,
    Wednesday,
    Thursday,
    Friday,
    Saturday
};

int main() {

    DaysOfWeek today = Monday;

    switch(today){
        case Sunday:
            std::cout << "Today is Sunday.\n";
            break;
        case Monday:
            std::cout << "Today is Monday.\n";
            break;
        case Tuesday:
            std::cout << "Today is Tuesday.\n";
            break;
        case Wednesday:
            std::cout << "Today is Wednesday.\n";
            break; 
        case Thursday:
            std::cout << "Today is Thursday.\n";
            break; 
        case Friday:
            std::cout << "Today is Friday.\n";
            break;
        case Saturday:
            std::cout << "Today is Saturday.\n";
            break; 
    }

    return 0; 
}