import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class test {

	public static void main(String[] args) {
		try{
			FileReader fr = new FileReader("test.txt");
			Scanner sc = new Scanner(fr);
			System.out.println("k");
			while(sc.hasNextLine()){
				String[] words = new String[1];
				words = sc.nextLine().split(";");
				System.out.println(words[0]);
				System.out.println(words[1]);
				System.out.println("kk");

			}
					
			sc.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}

	}

}
