import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/*
 * Implement the four methods as described in the specification.
 * 
 * Do not change the method signatures!
 * Contact the instructor if you feel that a change is necessary.
 */

public class Analyzer {

	public Analyzer() {
		
	}
	
	public static Set<Sentence> readFile(String filename) throws IllegalArgumentException, IOException {
		// implement this method in Part 1

		if (filename == null) {
			throw new IllegalArgumentException();
		}

		Set<Sentence> result = new HashSet<Sentence>();
		
		BufferedReader br = new BufferedReader(new FileReader(filename));

		String strLine;

		//Read File Line By Line
		while ((strLine = br.readLine()) != null)   {
			Sentence sentence = createSentence(strLine);
			if (sentence != null) {
				result.add(sentence);
			}
		}
		br.close();	

		return result;
	}

	private static Sentence createSentence(String line) {
		String[] parts = line.trim().split(" ", 2);
		if (parts.length != 2) {
			return null;
		}

		try {
			int score = Integer.parseInt(parts[0]);
			
			if (score >= -2 && score <= 2) {
				String text = parts[1];

				if (text.length() > 0) {
					return new Sentence(score, text);
				} else {
					return null;
				}
			} else {
				return null;
			}

		} catch (NumberFormatException e) {
			return null;
		 }

		
	}



	public static Map<String, Double> calculateWordScores(Set<Sentence> sentences) {
				
		// implement this method in Part 2
		if (sentences == null || sentences.isEmpty()) {
			return new HashMap<>();
		}


		Map<String, Integer> wordCounts = new HashMap<>();
		Map<String, Double> result = new HashMap<>();

		// If the input Set of Sentences is non-null and non-empty, then the method should ignore (and not consider for calculation) any Sentence objects in the Set for which necessary data is missing or invalid. 
		// It is up to you to determine what “missing or invalid” means! Refer back to the issues from discussions and assignments on defensive programming and the situations that you needed to handle.

		for (Sentence sentence : sentences) {
			StringTokenizer st = new StringTokenizer(sentence.getText());
			while (st.hasMoreTokens()) {
				String word = st.nextToken().toLowerCase();
				if (word.matches("^[a-z].*")) {
					wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
					result.put(word, result.getOrDefault(word, 0.0) + sentence.getScore());
				}
			}
		}

		for (Map.Entry<String, Double> entry : result.entrySet()) {
			String word = entry.getKey();
			int count = wordCounts.get(word);
			double scoreSum = entry.getValue();
			double score = scoreSum / count;
			result.put(word, score);
		}

		return result;
	}
	

	public static double calculateSentenceScore(Map<String, Double> wordScores, String sentence) {
				
		// implement this method in Part 3
		if (sentence.length() == 0 || wordScores.isEmpty() || wordScores == null) {
			return 0.0;
		}

		double score = 0.0;
		int count = 0;
		double scoreSum = 0.0;

		StringTokenizer st = new StringTokenizer(sentence);
		while (st.hasMoreTokens()) {
			String word = st.nextToken().toLowerCase();
			if (word.matches("^[a-z].*")) {
				count += 1;
				scoreSum += wordScores.getOrDefault(word, 0.0);
			}
		}

		score = scoreSum / count;
		
		return score;
	}
	
	public static void main(String[] args) {
		
		// implement this method in Part 4
		if (args.length == 0) {
			System.out.println("no input file");
			return;
		}

		String fileName = args[0];

		try {
			Set<Sentence> sentences = readFile(fileName);
			Map<String, Double> wordScores = calculateWordScores(sentences);
			Scanner scanner = new Scanner(System.in);

			while (true) {
				System.out.println("Enter your sentence here: ");
				String input = scanner.nextLine();

				if (input.contains("quit")) {
					break;
				}
				double score = calculateSentenceScore(wordScores, input);
				System.out.println("Your sentence's score is: " + score);
			}
			
			scanner.close();

		} catch (IllegalArgumentException e) {
			System.out.println("bad input file");
			return;
		} catch (IOException e) {
			System.out.println("bad input file");
			return;
		}
		 
	}
	
	// don't forget to write your JUnit tests for Part 5!

}
