#include <iostream>

void sortAsc(int array[], int size);
void sortDesc(int array[], int size);

int main() {


    int array[] = {10, 1, 9, 2, 8, 3, 7, 4, 6, 5};
    int size = sizeof(array) / sizeof(array[0]);

    sortDesc(array, size);

    for(int element : array) {
        std::cout << element << " ";
    }

    return 0;
}

// Bubble Sort Algorithm
// Sorts the array in ascending order. If the value in index 0 is larger than 1, the values are swapped. This process is repeated until the array is sorted in ascending order.
void sortAsc(int array[], int size){
    int temp;
    for(int i = 0; i < size - 1; i++) {
        for(int j = 0; j < size - i - 1; j++)
          if(array[j] > array[j + 1]) { // If the value in index 0 is larger than 1, the values are swapped. This process is repeated until the array is sorted in ascending order.
              temp = array[j];
              array[j] = array[j + 1];
              array[j + 1] = temp;
          }
    }
}

// Sorts the array in descending order. If the value in index 0 is smaller than 1, the values are swapped. This process is repeated until the array is sorted in descending order.
void sortDesc(int array[], int size){
    int temp;
    for(int i = 0; i < size -1; i++) {
        for(int j = 0; j < size - i - 1; j++)
          if(array[j] < array[j + 1]) { // If the value in index 0 is smaller than 1, the values are swapped. This process is repeated until the array is sorted in descending order.
              temp = array[j];
              array[j] = array[j + 1];
              array[j + 1] = temp;
          }
    }
}