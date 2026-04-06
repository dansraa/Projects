#include <iostream>

// Function overloading allows us to have multiple functions with the same name but different parameters.
int myNum = 3; // Global variable. Viewable by all functions in this file.

void buyShop(){
    std::cout << "Welcome to the shop! What would you like to buy?\n";
}
void buyShop(std::string item){
    std::cout << "You have bought a " << item << "!\n";
}
void buyShop(std::string item, std::string item2){
    std::cout << "You have bought a " << item << " & " << item2 << "!\n";
}

int main() {

    buyShop("sword", "gem");
    std::cout << "The value of myNum is: " << myNum << '\n';

}