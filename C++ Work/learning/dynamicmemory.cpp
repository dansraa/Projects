#include <iostream>

int main() {

    int *pNum = NULL;

    pNum = new int; // The new operator allocates memory on the heap for an integer and returns a pointer to that memory. The pointer is then assigned to pNum.

    *pNum = 21;

    std::cout << "address: " << pNum << '\n';
    std::cout << "value: " << *pNum << '\n';

    delete pNum; // The delete operator deallocates the memory that was previously allocated by new. It takes a pointer as an argument and frees the memory that the pointer points to. 
    // After calling delete, the pointer becomes a dangling pointer, which means it still holds the address of the deallocated memory, but that memory is no longer valid.

    std::cout << "address: " << pNum << '\n';
    std::cout << "value: " << *pNum << '\n';

    // After deleting the memory, we should set the pointer to NULL to avoid dangling pointer issues. This way, we can check if the pointer is NULL before trying to access it.

}