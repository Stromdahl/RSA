package com.company.RSA;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class RSAKey {
    private KeyPair key;

    public RSAKey(String fileName) throws IOException, ClassNotFoundException {
        readKey(fileName);
    }

    RSAKey(BigInteger key, BigInteger n) {
        setKey(new KeyPair(key, n));
    }

    public void saveKey(String fileName) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(fileName);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(key);
        out.close();
    }

    public void readKey(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(fileName);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        this.setKey((KeyPair) in.readObject());
        in.close();
    }

    public String encrypt(String message) {
        return (new BigInteger(message.getBytes(StandardCharsets.UTF_8))).modPow(key.getKey(), key.getN()).toString();
    }

    public String decrypt(String message) {
        String msg = new String(message.getBytes(StandardCharsets.UTF_8));
        return new String((new BigInteger(msg)).modPow(key.getKey(), key.getN()).toByteArray());
    }

    public KeyPair getKey() {
        return key;
    }

    public void setKey(KeyPair key){
        this.key = key;
    }

    public static class KeyPair implements Serializable {
        @Serial
        private static final long serialVersionUID = 4L;
        private BigInteger key;
        private BigInteger n;

        public KeyPair(BigInteger key, BigInteger n) {
            this.setKey(key);
            this.setN(n);
        }

        public BigInteger getN() {
            return this.n;
        }

        public void setN(BigInteger n) {
            this.n = n;
        }

        public BigInteger getKey() {
            return this.key;
        }

        public void setKey(BigInteger key) {
            this.key = key;
        }
    }
}
