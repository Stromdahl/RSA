package com.company.RSA;

import java.io.*;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class RSA {
    private static BigInteger generateRandomBigInteger(int bitLength) {
        SecureRandom rand = new SecureRandom();
        return new BigInteger(bitLength / 2, 100, rand);
    }

    public static void generateKeys(String keyName, int bitLength) {
        BigInteger p = generateRandomBigInteger(bitLength);
        BigInteger q = generateRandomBigInteger(bitLength);
        BigInteger n = p.multiply(q);
        BigInteger phiN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        BigInteger e = new BigInteger("3");
        while (phiN.gcd(e).intValue() > 1) {
            e = e.add(new BigInteger("2"));
        }
        BigInteger d = e.modInverse(phiN);
        //Public key: e + n; Private key d + n;
        new RSAKey(e, n).saveKey("./keys/" + keyName + "_pub.key");
        new RSAKey(d, n).saveKey("./keys/" + keyName + "_priv.key");
    }

    public static void saveEncryptedMessage(String message, String fileName) {
        try {
            File myObj = new File(fileName + ".enc");
            if (myObj.createNewFile()) {
                FileWriter myWriter = new FileWriter(myObj);
                myWriter.write(message);
                myWriter.close();

            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static String readEncryptedMessage(String fileName) {
        String data = "";
        try {
            File myObj = new File(fileName + ".enc");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return data;
    }
}



