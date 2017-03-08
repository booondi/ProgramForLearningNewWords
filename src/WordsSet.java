/*
 * Aplikacja napisana w ramach szkolenia. www.km-programs.pl
 */

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class WordsSet {
	private Word[] word;

	public Word[] getWord() {
		return word;
	}

	public void setWord(Word[] word) {
		this.word = word;
	}

	public WordsSet(int arrayLength, String nameFile) {

		Word[] array = new Word[arrayLength];

		for (int i = 0; i < arrayLength; i++) {
			array[i] = new Word(nameFile);
		}
		setWord(array);
	}
	

	public WordsSet(int arrayLength) {

		Word[] array = new Word[arrayLength];

		for (int i = 0; i < arrayLength; i++) {
			array[i] = new Word();
		}
		setWord(array);
	}

	public void learning(WordsSet set) {
		boolean endOfLearning = false;

		do {
			System.out.println("What do you want to do? Press 1 for more practise, press 0 for end of program.");
			int choice = Main.sc.nextInt();

			switch (choice) {
			case 1:
				int randomSet = Main.rnd.nextInt(getWord().length);
				int translate = Main.rnd.nextInt(2);

				if (translate == 0) { // tlumaczymy z pol na ang
					System.out.println("What is translate of " + word[randomSet].getWordInPolish() + "?");
					String t = Main.sc.nextLine();
					t = Main.sc.nextLine();
					int count1 = 0;
					if (t.toUpperCase().equals(word[randomSet].getWordInEnglish())) {
						word[randomSet].setHowManyTimesAsked(word[randomSet].getHowManyTimesAsked() + 1);
						word[randomSet]
								.setHowManyTimesCorrectAnswer(word[randomSet].getHowManyTimesCorrectAnswer() + 1);
						System.out.println("Good answer!");
					} else {
						while (count1 != 2) {
							word[randomSet].setHowManyTimesAsked(word[randomSet].getHowManyTimesAsked() + 1);
							System.out.println(
									"Wrong answer. You still have " + (2 - count1) + " chance to good answer.");
							count1++;
							System.out.println("What is translate of " + word[randomSet].getWordInPolish() + "?");
							t = Main.sc.nextLine();
							if (t.toUpperCase().equals(word[randomSet].getWordInEnglish())) {
								word[randomSet].setHowManyTimesAsked(word[randomSet].getHowManyTimesAsked() + 1);
								word[randomSet].setHowManyTimesCorrectAnswer(
										word[randomSet].getHowManyTimesCorrectAnswer() + 1);
								System.out.println("Good answer!");
								count1 = 2;
							}
						}
					}

				} else if (translate == 1) {
					System.out.println("What is translate of " + word[randomSet].getWordInEnglish() + "?");
					String t = Main.sc.nextLine();
					t = Main.sc.nextLine();
					int count2 = 0;
					if (t.toUpperCase().equals(word[randomSet].getWordInPolish())) {
						word[randomSet].setHowManyTimesAsked(word[randomSet].getHowManyTimesAsked() + 1);
						word[randomSet]
								.setHowManyTimesCorrectAnswer(word[randomSet].getHowManyTimesCorrectAnswer() + 1);
						count2 = 2;
						System.out.println("Good answer!");
					} else {
						while (count2 != 2) {
							word[randomSet].setHowManyTimesAsked(word[randomSet].getHowManyTimesAsked() + 1);
							System.out.println(
									"Wrong answer. You still have " + (2 - count2) + " chance to good answer.");
							System.out.println("What is translate of " + word[randomSet].getWordInEnglish() + "?");
							t = Main.sc.nextLine();
							count2++;
							if (t.toUpperCase().equals(word[randomSet].getWordInPolish())) {
								word[randomSet].setHowManyTimesAsked(word[randomSet].getHowManyTimesAsked() + 1);
								word[randomSet].setHowManyTimesCorrectAnswer(
										word[randomSet].getHowManyTimesCorrectAnswer() + 1);
								System.out.println("Good answer!");
								count2 = 2;
							}
						}
					}
				}
				break;
			case 0:
				endOfLearning = true;
				System.out.println("End of learning. Your statistics:");
				System.out.println(toString());
				double wsp = 0;
				double highest = 0;
				int idx = 0;
				double[] arrayRatio = new double[word.length];
				for (int i = 0; i < word.length; i++) {
					wsp = word[i].getHowManyTimesCorrectAnswer() / word[i].getHowManyTimesAsked();

					if (wsp > highest) {
						highest = wsp;
						idx = i;
					}
					if (Double.isNaN(wsp)) {
						arrayRatio[i] = 0;
					} else {
						arrayRatio[i] = wsp;
					}
				}

				double ratioSum = 0;
				for (int i = 0; i < arrayRatio.length; i++) {
					ratioSum += arrayRatio[i];
				}
				System.out.println("Word, that you have learned the best: " + word[idx]);

				double learnRatio = ratioSum / arrayRatio.length;
				System.out.println("Your learn ratio: " + learnRatio);
				if (learnRatio <= 0.5) {
					System.out.println("Your learn level: BAD");
				} else if (learnRatio > 0.5 && learnRatio <= 0.75) {
					System.out.println("Your learn level: MEDIUM");
				} else if (learnRatio > 0.75) {
					System.out.println("Your learn level: GOOD");
				}

				try {
					FileWriter fw = new FileWriter("statistics.txt", true);
					PrintWriter pw = new PrintWriter(fw);

					pw.println("Word, that you have learned the best: " + word[idx]);
					pw.println("Your learn ratio: " + learnRatio);
					if (learnRatio <= 0.5) {
						pw.println("Your learn level: BAD");
					} else if (learnRatio > 0.5 && learnRatio <= 0.75) {
						pw.println("Your learn level: MEDIUM");
					} else if (learnRatio > 0.75) {
						pw.println("Your learn level: GOOD");
					}
					pw.println("\n");
					pw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		} while (!endOfLearning);
	}

	@Override
	public String toString() {
		return "WordsSet [word=" + Arrays.toString(word) + "]";
	}

}
