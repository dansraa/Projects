#include <iostream>


// Structs allow for different types of information to be stored in a function.
struct Car {
    std::string make;
    std::string model;
    int year;
};

int main() {

    Car car1;
    car1.make = "Toyota";
    car1.model = "Camry";
    car1.year = 2020;

    Car car2;
    car2.make = "Honda";
    car2.model = "Civic";
    car2.year = 2019;

    std::cout << "Make: " << car1.make << '\n';
    std::cout << "Model: " << car1.model << '\n';
    std::cout << "Year: " << car1.year << '\n';

    std::cout << "Make: " << car2.make << '\n';
    std::cout << "Model: " << car2.model << '\n';
    std::cout << "Year: " << car2.year << '\n';


    return 0;
}