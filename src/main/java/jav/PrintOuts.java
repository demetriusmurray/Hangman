package jav;
import java.lang.*;
import java.util.ArrayList;

public class PrintOuts {
    String attemptWord = new String();
    public String attemptPlural(int numAttemptsLeft) {
        if (numAttemptsLeft == 1) {
            attemptWord = "attempt";
        } else {
            attemptWord = "attempts";
        }
        return attemptWord;
    }

    public void printWelcome(){
        System.out.println("Greetings... Let's Play Hangman!");
    }

    public void printStart(String startTerm) {
        System.out.println("\nPress the 'ENTER' key to " + startTerm + "\n ...or type 'NO' to close game");
    }

    public void displayWord (String dWord) {
        System.out.println(dWord);
    }

    public void printStartGame(int numAttemptsLeft) {
        attemptPlural(numAttemptsLeft);
        System.out.println("You have " + numAttemptsLeft + " " + attemptWord + " remaining. Good luck!");

    }

    public void previousGuess(char letter, int numAttemptsLeft){
        attemptPlural(numAttemptsLeft);
        System.out.println("This letter '" + letter + "' was already guessed.");
        System.out.println("You still have " + numAttemptsLeft + attemptWord + " remaining.");
    }

    public void printAttempts(ArrayList<Character> attempts) {
        System.out.println("Your previous attempts are " + attempts);
    }

    public void printRightGuess(char letter, int numAttemptsLeft) {
        attemptPlural(numAttemptsLeft);
        System.out.println("\nNice! " + "'" + letter + "' is in this word.\nYou still have " + numAttemptsLeft + " " + attemptWord + " remaining.");
    }

    public void printWrongGuess(int numAttemptsLeft) {
        attemptPlural(numAttemptsLeft);
        System.out.println("This is not a letter");
        System.out.println("You have " + numAttemptsLeft + " " + attemptWord + " remaining.");
    }

    public void printLose(String word) {
        System.out.println("You have 0 attempts left.");
        System.out.println("The word you were trying to guess was '" + word + "'");
    }
    public void printWon(){
        System.out.println("You Won!");
    }

    public void printGoodbye() {
        System.out.println("Goodbye.");
    }
}
