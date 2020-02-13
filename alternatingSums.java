static int[] alternatingSums(int[] a) {

        int[] x = new int[2];
        int a1 =0,a2 = 0;
        for (int i=0;i<a.length;i++){
            if (i%2==0){
                a1 += a[i];
            } else {
                a2 += a[i];
            }
        }
        x[0] = a1;x[1] = a2;

        return x;
    }
