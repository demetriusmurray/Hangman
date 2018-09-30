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
    PrintOuts printOuts = new PrintOuts();

    int numAttemptsLeft;
    ArrayList<Character> attempts = new ArrayList<Character>();
    Scanner sc = new Scanner(System.in);

    public void homeScreen(String startTerm) {
        printOuts.printStart(startTerm);
        String startKey = sc.nextLine();
        if (startKey.length() == 0) {
            start();
        } else if (startKey.equals("NO")) {
            printOuts.printGoodbye();
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
        printOuts.displayWord(word3);
        printOuts.printStartGame(numAttemptsLeft);
        readInput();
    }

    public void readInput() {
        while (numAttemptsLeft > 0) {
            try {
                char userInput = sc.nextLine().charAt(0);
                userInput = Character.toUpperCase(userInput);
                storeInput(userInput);
            }
            catch (Exception a) {
                System.out.println("You have to make a guess. Try again");
                readInput();
            }
        }
    }

    public void storeInput(char letter) {
        if (!attempts.contains(letter)) {
            attempts.add(letter);
            rightOrWrong(letter);
        } else {
            printOuts.previousGuess(letter, numAttemptsLeft);
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
            printOuts.displayWord(word3);
            printOuts.printWon();
            homeScreen("play again");
        } else {
            printOuts.displayWord(word3);
            printOuts.printRightGuess(letter, numAttemptsLeft);
            printOuts.printAttempts(attempts);
        }
    }

    public void wrongAnswer() {
        numAttemptsLeft--;
        if (numAttemptsLeft == 0) {
            printOuts.printLose (word);
            homeScreen("try again");
        } else {
            printOuts.printWrongGuess(numAttemptsLeft);
            printOuts.printAttempts(attempts);
            printOuts.displayWord(word3);
        }
    }
}