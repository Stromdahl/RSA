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

    public static RSAKey[] generateKeys(String keyName, int bitLength) throws IOException {
        BigInteger p = generateRandomBigInteger(bitLength);
        BigInteger q = generateRandomBigInteger(bitLength);
        BigInteger n = p.multiply(q);
        BigInteger phiN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        BigInteger e = new BigInteger("3");
        while (phiN.gcd(e).intValue() > 1) {
            e = e.add(new BigInteger("2"));
        }
        BigInteger d = e.modInverse(phiN);

        RSAKey[] keys = {
                new RSAKey(e, n), //Public key: e + n;
                new RSAKey(d, n)  // Private key d + n;
        };

        keys[0].saveKey("./keys/" + keyName + "_pub.key");
        keys[1].saveKey("./keys/" + keyName + "_priv.key");

        return keys;
    }

    public static void saveEncryptedMessage(String message, String fileName) throws IOException {
        File myObj = new File(fileName);
        if (myObj.createNewFile()) {
            FileWriter myWriter = new FileWriter(myObj);
            myWriter.write(message);
            myWriter.close();
        } else {
            throw new IOException();
        }
    }

    public static String readMessage(String fileName) throws FileNotFoundException {
        String data = "";
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
            }
            myReader.close();
        return data;
    }
}



