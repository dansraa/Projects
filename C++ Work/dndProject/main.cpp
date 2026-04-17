#include <iostream>
#include <ctime> //Randomness

// Entity Stat Structure
struct Entity {
    int health = 100;
    int attackPower = 10; // Currently Irrelevant.
};

void enemyEncounter();
void playerAttack(Entity &enemy);
void enemyAttack(Entity &player);


// Main Menu
int main() {

    int choice = 0;

    do{
        std::cout << "Welcome to the Dungeons and Dragons text based game!\n";
        std::cout << "Please select an option:\n";
        std::cout << "1. Start New Game\n2. Load Game\n3. Exit\n";
        std::cin >> choice;

           switch(choice){
              case 1:
               std::cout << "Starting new game...\n";
                  enemyEncounter();
                break;
             case 2: 
                    std::cout << "Loading game...\n";
                    break;
           case 3:
                    std::cout << "Exiting game. Goodbye!\n";
                     break;
              default: std::cout << "Invalid option selected. Please select a valid option.\n";
            }
    }while(choice != 3);
    return 0;
}

// Test Enemy Encounter Function
void enemyEncounter(){

    Entity enemy = {50, 10}; // Create an enemy with 100 health and 10 attack power.
    Entity player = {100, 10}; // Create a player with 100 health and 10 attack power.

    int choice = 0;

    // Do while loop to keep enemy encounter running until enemy health reaches 0 or player runs away.
    do{
        std::cout << "You have encountered an enemy!\n";
        std::cout << "What would you like to do?\n";
        std::cout << "1. Attack\n2. Defend\n3. Run\n";
        std::cin >> choice;

        switch(choice){
            case 1:
                std::cout << "You attack the enemy!\n";
                playerAttack(enemy);
                std::cout << "Enemy health: " << enemy.health << "\n";
                enemyAttack(player);
                std::cout << "Player health: " << player.health << "\n";
                break;
            case 2: 
                std::cout << "You defend against the enemy's attack!\n";
                break;
            case 3:
                std::cout << "You run away from the enemy!\n";
                break;
            default: std::cout << "Invalid option selected. Please select a valid option.\n";
        }
    }while (choice != 3 && enemy.health > 0);
}

// Test Player Attack
void playerAttack(Entity &enemy){

    srand(time(NULL)); // Seed the random number generator with the current time.

    int damage = 0;
    damage = rand() % 10 + 1; // Random damage between 1 and 10

    enemy.health -= damage; // Subtract damage from enemy health

    std::cout << "You dealt " << damage << " damage to the enemy!\n";
}

// Test Enemy Attack
void enemyAttack(Entity &player){

    srand(time(NULL)); // Seed the random number generator with the current time.

    int damage = 0;
    damage = rand() % 10 + 5; // Random damage between 1 and 10

    player.health -= damage; // Subtract damage from enemy health

    std::cout << "You dealt " << damage << " damage to the enemy!\n";
}