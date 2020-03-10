package AdventureGame;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        
        
        // System Objects
        Scanner in = new Scanner(System.in);
        Random rand = new Random();
        
        // Game Variables
        String[] Enemies = { "Skeleton", "Zombie", "Warrior", "Assasin" };
        int maxEnemyHealth = 25;
        int enemyHealthIncreaser = 3;
        int enemyAttackDamage = 5;
        int enemyAttackIncreaser = 2;
        
        // Player variables
        int health = 25;
        int healthIncreaser = 5;
        int attackDamage = 10;
        int attackDamageIncreaser = 2;
        
        int dungeonLevel = 1;
        int dungeonSubLevel = 1;
        
        int numHealthPotions = 3;
        int healthPotionHealAmount = 30;
        int healthPotionDropChance = 50; //  <-- Precentage  \\
        
        boolean running = true;
        
        System.out.println("Welcome to the Dungeon!");
                
        GAME:
        while(running){
            System.out.println("---------------------------------------------");
            
            int enemyHealth = rand.nextInt(maxEnemyHealth);
            String enemy = Enemies[rand.nextInt(Enemies.length)];
            System.out.println("\t# " + enemy + " has appeared! #\n");
            
            if(enemyHealth == 0) {
                enemyHealth = rand.nextInt(maxEnemyHealth);
            }
            
            
            OUTER:
            while (enemyHealth > 0) {
                System.out.println("\t");
                System.out.println("\tDungeon Level: " + dungeonLevel + " - " + dungeonSubLevel);
                System.out.println("\tYour HP: " + health);
                System.out.println("\tYour attack Stat: " + attackDamage);
                System.out.println("\n");
                System.out.println("\t" + enemy + "'s HP: " + enemyHealth);
                System.out.println("\tEnemy Attack Stat: " + enemyAttackDamage);
                System.out.println("\n\tWhat would you like to do?");
                System.out.println("\t1. Attack");
                System.out.println("\t2. Drink Health Potion");
                System.out.println("\t3. Run!");
                String input = in.nextLine();
                
                switch (input) {
                    case "1":
                        int damageDealt = rand.nextInt(attackDamage);
                        int damageTaken = rand.nextInt(enemyAttackDamage);
                        enemyHealth -= damageDealt;
                        health -= damageTaken;
                        System.out.println("---------------------------------------------");
                        System.out.println("\t> You strike the " + enemy + " for " + damageDealt + " damage.");
                        System.out.println("\t> You receive " + damageTaken + " in retaliation!");
                        if (health < 1) {
                            System.out.println("\t> You have taken too much damage, you are too weak to go on!");
                            break OUTER;
                        }
                        break;
                        
                    case "2":
                        if (numHealthPotions > 0) {
                            health += healthPotionHealAmount;
                            numHealthPotions--;
                            System.out.println("\t> You drink a health potion, healing yourself for " + healthPotionHealAmount + "."
                                    + "\n\t> You now have: " + health + " HP."
                                    + "\n\t> You have " + numHealthPotions + " health potions left.\n");
                        } else {
                            System.out.println("\t> You have no health potions left! Deafeat enemies for a chance to get one!");
                        }   break;
                        
                    case "3":
                        System.out.println("\tYou run away from the " + enemy + "!");
                        continue GAME;
                        
                    default:
                        System.out.println("\tInvalid command!");
                        break;
                }
            }
            
            if (health < 1) {
                System.out.println("\n\tYou limp out of the dungeon, weak from battle!\n");
                break;
            }
            
            System.out.println("---------------------------------------------");
            System.out.println(" # " + enemy + " was defeated! #");
            System.out.println(" # You have " + health + " HP left. #");
            System.out.println(" # You have " + numHealthPotions + " HealthPotions Left. *");
            if(rand.nextInt(100) < healthPotionDropChance) {
            numHealthPotions++;
            System.out.println(" # The " + enemy + " dropped a health potion! # ");
            System.out.println(" # You now have " + numHealthPotions + " health potion(s). #");
            }
            System.out.println("---------------------------------------------");
            System.out.println("What would you like to do now?");
            System.out.println("1. Continue fighting");
            System.out.println("2. Exit dungeon");
            
            //Dungeon Gets Harder the more you play!            
            dungeonSubLevel++;
            if(dungeonSubLevel == 5) {
                dungeonSubLevel = 1;
                dungeonLevel++;
                
                health += healthIncreaser;
                healthIncreaser += 5;
                        
                attackDamage += attackDamageIncreaser;
                attackDamageIncreaser += 2;
                
                enemyAttackDamage += enemyAttackIncreaser;
                enemyAttackIncreaser += 2;
                
                maxEnemyHealth += enemyHealthIncreaser;
                enemyHealthIncreaser += 2;
            }
            
            String input = in.nextLine();
            
            while(!input.equals("1") && !input.equals("2")) {
                System.out.println("Invalid command!");
                input = in.nextLine();
            }
            
            if(input.equals("1")) {
                System.out.println("You continue on your adventure!");
            }
            else if (input.equals("2")) {
                System.out.println("You exit the dungeon, successful from your adventures!");
                break;
            }
        }
        System.out.println("\n\t#######################");
        System.out.println("\n\t# Thanks for playing! #");
        System.out.println("\n\t#######################");
    }
}