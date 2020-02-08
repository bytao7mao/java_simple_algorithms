package com.company;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BinaryTransform {
    public String getA() {
        return "01000001";
    }

    public String getB() {
        return "01000010";
    }

    public String getC() {
        return "01000011";
    }

    public String getD() {
        return "01000100";
    }

    public String getE() {
        return "01000101";
    }

    public String getF() {
        return "01000110";
    }

    public String getG() {
        return "01000111";
    }

    public String getH() {
        return "01001000";
    }

    public String getI() {
        return "01001001";
    }

    public String getJ() {
        return "01001010";
    }

    public String getK() {
        return "01001011";
    }

    public String getL() {
        return "01001100";
    }

    public String getM() {
        return "01001101";
    }

    public String getN() {
        return "01001110";
    }

    public String getO() {
        return "01001111";
    }

    public String getP() {
        return "01010000";
    }

    public String getR() {
        return "01010010";
    }

    public String getS() {
        return "01010011";
    }

    public String getT() {
        return "01010100";
    }

    public String getU() {
        return "01010101";
    }

    public String getV() {
        return "01010110";
    }

    public String getW() {
        return "01010111";
    }

    public String getX() {
        return "01011000";
    }

    public String getY() {
        return "01011001";
    }

    public String getZ() {
        return "01011010";
    }

    public String getComma() {
        return "00101100";
    }

    public String getDot() {
        return "00101110";
    }

    public String getExclamation_sign() {
        return "00100001";
    }

    public String getApostrophe() {
        return "01100000";
    }

    public String getBlank_space() {
        return "0010000";
    }


    //uppercase letters
    char A = (char)Integer.valueOf("01000001", 2).intValue();
    char B = (char)Integer.valueOf("01000010", 2).intValue();
    char C = (char)Integer.valueOf("01000011", 2).intValue();
    char D = (char)Integer.valueOf("01000100", 2).intValue();
    char E = (char)Integer.valueOf("01000101", 2).intValue();
    char F = (char)Integer.valueOf("01000110", 2).intValue();
    char G = (char)Integer.valueOf("01000111", 2).intValue();
    char H = (char)Integer.valueOf("01001000", 2).intValue();
    char I = (char)Integer.valueOf("01001001", 2).intValue();
    char J = (char)Integer.valueOf("01001010", 2).intValue();
    char K = (char)Integer.valueOf("01001011", 2).intValue();
    char L = (char)Integer.valueOf("01001100", 2).intValue();
    char M = (char)Integer.valueOf("01001101", 2).intValue();
    char N = (char)Integer.valueOf("01001110", 2).intValue();
    char O = (char)Integer.valueOf("01001111", 2).intValue();
    char P = (char)Integer.valueOf("01010000", 2).intValue();
    char R = (char)Integer.valueOf("01010010", 2).intValue();
    char S = (char)Integer.valueOf("01010011", 2).intValue();
    char T = (char)Integer.valueOf("01010100", 2).intValue();
    char U = (char)Integer.valueOf("01010101", 2).intValue();
    char V = (char)Integer.valueOf("01010110", 2).intValue();
    char W = (char)Integer.valueOf("01010111", 2).intValue();
    char X = (char)Integer.valueOf("01011000", 2).intValue();
    char Y = (char)Integer.valueOf("01011001", 2).intValue();
    char Z = (char)Integer.valueOf("01011010", 2).intValue();

    //lowercase letters
    char a = (char)Integer.valueOf("01100001", 2).intValue();
    char b = (char)Integer.valueOf("01100010", 2).intValue();
    char c = (char)Integer.valueOf("01100011", 2).intValue();
    char d = (char)Integer.valueOf("01100100", 2).intValue();
    char e = (char)Integer.valueOf("01100101", 2).intValue();
    char f = (char)Integer.valueOf("01100110", 2).intValue();
    char g = (char)Integer.valueOf("01100111", 2).intValue();
    char h = (char)Integer.valueOf("01101000", 2).intValue();
    char i = (char)Integer.valueOf("01101001", 2).intValue();
    char j = (char)Integer.valueOf("01101010", 2).intValue();
    char k = (char)Integer.valueOf("01101011", 2).intValue();
    char l = (char)Integer.valueOf("01101100", 2).intValue();
    char m = (char)Integer.valueOf("01101101", 2).intValue();
    char n = (char)Integer.valueOf("01101110", 2).intValue();
    char o = (char)Integer.valueOf("01101111", 2).intValue();
    char p = (char)Integer.valueOf("01110000", 2).intValue();
    char r = (char)Integer.valueOf("01110010", 2).intValue();
    char s = (char)Integer.valueOf("01110011", 2).intValue();
    char t = (char)Integer.valueOf("01110100", 2).intValue();
    char u = (char)Integer.valueOf("01110101", 2).intValue();
    char v = (char)Integer.valueOf("01110110", 2).intValue();
    char w = (char)Integer.valueOf("01110111", 2).intValue();
    char x = (char)Integer.valueOf("01111000", 2).intValue();
    char y = (char)Integer.valueOf("01111001", 2).intValue();
    char z = (char)Integer.valueOf("01111010", 2).intValue();

    char comma = (char) Integer.valueOf("00101100",2).intValue(); //44 dec
    char dot = (char) Integer.valueOf("00101110",2).intValue(); //46 dec
    char exclamation_sign = (char) Integer.valueOf("00100001",2).intValue(); //33 dec
    char apostrophe = (char) Integer.valueOf("01100000",2).intValue(); //96 dec
    char blank_space = (char) Integer.valueOf("0010000",2).intValue(); //32 dec

    private static <T> List<Field> getFields(T t) {
        List<Field> fields = new ArrayList<>();
        Class clazz = t.getClass();
        while (clazz != Object.class) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        return fields;
    }

    @Override
    public String toString() {
        return "BinaryTransform{" +
                "A=" + A +
                ", B=" + B +
                ", C=" + C +
                ", D=" + D +
                ", E=" + E +
                ", F=" + F +
                ", G=" + G +
                ", H=" + H +
                ", I=" + I +
                ", J=" + J +
                ", K=" + K +
                ", L=" + L +
                ", M=" + M +
                ", N=" + N +
                ", O=" + O +
                ", P=" + P +
                ", R=" + R +
                ", S=" + S +
                ", T=" + T +
                ", U=" + U +
                ", V=" + V +
                ", W=" + W +
                ", X=" + X +
                ", Y=" + Y +
                ", Z=" + Z +
                ", a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", d=" + d +
                ", e=" + e +
                ", f=" + f +
                ", g=" + g +
                ", h=" + h +
                ", i=" + i +
                ", j=" + j +
                ", k=" + k +
                ", l=" + l +
                ", m=" + m +
                ", n=" + n +
                ", o=" + o +
                ", p=" + p +
                ", r=" + r +
                ", s=" + s +
                ", t=" + t +
                ", u=" + u +
                ", v=" + v +
                ", w=" + w +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", comma=" + comma +
                ", dot=" + dot +
                ", exclamation_sign=" + exclamation_sign +
                ", apostrophe=" + apostrophe +
                ", blank_space=" + blank_space +
                '}';
    }

    public static void main(String[] args) {
        String x = "ddabdbasbd";
        String result = "";
        BinaryTransform binaryTransform = new BinaryTransform();
        System.out.println(binaryTransform.blank_space);
        List<Field> allchars = getFields(binaryTransform);
        HashMap<Character, String> characterStringHashMap = new HashMap<>();

            characterStringHashMap.put(binaryTransform.A, "01000001");
            characterStringHashMap.put(binaryTransform.B, "01000010");
            characterStringHashMap.put(binaryTransform.C, "01000011");
            characterStringHashMap.put(binaryTransform.D, "01000100");
            characterStringHashMap.put(binaryTransform.E, "01000101");
            characterStringHashMap.put(binaryTransform.F, "01000110");
            characterStringHashMap.put(binaryTransform.G, "01000111");
            characterStringHashMap.put(binaryTransform.H, "01001000");
            characterStringHashMap.put(binaryTransform.I, "01001001");
            characterStringHashMap.put(binaryTransform.J, "01001010");
            characterStringHashMap.put(binaryTransform.K, "01001011");
            characterStringHashMap.put(binaryTransform.L, "01001100");
            characterStringHashMap.put(binaryTransform.M, "01001101");
            characterStringHashMap.put(binaryTransform.N, "01001110");
            characterStringHashMap.put(binaryTransform.O, "01001111");
            characterStringHashMap.put(binaryTransform.P, "01010000");
            characterStringHashMap.put(binaryTransform.R, "01010010");
            characterStringHashMap.put(binaryTransform.S, "01010011");
            characterStringHashMap.put(binaryTransform.T, "01010100");
            characterStringHashMap.put(binaryTransform.U, "01010101");
            characterStringHashMap.put(binaryTransform.V, "01010110");
            characterStringHashMap.put(binaryTransform.W, "01010111");
            characterStringHashMap.put(binaryTransform.X, "01011000");
            characterStringHashMap.put(binaryTransform.Y, "01011001");
            characterStringHashMap.put(binaryTransform.Z, "01011010");

            characterStringHashMap.put(binaryTransform.a, "01100001");
            characterStringHashMap.put(binaryTransform.b, "01100010");
            characterStringHashMap.put(binaryTransform.c, "01100011");
            characterStringHashMap.put(binaryTransform.d, "01100100");
            characterStringHashMap.put(binaryTransform.e, "01100101");
            characterStringHashMap.put(binaryTransform.f, "01100110");
            characterStringHashMap.put(binaryTransform.g, "01100111");
            characterStringHashMap.put(binaryTransform.h, "01101000");
            characterStringHashMap.put(binaryTransform.i, "01101001");
            characterStringHashMap.put(binaryTransform.j, "01101010");
            characterStringHashMap.put(binaryTransform.k, "01101011");
            characterStringHashMap.put(binaryTransform.l, "01101100");
            characterStringHashMap.put(binaryTransform.m, "01101101");
            characterStringHashMap.put(binaryTransform.n, "01101110");
            characterStringHashMap.put(binaryTransform.o, "01101111");
            characterStringHashMap.put(binaryTransform.p, "01110000");
            characterStringHashMap.put(binaryTransform.r, "01110010");
            characterStringHashMap.put(binaryTransform.s, "01110011");
            characterStringHashMap.put(binaryTransform.t, "01110100");
            characterStringHashMap.put(binaryTransform.u, "01110101");
            characterStringHashMap.put(binaryTransform.v, "01110110");
            characterStringHashMap.put(binaryTransform.w, "01110111");
            characterStringHashMap.put(binaryTransform.x, "01111000");
            characterStringHashMap.put(binaryTransform.y, "01111001");
            characterStringHashMap.put(binaryTransform.z, "01111010");
            characterStringHashMap.put(binaryTransform.comma, "00101100");
            characterStringHashMap.put(binaryTransform.exclamation_sign, "00100001");
            characterStringHashMap.put(binaryTransform.blank_space, "0010000");
            characterStringHashMap.put(binaryTransform.apostrophe, "01100000");
            characterStringHashMap.put(binaryTransform.dot, "00101110");




        for (int i=0; i<x.length(); i++){
            for (int j=0;j<allchars.size();j++){
            if (allchars.get(j).getName().contains(String.valueOf(x.charAt(i)))) {
                result = result + characterStringHashMap.get(x.charAt(i));
            } }
        }
        System.out.println(result);


//        for (int i=0; i<x.length(); i++){
//                if (characterStringHashMap.containsValue(x.substring(0,8))) {
//                    System.out.println("dsadsa");
//                }
//        }
//        System.out.println(result);

    }
}
