#include <iostream>

// Inheritance is a class that is derived from another class.
// Inherits the attributes and methods from another class.
// Children inherit from the parent class.
// Reusing similar code from other classes.

class Animal{
    public:
        void eat() {
            std::cout << "The animal is eating.\n";
        }
};

class Dog : public Animal { // The Dog class is a child class of the Animal class. It inherits the eat() method from the Animal class.
    public:
        void bark() {
            std::cout << "The dog is barking.\n";
        }
};

int main() {

   Dog dog1;
   dog1.eat(); // The Dog class can use the eat() method from the Animal class because it inherits from it.
   dog1.bark(); // The Dog class can also use its own bark() method.

    return 0;
}