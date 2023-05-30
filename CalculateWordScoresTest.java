import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class CalculateWordScoresTest {
    
    @Test
    public void testSentencesNull() {

        Set<Sentence> sentences = null;
        Map<String, Double> result = new HashMap<>();
        assertEquals(result, Analyzer.calculateWordScores(sentences));
    }

    @Test
    public void testSentencesEmpty() {

        Set<Sentence> sentences = new HashSet<Sentence>();
        Map<String, Double> result = new HashMap<>();
        assertEquals(result, Analyzer.calculateWordScores(sentences));
    }

    @Test
    public void testTwoSentences() {

        Set<Sentence> sentences = new HashSet<Sentence>();
        Sentence sentenceOne = new Sentence(2, "I like cake and could eat cake all day .");
        Sentence sentenceTwo = new Sentence(1, "I hope the dog does not eat my cake .");
        sentences.add(sentenceOne);
        sentences.add(sentenceTwo);

        Map<String, Double> result = new HashMap<>();
        result.put("dog", 1.0);
        result.put("eat", 1.5);
        result.put("cake", 1.6667);

        assertEquals(result.get("dog"), Analyzer.calculateWordScores(sentences).get("dog"), 0.0001);
        assertEquals(result.get("eat"), Analyzer.calculateWordScores(sentences).get("eat"), 0.0001);
        assertEquals(result.get("cake"), Analyzer.calculateWordScores(sentences).get("cake"), 0.0001);
        assertEquals(result.get("cat"), Analyzer.calculateWordScores(sentences).get("cat"));
        assertEquals(14, Analyzer.calculateWordScores(sentences).size());
    }

    @Test
    public void testInvalidInput() {

        Set<Sentence> sentences = new HashSet<Sentence>();
        Sentence sentenceOne = new Sentence(3, "I like cake and could eat cake all day .");
        Sentence sentenceTwo = new Sentence(1, "I hope the dog does not eat my cake .");
        sentences.add(sentenceOne);
        sentences.add(sentenceTwo);

        Map<String, Double> result = new HashMap<>();
        result.put("dog", 1.0);
        result.put("eat", 1.0);
        result.put("cake", 1.0);

        assertEquals(result.get("dog"), Analyzer.calculateWordScores(sentences).get("dog"), 0.0001);
        assertEquals(result.get("eat"), Analyzer.calculateWordScores(sentences).get("eat"), 0.0001);
        assertEquals(result.get("cake"), Analyzer.calculateWordScores(sentences).get("cake"), 0.0001);
        assertEquals(result.get("cat"), Analyzer.calculateWordScores(sentences).get("cat"));
        assertEquals(9, Analyzer.calculateWordScores(sentences).size());
    }

    @Test
    public void testUpperCase() {

        Set<Sentence> sentences = new HashSet<Sentence>();
        Sentence sentenceOne = new Sentence(2, "I like Cake and could eat cake all day .");
        Sentence sentenceTwo = new Sentence(1, "I hope the dOg does not eat my cakE .");
        sentences.add(sentenceOne);
        sentences.add(sentenceTwo);

        Map<String, Double> result = new HashMap<>();
        result.put("dog", 1.0);
        result.put("eat", 1.5);
        result.put("cake", 1.6667);

        assertEquals(result.get("dog"), Analyzer.calculateWordScores(sentences).get("dog"), 0.0001);
        assertEquals(result.get("eat"), Analyzer.calculateWordScores(sentences).get("eat"), 0.0001);
        assertEquals(result.get("cake"), Analyzer.calculateWordScores(sentences).get("cake"), 0.0001);
        assertEquals(result.get("cat"), Analyzer.calculateWordScores(sentences).get("cat"));
        assertEquals(14, Analyzer.calculateWordScores(sentences).size());
    }

    @Test
    public void testSpecialChars() {

        Set<Sentence> sentences = new HashSet<Sentence>();
        Sentence sentenceOne = new Sentence(2, "It 's a lot of fun to learn about data structures .");
        sentences.add(sentenceOne);

        Map<String, Double> result = new HashMap<>();
        result.put("it", 2.0);

        assertEquals(result.get("it"), Analyzer.calculateWordScores(sentences).get("it"), 0.0001);
        assertEquals(result.get("s"), Analyzer.calculateWordScores(sentences).get("s"));
        assertEquals(result.get("."), Analyzer.calculateWordScores(sentences).get("."));
        assertEquals(10, Analyzer.calculateWordScores(sentences).size());
    }
}
