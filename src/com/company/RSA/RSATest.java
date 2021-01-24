package com.company.RSA;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class RSATest {
    @Test
    public void testGenerateKeys() throws IOException {
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
    public void testReadWriteMessage() throws IOException {
        Random random = new Random();
        String keyName = "" + (random.nextInt(1000 - 100) + 100);
        RSA.generateKeys(keyName, 2048);

        String message = "Message";

        RSAKey publicKey = null;
        RSAKey privateKey = null;

        try {
            publicKey = new RSAKey("./keys/" + keyName + "_pub.key");
            privateKey = new RSAKey("./keys/" + keyName + "_priv.key");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        assert publicKey != null;
        String encryptedMessageWidthPublicKey = publicKey.encrypt(message);

        try {
            RSA.saveEncryptedMessage(encryptedMessageWidthPublicKey, keyName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String encryptedMessage = RSA.readMessage(keyName);

        assert privateKey != null;
        String cleanMessage = privateKey.decrypt(encryptedMessage);

        new File("./keys/" + keyName + "_pub.key").delete();
        new File("./keys/" + keyName + "_priv.key").delete();
        new File(keyName + ".enc").delete();

        assertEquals(message, cleanMessage);
    }
}
