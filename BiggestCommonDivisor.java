public int GCD(int a, int b) {
        if (b==0)
            return a;
        else
            return GCD(b,a%b);
    }

// Short example: 
// GCD(8,12){
//   GCD(12,4);
// }GCD(4,0){
//   b==0 so we return 4;
// }
