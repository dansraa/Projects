#include <iostream>
#include <ctime> //Randomness

// Entity Stat Structure
struct Entity {
    int health = 100;
    int attackPower = 10; // Currently Irrelevant.
};

void gameLoop();
void enemyEncounter();
void encounterType();
void shopEncounter();
void innEncoutner();
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
                    gameLoop();
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

void gameLoop(){
    // Placeholder for the main game loop. This will be where the player can explore, encounter enemies, find treasures, etc.
    srand(time(NULL));
    int choice = 0;

    do{
        std::cout << "Welcome to the game loop! What would you like to do?\n";
        std::cout << "1. Explore\n2. Visit Shop\n3. Rest at the Inn\n4. Exit Game Loop\n";
        std::cin >> choice;


        switch(choice){
            case 1:
                encounterType();
                break;
         case 2:
                shopEncounter();
                break;
            case 3:
                innEncoutner();
                break; //placeholder
            case 4:
                std::cout << "See you later!\n"; // Exit function.
                break;
             default: std::cout << "Invalid event generated.\n";
        }
    }while (choice != 4);
}

// Test Encounter Type Function
void encounterType(){
    srand(time(NULL)); // Seed random number generator with current time.
    int encounter = rand() % 3 + 1; // Generate a random number between 1 and 3 to determine the type of encounter.

    switch(encounter){
        case 1:
            enemyEncounter();
            break;
        case 2:
            std::cout << "You have found a treasure chest!\n";
            break;
        case 3:
            std::cout << "You have found a hidden passage!\n";
            break;
        default: std::cout << "Invalid encounter type generated.\n";
    }
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

void shopEncounter(){
    // Placeholder for shop encounter function. This will be where the player can buy and sell items, weapons, armor, etc.

    int choice = 0;

    do{
        std::cout << "Welcome to the shop! Enjoy my wares and take a gander!?\n";
        std::cout << "1. Buy Items\n2. Sell Items\n3. Leave Shop\n";
        std::cin >> choice;

        switch(choice){
            case 1:
                std::cout << "You selected buy items!\n";
                break; //placeholder
            case 2:
                std::cout << "You selected sell items!\n";
                break; //placeholder
            case 3:
                std::cout << "You selected leave shop!\n";
                break; // Exit function.
            default: std::cout << "Invalid option selected. Please select a valid option.\n";
        }
    }while (choice != 3);
}

void innEncoutner(){
    // Placeholder for inn encounter function.

    int choice = 0;

    do{
        std::cout << "Welcome to the inn! Would you like to rest and recover your health?\n";
        std::cout << "1. Rest\n2. Leave\n";
        std::cin >> choice;

        switch(choice){
            case 1:
                std::cout << "You selected yes!\n";
                break; //placeholder
            case 2:
                std::cout << "You selected no!\n";
                break; //placeholder
            default: std::cout << "Invalid option selected. Please select a valid option.\n";
        }
    }while (choice != 2);
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

