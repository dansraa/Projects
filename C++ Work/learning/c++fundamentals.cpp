#include <iostream>
#include <vector>
#include <string>
#include <cmath>

namespace first{
    int x = 1;
}

// Namespaces are used to organize code and prevent name conflicts. They allow us to have multiple variables with the same name in different contexts without causing errors.
namespace second{
    int x = 2;
}

int main() {
    std::cout << "Hello, World!" << std::endl;
    // std = standard, c = character, out = output, endl = end line
    // Prints hello world.
    
    std::cout << "This is a C++ program." << '\n';
    // '\n' is a newline character, it moves the cursor to the next line. More performant.

    /*
        Multi-line comment.
    */

    // Stores whole numebrs
    int x;
    int y;
    x = 5; // variable assignment
    y = 6;

        std::cout << "The value of x is: " << x << '\n';
        std::cout << "The value of y is: " << y << '\n';
    
    // Stores decimal numbers
    double z = 3.14;
    double gpa = 3.8;

        std::cout << "The double value of z is: " << z << '\n';
        std::cout << "The double value of gpa is: " << gpa << '\n';

    // Performs addition and stores the result in sum
    int sum = x + y; 

        std::cout << "The sum of x and y is: " << sum << '\n';

    // Single characters
    char grade = 'A';
    char dollarsign = '$';

        std::cout << "The grade is: " << grade << '\n';
        std::cout << "The character is: " << dollarsign << '\n';

    // Boolean values (true or false)
    bool student = true;
    bool powerOn = false;

        std::cout << "Is the person a student? " << student << '\n';
        std::cout << "Is the power on? " << powerOn << '\n';

    // Sequence of characters.
    std::string firstName = "Daniel";
    std::string lastName = "Serra";

        std::cout << "Your name is: " << firstName + " " + lastName << '\n';

    // Constants (unchangeable values)
    const double PI = 3.14159; // Adds data security to prevent changing of data.
    double radius = 10;
    double circumference = 2 * PI * radius;

        std::cout << "The circumference of the circle is: " << circumference  << "cm" << '\n';


    // Calling from other namespaces to avoid conflicts
    std::cout << "The value of x in the first namespace is: " << first::x << '\n';
    std::cout << "The value of x in the second namespace is: " << second::x << '\n';

    // typedef is used to give an alias for a data type. It can make code more readable and easier to understand.
    typedef std::string text_t; // Gives the huge line of code a simpler name "NameAgeList".
    typedef int number_t;
    text_t pizza = "Pizza is delicious!";
    number_t pizzaAmount = 5;

        std::cout << pizza << pizzaAmount <<'\n';

    
    // Arithmetic Operators
    int students = 10;
    int b = 3;

    students = students + 1; // Addition
    students +1; // Addition (does not change the value of students)
    students--; // Subtraction
    students-=2; // Subtraction
    students *= 2; // Multiplication
    students = students * 2; // Multiplication
    students /= 2; // Division
    students = students / 2; // Division
    int remainder = students % b; // Modulo (gives the remainder of the division)


    // Type conversion. Convert a value of one data type to another.
    double convert = (int)3.14; // C-style cast. Converts the double value 3.14 to an integer, resulting in 3.
    std::cout << "The converted value is: " << convert << '\n';

    // Accepting user input
    std::string userInput;
    std::cout << "Enter your name: ";
    //std::cin >> userInput; // Takes input from the user and stores it in the variable userInput. "cin" stands for "character input". It reads input from the standard input (usually the keyboard) and stores it in the variable provided.
    // Below allows for space in the input, while the above does not.
    std::getline(std::cin, userInput); // Reads a line of text from the standard input and stores it in the variable userInput. It allows for spaces in the input.
    
    std::cout << "Hello, " << userInput << "!" << '\n'; 

    //Useful math functions
    double num_x = 3;
    double num_y = 4;
    double greaterVal;
    double minVal;
    double powerFun;
    double squareRoot;
    double absVal;
    double roundVal;
    double roundDownVal;
    double roundUpVal;

    greaterVal = std::max(num_x, num_y); // Returns the greater of the two values.
    minVal = std::min(num_x, num_y); // Returns the lesser of the two values.
    std::cout << "The greater value is: " << greaterVal << '\n';
    std::cout << "The lesser value is: " << minVal << '\n';

    powerFun = pow(num_x, num_y); // Returns the value of num_x raised to the power of num_y.
    squareRoot = sqrt(num_x); // Returns the square root of num_x.
    absVal = abs(num_x); // Returns the absolute value of -5, which is 5.
    roundVal = round(3.14); // Rounds the value to the nearest integer, which is 3.
    roundDownVal = floor(3.14); // Rounds the value down to the nearest integer, which is 3.
    roundUpVal = ceil(3.14); // Rounds the value up to the nearest integer, which is 4.
    std::cout << powerFun << '\n';
    std::cout << squareRoot << '\n';

    return 0;
}