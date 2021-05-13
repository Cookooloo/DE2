import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) throws FileNotFoundException {

        // scanner for keystrokes
        Scanner keyboard = new Scanner(System.in);

        // 1 or 2 player option
        System.out.println("1 of 2 spelers?");
        String players = keyboard.nextLine();
        String word;

        if (players.equals("1")) {
            // scanner for wordlist.txt and
            Scanner scanner = new Scanner(new File("wordlist.txt"));


            // empty list for adding words
            List<String> words = new ArrayList<>();
            // adds words to words List
            while (scanner.hasNext()){
                words.add(scanner.nextLine());
            }

            // Randomizer
            Random rand = new Random();
            // word picker that gets one word from the wordlist.txt
            word = words.get(rand.nextInt(words.size()));
        }
        else {
            System.out.println("Speler 1, voer jouw woord in:");
            word = keyboard.nextLine();
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("Speler 2, veel succes!");
        }



        // test print
        // System.out.println(word);

        // List of characters in word
        List<Character> playerGuessses = new ArrayList<>();

        int wrongCount = 0;
        // loop that keeps going while player hasn't guessed all words
        while(true) {

            // prints the Hanged Man
            printHangedMan(wrongCount);

            if (wrongCount >= 6){
                System.out.println("Helaas, je hebt verloren.");
                System.out.println("Het woord was: " + word);
                break;
            }

            // print letters at correct guessed places, else print -
            printWordState(word, playerGuessses);

            // get player input
            if (!getPlayerGuess(keyboard, word, playerGuessses)) {
                wrongCount++;
            }
            // if value returns true, stop program
            if (printWordState(word, playerGuessses)) {
                System.out.println("Je hebt gewonnen!");
                break;
            }

            // lets player guess entire word to win early
            System.out.println("Voer jouw gok in:");
            if (keyboard.nextLine().equals(word)) {
                System.out.println("Je hebt gewonnen!");
                break;
            }
            else {
                System.out.println("Helaas, probeer opnieuw!");
            }
        }
    }

    private static void printHangedMan(int wrongCount) {
        System.out.println(" -------");
        System.out.println(" |     |");
        if (wrongCount >= 1) {
            System.out.println(" O");
        }

        if (wrongCount >= 2) {
            System.out.print("\\ ");
            if (wrongCount >= 3) {
                System.out.println("/");
            }
        }

        if (wrongCount >= 4) {
            System.out.println(" |");
        }

        if (wrongCount >= 5) {
            System.out.print("/ ");
            if (wrongCount >= 6) {
                System.out.println("\\");
            }
            else {
                System.out.println("");
            }
        }
        System.out.println();
        System.out.println();
    }


    private static boolean getPlayerGuess(Scanner keyboard, String word, List<Character> playerGuessses) {
        System.out.println("Voer een letter in:");
        // System to prevent cheating. Only take the first letter of typed string
        String letterGuess = keyboard.nextLine();
        playerGuessses.add(letterGuess.charAt(0));

        return word.contains(letterGuess);
    }

    private static boolean printWordState(String word, List<Character> playerGuessses) {
        int correctCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if (playerGuessses.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));
                correctCount++;
            }
            else {
                System.out.print("-");
            }
        }
        System.out.println();

        return (word.length() == correctCount);
    }
}
