package com.company;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Random random = new Random();

        //creates new Arraylist and adds all the words under the variable name wordlist
        List<String> wordList = new ArrayList<String>();
        Collections.addAll(wordList,"around", "away", "become", "benefit", "discover", "easy", "environmental", "financial", "imagine","machine");

        //selects a number between 0-10 and picks a word from the list at random
        String word = wordList.get(random.nextInt(10));

        System.out.println(word);

        List<Character> playerGuesses = new ArrayList<>();

        int wrongCount = 0;
        while(true) {

            printHangMan(wrongCount);

            if (wrongCount >= 6){
                System.out.println("You lose");
                break;
            }


            printWordState(word, playerGuesses);
                if (!getPlayerGuess(keyboard, word, playerGuesses)) {
                    wrongCount++;
                }

                if (printWordState(word, playerGuesses)) {
                  break;
                }

                System.out.println("Please enter your guess for the word");
                if (keyboard.nextLine().equals(word)){
                    System.out.println("You win!");
                    break;
                }
                else {
                    System.out.println("Try again!");
                }
        }

    }

    //prints that hangman when wrong guesses reach a certain point
    private static void printHangMan(int wrongCount) {
        System.out.println(" ------");
        System.out.println("|   |");
        if (wrongCount >= 1){
            System.out.println("|   0");
        }

        if (wrongCount >=2) {
            System.out.println("|  /|\\ ");
        }

        if (wrongCount >= 3) {
            System.out.println("|  / \\");
        }

        if (wrongCount >= 4) {
            System.out.println("|");
        }

        if (wrongCount >= 5){
            System.out.println("|");
        }

        else {
            System.out.println("");
            }
    }

    //prompts the user to enter a letter and if the character is guessed that is in the word it will reveal the character
    private static boolean getPlayerGuess(Scanner keyboard, String word, List<Character> playerGuesses) {
        System.out.println("Please enter a letter");
        String letterGuess = keyboard.nextLine();
        playerGuesses.add(letterGuess.charAt(0));

        return word.contains(letterGuess);
    }

    //shows the word as dashes and depending on how long the word will change the amount of dashes
    private static boolean printWordState(String word, List<Character> playerGuesses) {
        int correctCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if (playerGuesses.contains(word.charAt(i))){
                System.out.print(word.charAt(i));
                correctCount++;
            }
            else{
                System.out.print("-");
            }
        }
        System.out.println();
    //if the player has guessed all the characters it will return as true and break the loop
        return (word.length() == correctCount);
    }
}
