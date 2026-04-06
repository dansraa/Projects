#include <iostream>

double square(double length);
double cube(double length);

// Returning values from functions allows us to get results back from a function after it has performed its calculations. 
// In this example, we have two functions: square and cube, which calculate the area of a square and the volume of a cube, respectively, based on the given length.
int main(){
    double length = 7.0;
    double area = square(length); // Call the square function to calculate the area of a square with the given length.
    double volume = cube(length); // Call the cube function to calculate the volume of a cube with the given length.

    std::cout << "Volume: " << volume << std::endl; // Output the volume to the console.
    std::cout << "Area: " << area << std::endl; // Output the area to the console.
    return 0;
}

double square(double length){
    return length * length;
}

double cube(double length) {
    return length * length * length;
}