public class Tuple {
    int offset;
    int length;
    char character;
    int index;

    public Tuple(int off, int len, char ch,int i) {
        offset = off;
        length = len;
        character = ch;
        index = i;
    }

    public String toString() {
        return "[" + offset +"|"+ length +"|"+ character + "]";
    }

    public int getOffset() {
        return offset;
    }

    public int getLength() {
        return length;
    }

    public char getCharacter() {
        return character;
    }
}

