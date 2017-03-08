/*
 * Aplikacja napisana w ramach szkolenia. www.km-programs.pl
 */

import java.util.Random;
import java.util.Scanner;

public class Main {

	public static Scanner sc = new Scanner(System.in);
	public static Random rnd = new Random();

	public static void main(String[] args) {

		System.out.println(
				"Let's start learning. Do you want load words from file (press 0) or load words from your keyboard (press 1)?");
		int choice = Integer.parseInt(sc.nextLine());
		while (choice != 0 && choice != 1) {
			System.out.println("There's no option that you chose. Try again.");
			System.out
					.println("Do you want load words from file (press 0) or load words from your keyboard (press 1)?");
			choice = Integer.parseInt(sc.nextLine());
		}
		if (choice == 0) {
			System.out.println("You've chosen loading words from file. What is name of your file?");
			String fileName = sc.nextLine();
			Word w = new Word(fileName);
			WordsSet ws1 = new WordsSet(w.sizeofArrayFile(fileName), fileName);
			ws1.learning(ws1);
		}
		if (choice == 1) {
			System.out.println("You've chosen loading words from keybord. How many words do you want to add?");
			int howManyWords = Integer.parseInt(sc.nextLine());
			WordsSet ws1 = new WordsSet(howManyWords);
			ws1.learning(ws1);
		}

	}

}
