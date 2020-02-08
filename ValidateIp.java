package CodeFights;

public class ValidateIp {
    public static void main(String[] args) {
        boolean ok = isIpv4Adress("255.1.222.1");
        System.out.println(ok);
    }

    static boolean isIpv4Adress(String string){
        boolean condition=false; //creating boolean for our method
        int fourIp=4; //creating an integer and set to 4 (4 ip numbers)
        try { //try for nullpointerException
        String[] arrayStr = string.split("\\."); //split the string into 4 arrays
        for (String s:arrayStr) {
            //parsing the String into an integer
            int parsedString = Integer.parseInt(s);
            //checking if the String is empty
            if (s.isEmpty())
                condition = false;
            try {
                //checking if its a number and if its lower than 255 or equal
                if (isNumeric(s) && (parsedString <= 255)) {
                    //if yes then counter will substract 1 from it
                    fourIp--;
                }
            } catch (NumberFormatException e) {
                condition = false;
            }
         }
        } catch (NullPointerException e){
            condition=false;System.out.println("null pointer");
        }
        //checking the ip to have 4 parts
        if (fourIp==0)
            condition=true;

        return condition;
    }
    static boolean isNumeric(String string){
        boolean isChar = true;
        //checking each character
        for (Character c:string.toCharArray()){
            //if any of chars is not a number then condition is false
            if (!Character.isDigit(c))
                isChar = false;
        }
        return isChar;
    }
}
