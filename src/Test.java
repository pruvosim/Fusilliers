/**
 * 
 */
import java.io.*;



/**
 * @author verel
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Automata automate = new Automata(20);

		//String bestName = "/Users/verel/enseignement/12-13/EA/TP/code/top18.dat";
		String bestName = "C:/Users/Simon/Desktop/Fusilliers/Fichiers_annexes/code/solution_19a.dat";
		int [] rules = initRulesFromFile(bestName);

		int fit = automate.f(rules, 20);
		System.out.println(fit);

		String outName = "C:/Users/Simon/Desktop/Fusilliers/Fichiers_annexes/code/test_lol.dat";

		Initialization init = new Initialization();

		PrintWriter ecrivain;
		try {
			ecrivain =  new PrintWriter(new BufferedWriter(new FileWriter(outName)));

			for(int i = 0; i < 100000; i++) {
				init.init(rules);

				fit = automate.f(rules, 19);

				printToFile(fit, rules, ecrivain);
			}
			
			ecrivain.close();
		}
		catch (Exception e){
			System.out.println(e.toString());
		}
		
		System.out.println("The End.");
	}

	public static void printToFile(int fitness, int [] rules, PrintWriter ecrivain) {
		ecrivain.print(fitness);
		for(int i = 0; i < 216; i++) {
			ecrivain.print(" ");
			ecrivain.print(rules[i]);
		}
		ecrivain.println();
	}

	public static int [] initRulesFromFile(String fileName) {
		// 5 �tats + l'�tat "bord"
		int n = 5 + 1;

		int [] rules = new int[n * n * n];

		try {
			FileReader fichier = new FileReader(fileName);

			StreamTokenizer entree = new StreamTokenizer(fichier);

			int i = 0;
			while(entree.nextToken() == StreamTokenizer.TT_NUMBER)
			{		
				rules[i] = (int)entree.nval;
				i++;
			} 
			fichier.close();
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}

		return rules;
	}



}
