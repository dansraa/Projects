#include <iostream>
#include <cmath>

int main() {
    char unit;
    double temp;

    std::cout << "Enter the unit (F for Farenheit, C for Celsius, K for Kelvin): ";
    std::cin >> unit;

    if(unit == 'F' || unit == 'f'){
        std::cout << "Enter the temperature in Farenheit: ";
        std::cin >> temp;
        double celsius = (temp - 32) * 5.0/9.0;
        double kelvin = celsius + 273.15;
        std::cout << "The temperature in Celsius is: " << celsius << " Celsius" << '\n';
        std::cout << "The temperature in Kelvin is: " << kelvin << " Kelvin" << '\n';
    }
    else if (unit == 'C' || unit == 'c') {
        std::cout << "Enter the temperature in Celsius: ";
        std::cin >> temp;
        double farenheit = (temp * 9.0/5.0) + 32;
        double kelvin = temp + 273.15;
        std::cout << "The temperature in Farenheit is: " << farenheit << " Farenheit" << '\n';
        std::cout << "The temperature in Kelvin is: " << kelvin << " Kelvin" << '\n';
    }
    else if (unit == 'K' || unit == 'k') {
        std::cout << "Enter the temperature in Kelvin: ";
        std::cin >> temp;
        double celsius = temp - 273.15;
        double farenheit = (celsius * 9.0/5.0) + 32;
        std::cout << "The temperature in Celsius is: " << celsius << " Celsius" << '\n';
        std::cout << "The temperature in Farenheit is: " << farenheit << " Farneheit" << '\n';
    }
    else {
        std::cout << "Invalid unit entered. Please enter F, C, or K." << '\n';
    }
    return 0;

}