String rome(int input) {
String s = "";

    while (input >= 1000) {
        s += "M";
        input -= 1000;        
    } while (input >= 900) {
        s += "CM";
        input -= 900;
    } while (input >= 500) {
        s += "D";
        input -= 500;
    } while (input >= 400) {
        s += "CD";
        input -= 400;
    } while (input >= 100) {
        s += "C";
        input -= 100;
    } while (input >= 90) {
        s += "XC";
        input -= 90;
    } while (input >= 50) {
        s += "L";
        input -= 50;
    } while (input >= 40) {
        s += "XL";
        input -= 40;
    } while (input >= 10) {
        s += "X";
        input -= 10;
    } while (input >= 9) {
        s += "IX";
        input -= 9;
    } while (input >= 5) {
        s += "V";
        input -= 5;
    } while (input >= 4) {
        s += "IV";
        input -= 4;
    } while (input >= 1) {
        s += "I";
        input -= 1;
    }

    return s;
}

//go

func rome(n int) (o string) {
    M := "IVXLCDM "
    for n>0 {
        t := n%10
        s := M[:t%5/4]
        n /= 10
        for t>0 {
            t++
            k := t/5
            s += M[k:k+1]
            t -= k*4 + 2
        }
        o = s + o
        M = M[2:]
    }
    return
}
//js
rome = n =>  {
    c = 'IVXLCDM'
    for (a = 5, b = s = ''; n; b++, a ^= 7)
        for (o = n % a, n = n / a ^ 0; o--; s = c[~~(o > 2 ? b + n - (n &= -2) + (o = 1) : b)] + s) ;
    return s
}
//py2
def rome(n):
    a = ""
    i = 0
    N = [1000,900,500,400]
    for k in N:
        if k:
            a += n/k*(i%2*'CXI '[i/4]+'MDCLXVI'[i/2])
            n %= k  
            N += k/10,
            i += 1

    return a
//haskell

rome = f 0

f i 0                       = ""
f i n
    | t <- mod n 10, t > 0  = f i (n - y t) ++ ["IVXLCDM" !! (i + x t)]
    | 1 > 0                 = f (i+2) $ div n 10

x 4 = 1
x 5 = 1
x 9 = 2
x _ = 0

y 4 = 3
y 5 = 5
y 9 = 8
y _ = 1

//lisp
    (defun rome (n) 
  (format / "~@r" n))
