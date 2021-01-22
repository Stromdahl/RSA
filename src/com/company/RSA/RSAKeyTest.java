package com.company.RSA;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Random;

@SuppressWarnings("ResultOfMethodCallIgnored")

public class RSAKeyTest {

    @Test
    public void testEncryptDecryptMessage() throws IOException {
        Random random = new Random();
        String keyName = "key" + (random.nextInt(1000 - 100) + 100);
        RSA.generateKeys(keyName, 2048);

        RSAKey publicKey = null;
        RSAKey privateKey = null;

        try {
            publicKey = new RSAKey("./keys/" + keyName + "_pub.key");
            privateKey = new RSAKey("./keys/" + keyName + "_priv.key");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        new File("./keys/" + keyName + "_pub.key").delete();
        new File("./keys/" + keyName + "_priv.key").delete();

        String message = "HelloWorld!";

        assert publicKey != null;
        String encryptedMessageWidthPublicKey = publicKey.encrypt(message);
        assert privateKey != null;
        String cleanMessage = privateKey.decrypt(encryptedMessageWidthPublicKey);

        assertEquals(message, cleanMessage);
    }

    @Test
    public void testEncryptDecryptSigning() throws IOException {
        Random random = new Random();
        String keyName = "key" + (random.nextInt(1000 - 100) + 100);
        RSA.generateKeys(keyName, 2048);

        RSAKey publicKey = null;
        RSAKey privateKey = null;

        try {
            publicKey = new RSAKey("./keys/" + keyName + "_pub.key");
            privateKey = new RSAKey("./keys/" + keyName + "_priv.key");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        new File("./keys/" + keyName + "_pub.key").delete();
        new File("./keys/" + keyName + "_priv.key").delete();

        String message = "name";

        assert privateKey != null;
        String encryptedSignWidthPrivateKey = privateKey.encrypt(message);
        String cleanMessage = publicKey.decrypt(encryptedSignWidthPrivateKey);

        assertEquals(message, cleanMessage);


    }
}

