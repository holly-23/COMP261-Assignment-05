public class HuffmanNode implements Comparable<HuffmanNode>{
    private char c;
    private int frequency;
    private HuffmanNode node1;
    private HuffmanNode node2;

    public HuffmanNode(char c, int frequency, HuffmanNode node1, HuffmanNode node2) {
        this.c = c;
        this.frequency = frequency;
        this.node1 = node1;
        this.node2 = node2;
    }


    public char getC() {
        return c;
    }

    public int getFrequency() {
        return frequency;
    }

    public HuffmanNode getNode1() {
        return node1;
    }

    public HuffmanNode getNode2() {
        return node2;
    }

    public boolean isChild() {
        if(node1 == null && node2 == null) { return true; }
        return false;
    }

    @Override
    public int compareTo(HuffmanNode other) {
        if (this.getFrequency() < other.getFrequency()) { return -1; }
        if (this.getFrequency() > other.getFrequency()) { return 1; }
        return 0;
    }
}

