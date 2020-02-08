//counter SAME chars
public static int countChar(String str, char ch){
        int count=0;
        while (str.indexOf(ch)!=-1){
            count++;
            str=str.substring((str.indexOf(ch)+1));
        }
        return count;
    }
    public static void lineEncoding(String str){
       str = str.replace(" ", "");
       while (str.length() > 0){
           char ch = str.charAt(0);
           int count = countChar(str, ch);
           System.out.println(ch + " " + count);
           str = str.replace(""+ch,"");
       }
    }
//counter EACH char
static String lineEncoding(String string) {
        //adding non-char character to make the length of the string + 1
        string += "#";
        int counter = 1;
        StringBuilder result = new StringBuilder();
        //iterating from 1 to length of the string
        for (int i = 1; i < string.length(); i++) {
            //compare char from index 1 to index 0
            if (string.charAt(i) == string.charAt(i - 1)) {
                //if true add 1 to counter
                counter++;
                //after finishing with counter the same letter we add
                //the letter to stringbuilder like this: 3a
            } else {
                if (counter > 1) {
                    //append counter first
                    result.append(counter);
                }
                //append letter
                result.append(string.charAt(i-1));
                //reset counter
                counter=1;
            }
        }
        return result.toString();
    }
