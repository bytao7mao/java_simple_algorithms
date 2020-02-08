private static int sumUpNumbers(String inputString) {
        int count = 0;
        Pattern p = Pattern.compile("-?\\d+");
        Matcher m = p.matcher(inputString);
        while (m.find()) {
            count+=Integer.parseInt(m.group());
            System.out.println(m.group());
        }
        
        //variant #2
//         String[] splittingTheNumbers=inputString.split("\\D+");
//         int count=0;
//         for (String num:splittingTheNumbers){
//             if (num.length()>0) count+=Integer.parseInt(num);
//         }
//         return count;
        
        //variant #3
//      int count=0;
//      Matcher m = Pattern.compile("\\d+").matcher(inputString);
//      while (m.find()) {
//             count+=Integer.parseInt(m.group());
//         }
//     return count;

        System.out.println(count);
        return count;
    }
