package com.company;

public class Main {
    public static void main(String[] args) {
        int bitLength = 4096;
        RSA rsa = new RSA(bitLength);


        String encrypted = rsa.encrypt("Pröjsa genast", rsa.publicKey);
        String clear = rsa.decrypt(encrypted, rsa.privateKey);
        System.out.println(clear);
        // Encrypt
        //String cipher = msg.modPow(e, n).toString();
        // Decrypt
        //String plain = new String((new BigInteger(cipher)).modPow(d, n).toByteArray());
    }
}