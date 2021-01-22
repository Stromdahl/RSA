package com.company.RSA;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RSATest {
    @Test
    public void testGenerateKeys(){
        Random random = new Random();
        String keyName = "key" + (random.nextInt(1000 - 100) + 100);
        RSA.generateKeys(keyName, 2048);

        File publicKey = new File("./keys/" + keyName + "_pub.key");
        assertTrue(publicKey.exists());
        publicKey.delete();

        File privateKey = new File("./keys/" + keyName + "_priv.key");
        assertTrue(privateKey.exists());
        privateKey.delete();
    }

    @Test
    public void testReadWriteMessage(){
        Random random = new Random();
        String keyName = "" + (random.nextInt(1000 - 100) + 100);
        RSA.generateKeys(keyName, 2048);

        String message = "Message";

        RSAKey publicKey = new RSAKey("./keys/" + keyName + "_pub.key");
        RSAKey privateKey = new RSAKey("./keys/" + keyName + "_priv.key");



        String encryptedMessageWidthPublicKey = publicKey.encrypt(message);

        RSA.saveEncryptedMessage(encryptedMessageWidthPublicKey, keyName);
        String encryptedMessage = RSA.readEncryptedMessage(keyName);

        String cleanMessage = privateKey.decrypt(encryptedMessage);

        new File("./keys/" + keyName + "_pub.key").delete();
        new File("./keys/" + keyName + "_priv.key").delete();
        new File(keyName + ".enc").delete();

        assertEquals(message, cleanMessage);
    }
}
