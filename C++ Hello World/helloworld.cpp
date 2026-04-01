#include <iostream>
#include <vector>

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
    typedef std::string text_t; // Gives the huge line of code a simpler name "NameAgeList"
    typedef int number_t;
    text_t pizza = "Pizza is delicious!";
    number_t pizzaAmount = 5;

        std::cout << pizza << pizzaAmount <<'\n';

    return 0;
}