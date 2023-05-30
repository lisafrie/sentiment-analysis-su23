import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class CalculateSentenceScoreTest {
    
    @Test
    public void testNoSentence() {

        String input = "";
        Map<String, Double> wordScores = new HashMap<String, Double>();
        wordScores.put("dogs", 2.0);
        assertEquals(0.0, Analyzer.calculateSentenceScore(wordScores, input), 0.0001);
        
    }

    @Test
    public void testEmptyWordScore() {

        String input = "dogs are cute";
        Map<String, Double> wordScores = new HashMap<String, Double>();
        assertEquals(0.0, Analyzer.calculateSentenceScore(wordScores, input), 0.0001);
        
    }

    @Test
    public void testThreeWords() {

        String input = "dogs are cute";
        Map<String, Double> wordScores = new HashMap<String, Double>();
        wordScores.put("dogs", 2.0);
        wordScores.put("are", 0.0);
        wordScores.put("cute", 1.0);
        assertEquals(1.0, Analyzer.calculateSentenceScore(wordScores, input), 0.0001);

        String inputTwo = "dogs aRe Cute";
        assertEquals(1.0, Analyzer.calculateSentenceScore(wordScores, inputTwo), 0.0001);
        
    }

    @Test
    public void testDoubleOccurence() {

        String input = "dogs are cute dogs";
        Map<String, Double> wordScores = new HashMap<String, Double>();
        wordScores.put("dogs", 2.0);
        wordScores.put("are", 0.0);
        wordScores.put("cute", 1.0);
        assertEquals(1.25, Analyzer.calculateSentenceScore(wordScores, input), 0.0001);
        
    }

    @Test
    public void testSpecialChars() {

        String input = "dogs are ?smart";
        Map<String, Double> wordScores = new HashMap<String, Double>();
        wordScores.put("dogs", 2.0);
        wordScores.put("are", 0.0);
        wordScores.put("cute", 1.0);
        wordScores.put("smart", 1.0);
        assertEquals(1.0, Analyzer.calculateSentenceScore(wordScores, input), 0.0001);
        
    }

    @Test
    public void testWordNotInMap() {

        String input = "dogs are funny";
        Map<String, Double> wordScores = new HashMap<String, Double>();
        wordScores.put("dogs", 2.0);
        wordScores.put("are", 0.0);
        wordScores.put("cute", 1.0);
        assertEquals(0.666667, Analyzer.calculateSentenceScore(wordScores, input), 0.0001);
        
    }
}
