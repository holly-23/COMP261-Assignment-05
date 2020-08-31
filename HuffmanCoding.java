import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * A new instance of HuffmanCoding is created for every run. The constructor is
 * passed the full text to be encoded or decoded, so this is a good place to
 * construct the tree. You should store this tree in a field and then use it in
 * the encode and decode methods.
 */
public class HuffmanCoding {
	HuffmanNode root;
	Map<Character, Integer> frequencyTable = new HashMap<>();
	Map<Character,String> map = new HashMap<>();
	/**
	 * This would be a good place to compute and store the tree.
	 */
	public HuffmanCoding(String text) {
		// TODO fill this in.

		for (int i = 0; i < text.length(); i++) {
			Character c = text.charAt(i);
			if (frequencyTable.containsKey(c)) {
				frequencyTable.put(c, frequencyTable.get(c) + 1);
			}
			else {
				frequencyTable.put(c, 1);
			}
		}
		Queue<HuffmanNode> nodeQueue = new PriorityQueue<>();
		for (char c : frequencyTable.keySet()) {
			nodeQueue.offer(new HuffmanNode(c, frequencyTable.get(c), null, null));
		}
		while (nodeQueue.size() > 1) {
			HuffmanNode node1 = nodeQueue.poll();
			HuffmanNode node2 = nodeQueue.poll();
			nodeQueue.offer(new HuffmanNode('\0', node1.getFrequency() + node2.getFrequency(), node1, node2));
		}
		root = nodeQueue.poll();
	}
	private void treeSearch(HuffmanNode node, String string){
		if(node.getNode1()==null||node.getNode2()==null){
			map.put(node.getC(),string);
		}
		else{
			treeSearch(node.getNode1(),string+"0");
			treeSearch(node.getNode2(),string+"1");
		}
	}

	/**
	 * Take an input string, text, and encode it with the stored tree. Should
	 * return the encoded text as a binary string, that is, a string containing
	 * only 1 and 0.
	 */
	public String encode(String text) {
		// TODO fill this in.
		treeSearch(root,"");

		char[] textArr = text.toCharArray();

		StringBuilder transLated = new StringBuilder();
		for (char c : textArr) { transLated.append(map.get(c)); }
		return transLated.toString();
	}

	/**
	 * Take encoded input as a binary string, decode it using the stored tree,
	 * and return the decoded text as a text string.
	 */
	public String decode(String encoded) {
		// TODO fill this in.
		char[] encodedArray = encoded.toCharArray();
		int i = 0;

		StringBuilder out = new StringBuilder();
		while (i < encoded.length()) {
			HuffmanNode node = root;
			while (!node.isChild()) {
				char c = encodedArray[i];

				if (c == '1') { node = node.getNode2(); }
				else { node = node.getNode1(); }
				i++;
			}

			out.append(node.getC());

		}
		return out.toString();
	}

	/**
	 * The getInformation method is here for your convenience, you don't need to
	 * fill it in if you don't wan to. It is called on every run and its return
	 * value is displayed on-screen. You could use this, for example, to print
	 * out the encoding tree.
	 */
	public String getInformation() {
		print(root,"");
		return "";
	}

	public void print(HuffmanNode huff, String indent){
		if(huff!=null){
			System.out.println(indent + ":" + huff.getC());
			print(huff.getNode1(), indent + "		");
			print(huff.getNode2(), indent + "  	");
		}
	}
}


