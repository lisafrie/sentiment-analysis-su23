import java.util.*;

/*
 * This class represents a single sentence in the input file.
 * You may add methods to this class but please do not modify 
 * or delete the instance variables, constructor, or getter methods.
 */

public class Sentence {
	
	private int score;
	private String text;
	
	public Sentence(int score, String text) {
		this.score = score;
		this.text = text;
	}
	
	public int getScore() {
		return score;
	}
	
	public String getText() {
		return text;
	}

	// Override hashCode() and equals() methods for proper Set behavior
    // @Override
    // public int hashCode() {
    //     final int prime = 31;
    //     int result = 1;
    //     result = prime * result + score;
    //     result = prime * result + ((text == null) ? 0 : text.hashCode());
    //     return result;
    // }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Sentence other = (Sentence) obj;
        return score == other.score && text.equals(other.text);
    }
	
}
