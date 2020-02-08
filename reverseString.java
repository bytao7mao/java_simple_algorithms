static String reverse(String string){
        //method 1
        char[] stringAsByteArr = string.toCharArray(); //the initial string transformed into array of characters
        char[] result = new char[stringAsByteArr.length]; //the new array of characters in reverse order
        for (int i=0;i<stringAsByteArr.length;i++){
            //for each char in reverse order, put in the new char array
            result[i] = stringAsByteArr[stringAsByteArr.length-i-1];
        }
        return String.valueOf(result);
    }
    
    
    
    
    //method 2
    //void reverse2(){            
//        char[] try1 = string.toCharArray();
//        for (int i = try1.length-1; i>=0; i--)
//            System.out.print(try1[i]);
//     }
    
    //method3
//easy but i feel like cheating with this method :D
    // String testB = new StringBuilder("testing").reverse().toString();
    
   
