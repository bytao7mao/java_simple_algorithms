package com.company;

public class CaesarChyper {
    static String caesarChiper(String arg){
        String result = "";
        for (int i=0; i<arg.length(); i++){
//            if (arg.charAt(i) == 'z' || arg.charAt(i) == 'Z')
//                result+=(char)(arg.charAt(i)-(25));
//            else
//                result+=(char)(arg.charAt(i)+(1));

            result+=(arg.charAt(i)=='z'||arg.charAt(i)=='Z')?
                            (char)(arg.charAt(i)-(25)):
                            (char)(arg.charAt(i)+(1));

        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(caesarChiper("abc"));
    }
}
