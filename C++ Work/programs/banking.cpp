#include <iostream>
#include <iomanip>

void showBalance(double balance);
double deposit();
double withdraw(double balance);

int main() {
    double balance = 100;
    int choice = 0;

    // Do while loop to keep the program running until the user selects the exit option (4)
    do{
        std::cout << "Welcome to the bank! Please select an option:\n";
        std::cout << "1. Show Balance\n2. Deposit\n3. Withdraw\n4. Exit\n";
        std::cin >> choice;

      switch(choice){
            case 1:
             showBalance(balance);
             break;
           case 2: balance = balance + deposit();
               showBalance(balance);
               break;
         case 3:
               balance = balance - withdraw(balance);
                break;
           case 4:
                std::cout << "Thank you for banking with us! Goodbye!\n";
                break;
         default: std::cout << "Invalid option selected. Please select a valid option.\n";
    }
    }while(choice != 4);

    return 0;
}   

// Function to show the current balance with 2 decimal places.
void showBalance(double balance){
    std::cout << "Your current balance is: $"<< std::setprecision(2) << std::fixed << balance << '\n'; // Shows balance up to 2 decimals
}
// Function to deposit money into the account. Returns the amount deposited. Is a double, so value can be passed to balance. 
double deposit(){
    double amount = 0;

    std::cout << "Enter the amount you would like to deposit: $ ";
    std::cin >> amount;

    // Returns the amount deposited to be added to the balance in main. Else spit error message and return 0 to not change the balance.
    if(amount > 0){
        return amount;
    } else {
        std::cout << "Invalid amount entered. Please enter a positive number.\n";
        return 0;
    }
}
// Function to withdraw money from the account. Takes the current balance as a parameter and returns the amount withdrawn.
double withdraw(double balance){
    double amount = 0;
    std::cout << "Enter the amount you would like to withdraw: $ ";
    std::cin >> amount;
    switch(balance >= 0){ // After withdrawing from bank account, if balance is greater than 0 allow for withdrawal.
        case true:
           if(amount >0){ // If amount is greater than 0, return the amount to be subtracted from the balance in main.
            return amount;
           } else {
            std::cout << "Invalid amount entered. Please enter a positive number.\n";
            return 0;
           }
        case false: // If balance is less than 0, spit error message and return 0 to not change the balance.
            std::cout << "Insufficient funds. Your current balance is: $ " << std::setprecision(2) << std::fixed << balance << '\n';
            return 0;
    }
}