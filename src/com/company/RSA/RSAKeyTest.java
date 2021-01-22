package com.company.RSA;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Random;

public class RSAKeyTest {

    @Test
    public void testEncryptDecryptMessage() {
        Random random = new Random();
        String keyName = "key" + (random.nextInt(1000 - 100) + 100);
        RSA.generateKeys(keyName, 2048);

        RSAKey publicKey = new RSAKey(keyName + "_pub.key");
        RSAKey privateKey = new RSAKey(keyName + "_priv.key");

        String message = "HelloWorld!";

        String encryptedMessageWidthPublicKey = publicKey.encrypt(message);
        String cleanMessage = privateKey.decrypt(encryptedMessageWidthPublicKey);

        assertEquals(message, cleanMessage);

        File publicKeyFile = new File(keyName + "_pub.key");
        File privateKeyFile = new File(keyName + "_priv.key");
        publicKeyFile.delete();
        privateKeyFile.delete();
    }

    @Test
    public void testEncryptDecryptSigning() {
        Random random = new Random();
        String keyName = "key" + (random.nextInt(1000 - 100) + 100);
        RSA.generateKeys(keyName, 2048);

        RSAKey publicKey = new RSAKey(keyName + "_pub.key");
        RSAKey privateKey = new RSAKey(keyName + "_priv.key");

        String message = "name";

        String encryptedSignWidthPrivateKey = privateKey.encrypt(message);
        String cleanMessage = publicKey.decrypt(encryptedSignWidthPrivateKey);

        assertEquals(message, cleanMessage);

        File publicKeyFile = new File(keyName + "_pub.key");
        File privateKeyFile = new File(keyName + "_priv.key");
        publicKeyFile.delete();
        privateKeyFile.delete();
    }
}

