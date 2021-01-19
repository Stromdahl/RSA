package com.company;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class RSA {

    public KeyPair privateKey;
    public KeyPair publicKey;

    RSA(int bitLength) {
        generateKeyPairs(bitLength);
    }

    RSA(String fileName) {
        readKeys(fileName);
    }

    public void saveKeys(String fileName){
        saveKey(fileName, publicKey);
        saveKey(fileName, privateKey);
    }

    private void saveKey(String fileName, KeyPair key) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(key);
            out.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void readKeys(String fileName){
        publicKey = readKey(fileName + "_pub.key");
        privateKey = readKey(fileName + "_priv.key");
    }

    private KeyPair readKey(String fileName) {
        KeyPair key = null;
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            key = (KeyPair) in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }
        return key;
    }

    private BigInteger generateRandomBigInteger(int bitLength) {
        SecureRandom rand = new SecureRandom();
        return new BigInteger(bitLength / 2, 100, rand);
    }

    public void generateKeyPairs(int bitLength) {
        SecureRandom rand = new SecureRandom();

        BigInteger p = generateRandomBigInteger(bitLength);
        BigInteger q = generateRandomBigInteger(bitLength);
        BigInteger n = p.multiply(q);
        BigInteger phiN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        BigInteger e = new BigInteger("3");
        while (phiN.gcd(e).intValue() > 1) {
            e = e.add(new BigInteger("2"));
        }
        BigInteger d = e.modInverse(phiN);
        publicKey = new KeyPair(e, n);
        privateKey = new KeyPair(d, n);
    }

    public static String encrypt(String message, KeyPair key) {
        return (new BigInteger(message.getBytes(StandardCharsets.UTF_8))).modPow(key.getKey(), key.getN()).toString();
    }

    public static String decrypt(String message, KeyPair key) {
        String msg = new String(message.getBytes(StandardCharsets.UTF_8));
        return new String((new BigInteger(msg)).modPow(key.getKey(), key.getN()).toByteArray());
    }
}
