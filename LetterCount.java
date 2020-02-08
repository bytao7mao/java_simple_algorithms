public class Main {

    public static void main(String[] args) {
        String[] strings = {
                "A STITCH IN TIME SAVES NINE",
                "DON'T EAT YELLOW SNOW"
        };

        int[] letterCount = new int[26];
        for (int count=0; count<strings.length; count++){
            String current = strings[count];
            char[] letters = current.toCharArray();
            for (int count2=0;count2<letters.length;count2++){
                //find the letter while iterating through the strings array
                char lett = letters[count2];
                if (lett >= 'A' && lett <= 'Z'){
                    //for each index from letterCount array
                    //we add one to it when the letter occurs
                    //for example if letter is 'A' then we substract 'A'
                    //in order to add one to letter 'A' which will be index 0
                    //for 'B' we substract 'A' to go in index 1 and add in that index
                    letterCount[lett - 'A']++;
                }
            }
        }
        for (char count='A';count<='Z';count++){
            //for each index in the letterCount
            //we print the count of the letters
            //for example if letterCount[count] = 'A' (65) then we substract 'A' (65)
            //and result will be index 0 which is the letter 'A'
            //another example: if letterCount[count] = 'B' (66) the we substract 'A' (65)
            //and result will be index 1 which is letter 'B'
            System.out.print(count + ": " +
            letterCount[count - 'A'] + " ");
        }
        System.out.println();
    }
}
