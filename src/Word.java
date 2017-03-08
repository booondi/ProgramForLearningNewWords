import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class Word {

	private String wordInPolish;
	private String wordInEnglish;
	private double howManyTimesAsked;
	private double howManyTimesCorrectAnswer;
	private static int  whichPair = 0;

	public String getWordInPolish() {
		return wordInPolish;
	}

	public void setWordInPolish(String wordInPolish) {
		int c = 0;
		for (int i = 0; i < wordInPolish.length(); i++) {
			if ((int) wordInPolish.charAt(i) < 65 || (int) wordInPolish.charAt(i) > 90) {
				c++;
			}
		}
		while (c > 0) {
			System.out.println("Word " + wordInPolish + " can only contains upper letters. Try again:");
			wordInPolish = Main.sc.nextLine();
			c = 0;
			for (int i = 0; i < wordInPolish.length(); i++) {
				if ((int) wordInPolish.charAt(i) < 65 || (int) wordInPolish.charAt(i) > 90) {
					c++;
				}
			}
		}

		this.wordInPolish = wordInPolish;
	}

	public String getWordInEnglish() {
		return wordInEnglish;
	}

	public void setWordInEnglish(String wordInEnglish) {
		int c = 0;
		for (int i = 0; i < wordInEnglish.length(); i++) {
			if ((int) wordInEnglish.charAt(i) < 65 || (int) wordInEnglish.charAt(i) > 90) {
				c++;
			}
		}
		while (c > 0) {
			System.out.println("Word " + wordInEnglish + " can only contains upper letters. Try again:");
			wordInEnglish = Main.sc.nextLine();
			c = 0;
			for (int i = 0; i < wordInEnglish.length(); i++) {
				if ((int) wordInEnglish.charAt(i) < 65 || (int) wordInEnglish.charAt(i) > 90) {
					c++;
				}
			}
		}
		this.wordInEnglish = wordInEnglish;
	}

	public double getHowManyTimesAsked() {
		return howManyTimesAsked;
	}

	public void setHowManyTimesAsked(double howManyTimesAsked) {
		this.howManyTimesAsked = howManyTimesAsked;
	}

	public double getHowManyTimesCorrectAnswer() {
		return howManyTimesCorrectAnswer;
	}

	public void setHowManyTimesCorrectAnswer(double howManyTimesCorrectAnswer) {
		this.howManyTimesCorrectAnswer = howManyTimesCorrectAnswer;
	}

	public Word() {
		System.out.println("Enter word in Polish:");
		String polish = Main.sc.nextLine();

		System.out.println("Enter word in English:");
		String english = Main.sc.nextLine();

		setWordInPolish(polish);
		setWordInEnglish(english);
		setHowManyTimesAsked(0);
		setHowManyTimesCorrectAnswer(0);
	}

	public int sizeofArrayFile(String fileName) {
		int count = 0;
		try {
			FileReader fr = new FileReader(fileName);
			Scanner sc = new Scanner(fr);

			String[] words = null;

			int c = 0;
			while (sc.hasNextLine()) {
				words = sc.nextLine().split(";");
				c = 0;
				for (int i = 0; i < words[0].length(); i++) {
					if ((int) words[0].charAt(i) < 65 || (int) words[0].charAt(i) > 90) {
						c++;
					}
				}
				for (int i = 0; i < words[1].length(); i++) {
					if ((int) words[1].charAt(i) < 65 || (int) words[1].charAt(i) > 90) {
						c++;
					}
				}

				if (c == 0) {
					count++;
				}
			}

			sc.close();
			return count;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public Word(String fileName) {
		try {
			FileReader fr = new FileReader(fileName);
			Scanner sc = new Scanner(fr);

			String[] words = null;
			int j = 0;

			int c = 0;
			while (sc.hasNextLine()) {
				words = sc.nextLine().split(";");
				//wyrazenie regularne!!!!!
				c = 0;
				for (int i = 0; i < words[0].length(); i++) {
					if ((int) words[0].charAt(i) < 65 || (int) words[0].charAt(i) > 90) {
						c++;
					}
				}
				for (int i = 0; i < words[1].length(); i++) {
					if ((int) words[1].charAt(i) < 65 || (int) words[1].charAt(i) > 90) {
						c++;
					}
				}

				if (c == 0)
				{
					++j;
				}
				if (j == whichPair) {
					setWordInPolish(words[0]);
					setWordInEnglish(words[1]);
					setHowManyTimesAsked(0);
					setHowManyTimesCorrectAnswer(0);
					break;
				}
				
				
			}
			++whichPair;
			//System.out.println(Arrays.toString(words));
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Word [wordInPolish=" + wordInPolish + ", wordInEnglish=" + wordInEnglish + ", howManyTimesAsked="
				+ howManyTimesAsked + ", howManyTimesCorrectAnswer=" + howManyTimesCorrectAnswer + "]";
	}

	public double learnRatio() {
		return howManyTimesCorrectAnswer / howManyTimesAsked;
	}

}
