import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Ngrams predictive probabilities for text
 */
public class Ngrams {
	/**
	 * The constructor would be a good place to compute and store the Ngrams probabilities.
	 * Take uncompressed input as a text string, and store a List of Maps. The n-th such
     * Map has keys that are prefixes of length n. Each value is itself a Map, from 
     * characters to floats (this is the probability of the char, given the prefix).
	 */
    List<Map<String, Map<Character,Float>>> ngram;  /* nb. suggestion only - you don't have to use
                                                     this particular data structure */

	public void Ngrams(String input) {
		// TODO fill this in.
		ngram = new ArrayList<>();
		for (int n = 0; n < 6; n++) {
			Map<String, Map<Character, Float>> m = map(input, n);
			ngram.add(m);
		}
	}
	public Map<String, Map<Character, Float>> map(String input, int n) {
		Map<String, Map<Character, Float>> newMap = new HashMap<>();
		for (int i = 0; i < (input.length() - n); i++) {
			String prefix = input.substring(i, i + n);
			Character nextChar = input.charAt(i + n);
			if (newMap.containsKey(prefix)) {
				if (newMap.get(prefix).containsKey(nextChar)) {
					float count = newMap.get(prefix).get(nextChar);
					newMap.get(prefix).put(nextChar, count + 1);
				}
				else { newMap.get(prefix).put(nextChar, 1.0f); }
			}
			else {
				newMap.put(prefix, new HashMap<>());
				newMap.get(prefix).put(nextChar, 1.0f);
			}
		}
		for (Map<Character, Float> floatMap : newMap.values()) {
			float total = 0;
			for (float f : floatMap.values()) {
				total += f;
			}
			for (Character c : floatMap.keySet()) {
				float f = floatMap.get(c);
				floatMap.put(c, f/total);
			}
		}
		return newMap;
	}

	/**
	 * Take a string, and look up the probability of each character in it, under the Ngrams model.
     * Returns a List of Floats (which are the probabilities).
	 */
	public List <Float> findCharProbs(String mystring) {
		// TODO fill this in.
		List<Float> floatList = new ArrayList<>();
		int gram = 5;
		int i = 0;
		boolean bool = false;

		for (int j = - 5; j < mystring.length() - gram; j++) {
			String prefix = mystring.substring(Math.max(0, j), j + gram);
			if(prefix.equals("a tak")){bool = true;}

			if (ngram.get(gram).containsKey(prefix)) {
				char c = mystring.charAt(j + gram);
				if (ngram.get(gram).get(prefix).containsKey(c)) {
					float probability = ngram.get(gram).get(prefix).get(c);
					if(bool && c=='a'){
						System.out.println("n: " + gram);
						System.out.println("probability of 'a': " + ngram.get(gram).get(prefix).get(c));
						bool = false;
					}
					floatList.add(probability);
					j -= i;
					i = 0;
					gram = 5;
				}
				else {
					i++;
					gram--;
				}
			}
			else {
				i++;
				gram--;
			}
		}
		return floatList;
	}

	/**
	 * Take a list of probabilites (floats), and return the sum of the logs (base 2) in the list.
	 */
	public float calcTotalLogProb(List<Float> charProbs) {
		// TODO fill this in.
		float result = 0;
		for (float p: charProbs) {
			result += (Math.log10(p) / Math.log10(2));
		}
		return result;
	}
}
