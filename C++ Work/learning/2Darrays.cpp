#include <iostream>

int main() {

    // A 2D array is an array of arrays. It is a grid-like structure of data. Similar to a matrix. It is a collection of rows and columns. The first dimension represents the number of rows, and the second dimension represents the number of columns.
    std::string cars[][3] = {{"Toyota", "Honda", "Ford"}, 
                            {"Chevrolet", "Nissan", "Mazda"}, 
                            {"BMW", "Mercedes", "Audi"}};

    //Accessed by using the row and column index. The row index is the first index, and the column index is the second index. The index starts at 0.
    // Iterating over a 2d array.
    int rows = sizeof(cars) / sizeof(cars[0]); // This line calculates the number of rows in the 2D array by dividing the total size of the array by the size of a single row (which is an array of strings).
    int cols = sizeof(cars[0]) / sizeof(cars[0][0]); // This line calculates the number of columns in the 2D array by dividing the size of a single row (which is an array of strings) by the size of a single element (which is a string).

    for(int i = 0; i < rows; i++) {
        for(int j = 0; j < cols; j++) {
            std::cout << cars[i][j] << " ";
        }
        std::cout << '\n';
    }

    return 0;
}