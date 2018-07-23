package jav;

import java.util.ArrayList;
import java.util.Scanner;
import java.lang.*;

/* WHAT I NEED TO DO TO CLEAN UP
*   Create class for print outs. make those insertable via parameters
*   multiple letters in word
*   non-case sensitive
*
* */


public class Hangman {
    WordBank wordBank = new WordBank();
    String word;
    String word2 = new String();
    String word3 = word2;

    int numAttemptsLeft;
    ArrayList<Character> attempts = new ArrayList<Character>();
    Scanner sc = new Scanner(System.in);

    public void homeScreen(String startTerm) {
        System.out.println();
        System.out.println("Press the 'ENTER' key to " + startTerm + "\n ...or type 'NO' to close game");
        String startKey = sc.nextLine();
        if (startKey.length() == 0) {
            start();
        } else if (startKey.equals("NO")) {
            System.out.println("Goodbye.");
        } else homeScreen("begin playing");
    }

    public void start() {
        int randomInt = (int) (Math.random() * wordBank.words.length);
        word = wordBank.words[randomInt];
        word = word.toUpperCase();
        word2 = "";
        numAttemptsLeft = 5;
        attempts.clear();
        for (char letter : word.toCharArray()) {
            word2 += "-";
        }
        word3 = word2;
        System.out.println(word3);
        System.out.println("You have " + numAttemptsLeft + " attempts remaining. Good luck!");
        readInput();
    }

    public void readInput() {
        while (numAttemptsLeft > 0) {
            char userInput = sc.nextLine().charAt(0);
            userInput = Character.toUpperCase(userInput);
            storeInput(userInput);
        }
    }

    public void storeInput(char letter) {
        if (!attempts.contains(letter)) {
            attempts.add(letter);
            rightOrWrong(letter);
        } else {
            System.out.println("This letter '" + letter + "' was already guessed.");
            System.out.println("You still have " + numAttemptsLeft + " remaining.");
        }
    }

    public void rightOrWrong(char letter) {
        int indexGuess = word.indexOf(letter);
        if (indexGuess >= 0) {
            rightAnswer(indexGuess, letter);
        } else {
            wrongAnswer();
        }
    }

    public void rightAnswer(int indexGuess, char letter) {

        while (indexGuess >= 0) {
            word3 = word2.substring(0, indexGuess) + letter + word2.substring(indexGuess + 1);
            word2 = word3;
            indexGuess = word.indexOf(letter, indexGuess + 1);
        }

        if (!word2.contains("-")) {
            System.out.println(word3);
            System.out.println("You Won!");
            homeScreen("play again");
        } else {
            System.out.println(word3);
            System.out.println("\nNice! " + "'" + letter + "' is in this word.\nYou still have " + numAttemptsLeft + " attempts remaining.");
            System.out.println("Your previous attempts are " + attempts);
        }
    }

    public void wrongAnswer() {
        if (numAttemptsLeft == 0) {
            System.out.println("You have no attempts left.");
            System.out.println("The word you were trying to rightOrWrong was '" + word + "'");
            homeScreen("try again");
        }
        System.out.println("This is not a letter");
        numAttemptsLeft--;
        System.out.println("You have " + numAttemptsLeft + " attempts remaining.");
        System.out.println(word3);
    }
}

//Mind Sex - Dead Presidents
   /*
     make it non case sensitive
     Deal with multiple letters in a word


     Question: Is it better to pass parameters through, call objects from other methods, or make objects global?

*/