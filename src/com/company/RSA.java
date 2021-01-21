package com.company;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {

    public RSAKey privateKey;
    public RSAKey publicKey;

    public RSA() {
    }

    RSA(String fileName) {
        readKeys(fileName);
    }

    public void saveKeys(String fileName){
        publicKey.saveKey(fileName + "_pub.key");
        privateKey.saveKey(fileName + "_priv.key");
    }

    public void readKeys(String fileName){
        publicKey.readKey(fileName + "_pub.key");
        privateKey.readKey(fileName + "_priv.key");
    }

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
        new RSAKey(e, n).saveKey(keyName + "_pub.key");
        new RSAKey(d, n).saveKey(keyName + "_priv.key");
    }
}
