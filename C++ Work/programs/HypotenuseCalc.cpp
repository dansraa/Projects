#include <iostream>
#include <cmath>

namespace data{
    double a;
    double b;
    double squareRoot;
}

int main() {
    std::cout << "Enter the lengths of the two sides of the right triangle: " << '\n';
    std::cin >> data::a >> data::b;

    // Calculates the hyptonenuse using the Pythagorean theorem: c = sqrt(a^2 + b^2).
    data::squareRoot = std::sqrt(std::pow(data::a, 2) + std::pow(data::b, 2));

    std::cout << "Hypotenuse length is: " << data::squareRoot << '\n';
}