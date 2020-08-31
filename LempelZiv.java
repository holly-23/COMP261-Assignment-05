import java.util.ArrayList;
import java.util.List;

/**
 * A new instance of LempelZiv is created for every run.
 */
public class LempelZiv {
	/**
	 * Take uncompressed input as a text string, compress it, and return it as a
	 * text string.
	 */
	public String compress(String input) {
		// TODO fill this in.
		List<Tuple> tupleList = new ArrayList<>();

		int cursor = 0;
		int windowSize = 1000;
		int start = 0;

		while (cursor < input.length()) {
			int length = 0;
			int previousMatch = -1;
			boolean isExit = false;

			while (true) {
				int f = 0;
				int end = 0;
				BForce stringMatch = new BForce();
				if ((cursor - windowSize) >= 0) {
					start = cursor - windowSize; 
				}
				if (cursor >= 1) { 
					end = cursor -1; 
				}

				if ((cursor + length) >= input.length()-1) {
					f = input.length() - 1;
					if(previousMatch == - 1){ tupleList.add(new Tuple(0, length, input.charAt(cursor + length),cursor)); }
					else{ tupleList.add(new Tuple((cursor - (previousMatch +start)), length, input.charAt(cursor + length),cursor)); }
					cursor= cursor + 2;
					isExit = true;
					break;
				}

				else { 
					f = (cursor + length);  
				}

				String pattern = input.substring(cursor, f + 1);
				String text = input.substring(start, end + 1);
				int matching = stringMatch.search(pattern, text);

				if (matching != -1) {
					previousMatch = matching;
					length = length + 1;
				}
				else {
					if (previousMatch == -1){ tupleList.add(new Tuple(0, length, input.charAt(cursor + length),cursor)); }
					else{ tupleList.add(new Tuple((cursor - (previousMatch +start)), length, input.charAt(cursor + length),cursor)); }
					cursor = cursor + length +1;
					break;
				}
			}
			if (isExit){ 
				break; 
			}
		}

		StringBuilder builder = new StringBuilder();
		for (Tuple tuple : tupleList) {
			builder.append(tuple.toString());
		}
		return builder.toString();
	}


	/**
	 * Take compressed input as a text string, decompress it, and return it as a
	 * text string.
	 */
	public String decompress(String compressed) {
		// TODO fill this in.
		List<Tuple> list = new ArrayList<>();

		String[] tup = compressed.split("\\[");
		for (int i = 1; i < tup.length; i++) {
			String tuple = tup[i];
			String[] tupleSplit = tuple.split("\\|");
			int off = Integer.parseInt(tupleSplit[0]);
			int len = Integer.parseInt(tupleSplit[1]);
			char character = tupleSplit[2].charAt(0);
			list.add (new Tuple(off, len, character, 0));
		} 

		StringBuilder decompressed = new StringBuilder();
		int cursor = 0;
		for (Tuple tuple : list) {
			if ((tuple.getOffset() == 0) && (tuple.getLength() == 0)) {
				decompressed.append(tuple.getCharacter());
				cursor++;
			}
			else {
				String s  = decompressed.substring(cursor - tuple.getOffset(),(cursor - tuple.getOffset()) + tuple.getLength());
				decompressed.append(s);
				cursor = cursor +  tuple.getLength() +1 ;
				decompressed.append(tuple.getCharacter());
			}

		}
		return decompressed.toString();
	}

	/**
	 * The getInformation method is here for your convenience, you don't need to
	 * fill it in if you don't want to. It is called on every run and its return
	 * value is displayed on-screen. You can use this to print out any relevant
	 * information from your compression.
	 */
	public String getInformation() {
		return "";
	}
}
