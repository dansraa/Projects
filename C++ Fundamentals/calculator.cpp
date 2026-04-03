#include <iostream>
#include <cmath>

int main() {
    double num1, num2;
    double result;
    char operation;

    std::cout << "Welcome to the Serra Cacl! (Short for calculator)" << '\n';
    std::cout << "Operations are: x (Multiplication), / (Division), + (Addition), - (Subtraction), % (Modulo)" << '\n';

    std::cout << "Enter the first number: ";
    std::cin >> num1;

    std::cout << "Enter the operation: ";
    std::cin >> operation;

    std::cout << "Enter the second number: ";
    std::cin >> num2;

    switch(operation){
        case '+':
            result = num1 + num2;
            std::cout << "The addition result is: " << result << '\n';
            break;
        case '-':
            result = num1 - num2;
            std::cout << "The subtraction result is: " << result << '\n';
            break;
        case 'x':
            result = num1 * num2;
            std::cout << "The multiplication result is: " << result << '\n';
            break;
        case '/':
            if(num2 != 0){
                result = num1 / num2;
                std::cout << "The division result is: " << result << '\n';
            }
            else {
                std::cout << "Error: Division by zero is not allowed." << '\n';
            }
            break;
        case '%':
            if(num2 != 0){
                result = fmod(num1, num2); // fmod is used for floating-point modulo
                std::cout << "The modulo result is: " << result << '\n';
            }
            else {
                std::cout << "Error: Modulo by zero is not allowed." << '\n';
            }
            break;
    }

    // Ternary operator. '?' means "if" and ':' means "else". It is a shorthand for an if-else statement. It is used to assign a value to a variable based on a condition.
    // Simpler way of writing an if statement. It is a shorthand for an if-else statement. It is used to assign a value to a variable based on a condition.
    result > 1000000 ? std::cout << "Wow, that's a big number!" << '\n' : std::cout << "The result is manageable." << '\n';

}