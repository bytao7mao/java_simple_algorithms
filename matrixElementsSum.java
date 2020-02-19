static int matrixElementsSum1(int[][] matrix){
        int c = 0, length;
        //3 rounds
        for(int i = 0; i < matrix.length; i++){
            //4 rounds
            for(int j = 0; j < matrix[i].length; j++){
                if(matrix[i][j] == 0){
                    length = i;
                    while(length < matrix.length-1){
                        //goes down under 0 ROW and says that the number
                        //from that matrix is 0
                        matrix[i+1][j] = 0;
                        length++;
                    }
                }
                else c += matrix[i][j];
            }
        }
        return c;
    }
