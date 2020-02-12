static String[] allLongestStrings(String[] inputArray) {
        ArrayList<String> finalResult = new ArrayList<>();
        //find longest string from array
        int maxLength = 0;
        for (String s : inputArray) {
            if (s.length() > maxLength) {
                maxLength = s.length();
            }
        }
        //compare length strings from the array between them
        for (int i = 0; i < inputArray.length; i++){
            if (inputArray[i].length() >= maxLength){
                finalResult.add(inputArray[i]);
            }
        }
        //move all (or the only) value from arraylist into a simple array
        String[] finalResult2 = new String[finalResult.size()];
        for (int i = 0; i < finalResult.size(); i++){
            finalResult2[i] = finalResult.get(i);
        }
        return finalResult2;
    }
