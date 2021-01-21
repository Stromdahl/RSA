package com.company;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class RSAKey {
    private KeyPair key;

    RSAKey(String fileName){
        readKey(fileName);
    }

    RSAKey(BigInteger key, BigInteger n){
        setKey(new KeyPair(key, n));
    }

    public KeyPair getKey() {
        return key;
    }

    public void setKey(KeyPair key) {
        this.key = key;
    }

    public void saveKey(String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(key);
            out.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void readKey(String fileName) {
        KeyPair key = null;
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            key = (KeyPair) in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }
        this.key = key;
    }
    
    public String encrypt(String message) {
        return (new BigInteger(message.getBytes(StandardCharsets.UTF_8))).modPow(key.getKey(), key.getN()).toString();
    }

    public String decrypt(String message) {
        String msg = new String(message.getBytes(StandardCharsets.UTF_8));
        return new String((new BigInteger(msg)).modPow(key.getKey(), key.getN()).toByteArray());
    }
}
