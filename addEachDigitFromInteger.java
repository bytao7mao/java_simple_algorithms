static int digitDegree(int n) {
        int r = 0;
        if(n<10){
            return 0;
        }else{
            String bla = Integer.toString(n);
            String[] bla2 = bla.split("");
            int[] result = new int[bla.length()];
            for (int i = 0;i<bla.length();i++){
                result[i] = Integer.parseInt(bla2[i]);
            }
            for (int i:result){
                r = r + i;
            }
        }
        return r;
    }
