int adjacentElementsProduct(int[] inputArray) {
    int start = inputArray[0] * inputArray[1];

        for (int i=1; i < inputArray.length-1; i++){
            int afterStart = inputArray[i] * inputArray[i+1];
            if (afterStart > start){
                start = afterStart;
            }
        }
        return start;
}
