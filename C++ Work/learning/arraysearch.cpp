#include <iostream>

int searchArray(int array[], int size, int element);

int main() {

    int numbers[] = {10, 20, 30, 40, 50};
    int size = sizeof(numbers)/sizeof(numbers[0]);
    int index;
    int myNum;

    std::cout << "Enter a number to search for: " << '\n';
    std::cin >> myNum;

    index = searchArray(numbers, size, myNum);

    if (index != -1) {
        std::cout << "Element found at index: " << index << '\n';
    } else {
        std::cout << "Element not found in the array." << '\n';
    }

    return 0;
}

int searchArray(int array[], int size, int element){

    for (int i = 0; i < size; i++) {
        if (array[i] == element) {
            return i; // Return the index of the found element.
        }
    }
    return -1; // Return -1 if the element is not found in the array.
}