import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final String[] words = {
            "ABSTRACT", "ASSERT", "BOOLEAN", "BREAK", "BYTE",
            "CASE", "CATCH", "CHAR", "CLASS", "CONST",
            "CONTINUE", "DEFAULT", "DOUBLE", "DO", "ELSE",
            "ENUM", "EXTENDS", "FALSE", "FINAL", "FINALLY",
            "FLOAT", "FOR", "GOTO", "IF", "IMPLEMENTS",
            "IMPORT", "INSTANCEOF", "INT", "INTERFACE",
            "LONG", "NATIVE", "NEW", "NULL", "PACKAGE",
            "PRIVATE", "PROTECTED", "PUBLIC", "RETURN",
            "SHORT", "STATIC", "STRICTFP", "SUPER", "SWITCH",
            "SYNCHRONIZED", "THIS", "THROW", "THROWS",
            "TRANSIENT", "TRUE", "TRY", "VOID", "VOLATILE", "WHILE"
    };
    private static int attempts = 0;
    private static char guess;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String word = rndWord();

        char[] placeHolders = new char[word.length()];
        for (int i = 0; i < placeHolders.length; i++) {
            placeHolders[i] = '_';
        }
        char[] misses = new char[6];
        Arrays.fill(misses, '_');

        System.out.println("Hey there. Let's play Hangman!");
        System.out.println(word);

        while (attempts < 6) {
        printPlaceholder(placeHolders);
        printMisses(misses);
        System.out.print("\nGuess: ");
        guess = scanner.next().charAt(0);
        while (!checkDouble(misses, guess)) {
            System.out.println("You've already used this letter. Please pick a different one.");
            guess = scanner.next().charAt(0);
        }
        printAttempts(attempts);

        if (checkGuess(word, guess)) {
            updatePlaceholder(word, guess, placeHolders);
        } else {
            misses[attempts] = guess;
            attempts++;
        }

        if (Arrays.equals(placeHolders, word.toCharArray())) {
            printPlaceholder(placeHolders);
            System.out.println("\nGood Work!");
            break;
            }
        }

        if (attempts == 6) {
            System.out.println("RIP! The answer was " + word);
            scanner.close();
        }
    }


    public static void printMisses(char[] misses){
        System.out.print("\nMisses: ");
        for (int i = 0; i < misses.length; i++) {
            System.out.print(misses[i]);
        }
    }

    public static void updatePlaceholder(String word, char guess, char[] placerHolders) {
        for (int i = 0; i < placerHolders.length; i++) {
            if (guess == word.charAt(i)) {
                placerHolders[i] = guess;
            }
        }
    }

    public static void printPlaceholder(char[] placeHolders){
        System.out.print("Word: ");
        for (int i = 0; i < placeHolders.length; i++) {
            System.out.print(" " + placeHolders[i]);
        }
    }

    public static String rndWord() {
        Random rnd = new Random();
        int r = rnd.nextInt(words.length);
        return words[r];
    }

    public static void printAttempts(int attempts) {
        System.out.println("Attempts left: " + attempts + "\n");
    }

    public static boolean checkGuess(String word, char guess) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkDouble(char[] misses, char guess){
        for (int i = 0; i < misses.length; i++) {
            if (misses[i] == guess) {
                return false;
            }
        }
        return true;
    }

}