public class PhraseCount {
    public static void main(String[] args) {
        String phrase[] = {
                "A STITCH IN TIME SAVES NINE",
                "DON'T EAT YELLOW SNOW",
                "TASTE THE RAINBOW",
                "EVERY GOOD BOY DOES FINE",
                "I LIKE IKE",
                "PLAY IT AGAIN, SAM",
        };

        int[] letterCount = new int[26];
        for (int count = 0; count < phrase.length; count++) {
            String current = phrase[count];
            char[] letters = current.toCharArray();
            for (int count2 = 0;  count2 < letters.length; count2++) {
                char lett = letters[count2];
                if ( (lett >= 'A') && (lett <= 'Z') ) {
                    letterCount[lett - 'A']++;
                }
            }
        }
        for (char count = 'A'; count <= 'Z'; count++) {
            System.out.print(count + ": " + letterCount[count - 'A'] + " ");
            if (count == 'M') {
                System.out.print("\n");
            }
        }
        System.out.println();

    }
}
