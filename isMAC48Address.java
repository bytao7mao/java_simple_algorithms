static boolean isMac48(String input){
        boolean condition = false;
        String regex = "[A-Fa-f0-9]+";
        Pattern pattern = Pattern.compile(regex);
        if (input.length()<18) {
            String[] toCheck = input.split("-");
            System.out.println(Arrays.toString(toCheck));
            for (int i = 0; i < toCheck.length; i++) {
//            if (!pattern.matcher(toCheck[i]).lookingAt()){
                for (Character c : toCheck[i].toCharArray()) {
                    if (!pattern.matcher(c.toString()).lookingAt()) {
                        condition = false;
                        System.out.println("false inner");
                        return condition;
                    } else {
                        condition = true;
                        System.out.println("true here " + toCheck[i]);
                    }
                }
//                condition = false;
//                System.out.println("false here " + toCheck[i]);
//                return condition;
//            } else {
//                condition = true;
//                System.out.println("true here "+toCheck[i]);
//            }
            }
        } else {
            condition=false;
        }
        return condition;
    }


//smaller
boolean isMAC48Address(String inputString) {
     Pattern p = Pattern.compile("^([0-9a-fA-F]{2}[-]){5}([0-9a-fA-F]{2})$");
     Matcher m = p.matcher(inputString);
     return m.matches();
}
