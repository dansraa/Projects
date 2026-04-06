#include <iostream>
#include <ctime>

char getUserChoice();
char getComputerChoice();
void showChoice(char choice);
void chooseWinner(char player, char computer);

int main() {
    char player;
    char computer;

    player = getUserChoice();
    std::cout << "You chose: \n";
    showChoice(player);
    computer = getComputerChoice();
    std::cout << "\n";
    std::cout << "Winner decision: \n";
    chooseWinner(player, computer);

    return 0;
}

char getUserChoice(){
    char player;
    std::cout << "Welcome to Rock, Paper, Scissors! Please select an option:\n";
    do{
            std::cout << "R for Rock\nP for Paper\nS for Scissors\n";
            std::cin >> player;
    } while(player != 'R' && player != 'r' && player != 'P' && player != 'p' && player != 'S' && player != 's');
    
    return player;
}
char getComputerChoice(){
    char computer;
    srand(time(0));
    int randNum = (rand() % 3) + 1; // Generate a random number between 1 and 3.
    std::cout << "Computer is choosing...\n";
    switch(randNum){
        case 1:
            computer = 'R';
            break;
        case 2:
            computer = 'P';
            break;
        case 3:
            computer = 'S';
            break;
    }
    std::cout << computer;
    return 0;
}
void showChoice(char choice){
    switch(choice){
        case 'R':
        case 'r':
            std::cout << "Rock\n";
            break;
        case 'P':
        case 'p':
            std::cout << "Paper\n";
            break;
        case 'S':
        case 's':
            std::cout << "Scissors\n";
            break;
    }
}
void chooseWinner(char player, char computer){
    if(player == computer){
        std::cout << "It's a tie!\n";
    } else if((player == 'R' || player == 'r') && (computer == 'S' || computer == 's')){
        std::cout << "You win! Rock beats Scissors.\n";
    } else if((player == 'P' || player == 'p') && (computer == 'R' || computer == 'r')){
        std::cout << "You win! Paper beats Rock.\n";
    } else if((player == 'S' || player == 's') && (computer == 'P' || computer == 'p')){
        std::cout << "You win! Scissors beats Paper.\n";
    } else {
        std::cout << "Computer wins! Better luck next time.\n";
    }

}