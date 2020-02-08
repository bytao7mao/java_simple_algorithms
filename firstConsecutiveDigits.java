//returns consecutive numbers(digits) from a string
static String longestDigitsPrefix(String inputString) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<inputString.length();i++){
            char[] s = inputString.toCharArray();
            char[] c = new char[inputString.length()];
            c[i] = s[i];
            if (Character.isDigit(c[i])){
                stringBuilder.append(c[i]);
            }else {
                break;
            }
        }
        return stringBuilder.toString();
    }
