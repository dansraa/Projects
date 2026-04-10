#include <iostream>

int main() {

    std::string name = "Daniel";
    double gpa = 2.5;
    char grade = 'B';
    char grades[] = {'A', 'B', 'C', 'D', 'F'};
    // The sizeof operator returns the size of a variable or data type in bytes.
    std::cout << "Size of double: " << sizeof(gpa) << " bytes\n";
    std::cout << "Size of string: " << sizeof(name) << " bytes\n";
    std::cout << "Size of char: " << sizeof(grade) << " bytes\n";
    std::cout << "Size of char array: " << sizeof(grades) << " bytes\n";

}