#include <iostream>

double getTotal(double prices[]);

int main() {

    std::string cars[] = {"Toyota", "Honda", "Ford", "Chevrolet", "Nissan"};
   
    std::cout << cars[0] << '\n';
    std::cout << cars[1] << '\n';
    std::cout << cars[2] << '\n';
    std::cout << cars[3] << '\n';
    std::cout << cars[4] << '\n';

    double prices[] = {19.99, 29.99, 39.99, 49.99, 59.99};
    double total = getTotal(prices); // This line calls the getTotal function, passing the prices array as an argument. The function is expected to calculate and return the total of the prices in the array, which is then stored in the variable total.

    std::cout << "Total: " << total << '\n';

    // For loops are a more efficient way to iterate through arrays, especially when the size of the array is large or not known at compile time.
    for (int i = 0; i < 5; i++) {
        std::cout << prices[i] << '\n';
    }

    for(std::string car : cars){
        std::cout << car << '\n';
    }

    return 0;
}

// Calculates the total of the prices array.
double getTotal(double prices[]) {
    double total = 0.0;
    for (int i = 0; i < 5; i++) {
        total += prices[i];
    }
    return total;

}