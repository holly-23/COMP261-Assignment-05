/**
 * A new KMP instance is created for every substring search performed. Both the
 * pattern and the text are passed to the constructor and the search method. You
 * could, for example, use the constructor to create the match table and the
 * search method to perform the search itself.
 */
public class KMP {
	int[] table;

	public KMP(String pattern, String text) {
		// TODO maybe fill this in.
		if (pattern.length() == 0) { return; }
		if (pattern.length() == 1) { return; }
		table = new int[pattern.length()];
		table[0] = -1;
		table[1] = 0;
		int i =0;
		int pos = 2;
		//char[] textArray = text.toCharArray();
		while(pos < pattern.length()) {
			if(pattern.charAt(pos - 1) == pattern.charAt(i)) {
				table[pos] = i++;
				pos++;
				i++;
			} else if(i > 0){
				i = table[i];
			} else {
				table[pos] = 0;
				pos++;
			}
		}

	}

	/**
	 * Perform KMP substring search on the given text with the given pattern.
	 * 
	 * This should return the starting index of the first substring match if it
	 * exists, or -1 if it doesn't.
	 */
	public int search(String pattern, String text) {
		// TODO fill this in.
		if (pattern.length() == 1) { return this.bruteSearch(pattern,text); }
		long s = System.currentTimeMillis();
		int k = 0;
		int i = 0;

		while (k + i < text.length()) {
			if (pattern.charAt(i) == text.charAt(k + i)) {
				i += 1;
				if (i == table.length) {
					long e = System.currentTimeMillis();
					System.out.printf("%.5fms  ", 1.0*(e-s));
					return k; 
				}
			}
			else if (table[i] == -1) {
				k = k + i + 1;
				i = 0;
			}
			else {
				k = k + i - table[i];
				i = table[i];
			}
		}
		long e= System.currentTimeMillis();
		System.out.printf("%.5fms  ", 1.0*(e-s));
		return -1;
	}
	public int bruteSearch(String pattern, String text) {
		// TODO fill this in.
		long s = System.currentTimeMillis();
		int patternLength = pattern.length();
		int finalIndex = text.length() - pattern.length();

		textLoop: for (int i = 0; i < finalIndex; i++) {
			searchLoop: for (int indexChar = 0; indexChar < patternLength; indexChar++) {
					char textC = text.charAt(i + indexChar);
					char patternC = pattern.charAt(indexChar);

					if (textC != patternC) {
						continue textLoop;
					}
			}
			long e= System.currentTimeMillis();
			System.out.printf("%.5fms  ", 1.0*(e-s));
			return i;
		}
		long e= System.currentTimeMillis();
		System.out.printf("%.5fms  ", 1.0*(e-s));
		return -1;
	}
}
