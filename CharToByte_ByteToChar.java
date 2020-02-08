import java.util.ArrayList;
import java.util.Arrays;

public class CharToByte_ByteToChar{    
        
static void charToByte_ASCII(String str) {
        char[] buffer = str.toCharArray();
        byte[] b = new byte[buffer.length];
        for (int i = 0; i < b.length; i++) {
            b[i] = (byte) buffer[i];
        }
        String result = Arrays.toString(b);
        System.out.println(result);
    }
    static void byteToChar_ASCII(int[] ints){
        char[] chars = new char[ints.length];
        for (int i=0;i<ints.length;i++){
            chars[i] = (char)ints[i];
        }
        System.out.println(Arrays.toString(chars));
    }
        
        static void charToByte2(char[] chars){
        byte[] bytes = new byte[chars.length];
        for (int i =0;i<bytes.length;i++){
            bytes[i]=(byte) chars[i];
        }
        System.out.println(Arrays.toString(bytes));
    }

public static void main(String[] args) {
        String letters = "ABC";
        int[] ascii_chars = {65,66,67};
        ASCIItoChar(ascii_chars);
        stringToBytesASCII(letters);
 }
}

//output
//[A, B, C]
//[65, 66, 67]
