public static boolean findEqual(int[] sequence) {
        for (int i = 0; i < sequence.length; i++) {
            for (int j = i+1; j < sequence.length; j++) {
                if (sequence[i] == sequence[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        //at least two numbers to be equal
        boolean isEqual = findEqual(new int[]{1,1,2,3});
        System.out.println(isEqual);
    }
