import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) throws FileNotFoundException {

        // Scanner for wordlist.txt
        Scanner scanner = new Scanner(new File("wordlist.txt"));

        // empty list for adding words
        List<String> words = new ArrayList<>();
        // adds words to words List
        while(scanner.hasNext()){
            words.add(scanner.nextLine());
        }

        // Randomizer
        Random rand = new Random();
        // word picker that gets one word from the wordlist.txt
        String word = words.get(rand.nextInt(words.size()));
        // test print
        System.out.println(word);

    }
}
