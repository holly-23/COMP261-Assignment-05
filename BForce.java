public class BForce {
    public int search(String pattern, String text) {
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
            return i;
        }
        return -1;
    }
}
