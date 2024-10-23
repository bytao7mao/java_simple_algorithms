import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Original string: ");
        String originalStr = scanner.nextLine();
        scanner.close();
        //split the string into (an array) by a whitespace
//        String[] words=originalStr.split(" ");

        //split the string into (an array) by a regex whitespace
        //tiny parts of strings method 1 with pattern class
        Pattern pattern = Pattern.compile("\\s");
        String[] patternWords = pattern.split(originalStr);
        StringBuilder finalReversedWords= new StringBuilder();
        for(int i = patternWords.length-1; i >= 0; i--){
            String tempString = patternWords[i];
//            char[] chars = tempString.toCharArray();
//            String reversedString = "";
//            for (int j=tempString.length()-1; j>=0; j--) {
//                reversedString = reversedString+chars[j];
//            }
//            System.out.println("reversedString: " + reversedString);
            finalReversedWords.append(tempString).append(" ");
        }
        System.out.print("Reversed string: " + finalReversedWords);
    }
}
