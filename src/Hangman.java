import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) throws FileNotFoundException {

        // Scanner for wordlist.txt and keystrokes
        Scanner scanner = new Scanner(new File("wordlist.txt"));
        Scanner keyboard = new Scanner(System.in);

        // empty list for adding words
        List<String> words = new ArrayList<>();
        // adds words to words List
        while (scanner.hasNext()){
            words.add(scanner.nextLine());
        }

        // Randomizer
        Random rand = new Random();
        // word picker that gets one word from the wordlist.txt
        String word = words.get(rand.nextInt(words.size()));
        // test print
        System.out.println(word);

        // List of characters in word
        List<Character> playerGuessses = new ArrayList<>();

        // loop that keeps going while player hasn't guessed all words
        while(true) {
            // print letters at correct guessed places, else print -
            printWordState(word, playerGuessses);
            // get player input
            getPlayerGuess(keyboard, word, playerGuessses);
            // if value returns true, stop program
            if (printWordState(word, playerGuessses)) {
                System.out.println("You Win!");
                break;
            }

            // lets player guess entire word to win early
            System.out.println("Voer jouw gok in:");
            if (keyboard.nextLine().equals(word)) {
                System.out.println("You Win!");
                break;
            }
            else {
                System.out.println("Helaas, probeer opnieuw!");
            }
        }
    }


    private static void getPlayerGuess(Scanner keyboard, String word, List<Character> playerGuessses) {
        System.out.println("Voer een letter in:");
        // System to prevent cheating. Only take the first letter of typed string
        String letterGuess = keyboard.nextLine();
        playerGuessses.add(letterGuess.charAt(0));
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
