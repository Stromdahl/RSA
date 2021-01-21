package com.company;

import com.company.GUI.GUIFrame;
import com.company.GUI.GenerateKeysDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        int bitLength = 4096;
        //RSA rsa = new RSA(bitLength);

        new GUIFrame();



//        RSAKey pub = new RSAKey("Mattias_pub.key");
//        RSAKey priv = new RSAKey("Mattias_priv.key");
//
//        String encrypted = pub.encrypt("Pröjsa genast");
//        String clear = priv.decrypt(encrypted);
//        System.out.println(clear);
        // Encrypt
        //String cipher = msg.modPow(e, n).toString();
        // Decrypt
        //String plain = new String((new BigInteger(cipher)).modPow(d, n).toByteArray());
    }
}