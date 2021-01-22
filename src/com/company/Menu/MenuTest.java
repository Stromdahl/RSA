package com.company.Menu;

import com.company.RSA.RSA;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class MenuTest {
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    void testLoadKeysSuccessful() throws IOException {
        new Scanner(System.in);
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        Random random = new Random();
        String keyName = "key" + (random.nextInt(1000 - 100) + 100);
        RSA.generateKeys(keyName, 2048);

        String userInput = "./keys/" + keyName + "_pub.key";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        new Menu().loadKeys();

        File publicKeyFile = new File("./keys/" + keyName + "_pub.key");
        File privateKeyFile = new File("./keys/" + keyName + "_priv.key");
        publicKeyFile.delete();
        privateKeyFile.delete();

        String expected = "Please enter a name for the key you want to load: \n" + "> \n" + String.format("./keys/%s_pub.key are now loaded", keyName);
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    void testLoadKeysFileNotLoaded() {
        new Scanner(System.in);
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        Random random = new Random();
        String keyName = "key" + (random.nextInt(1000 - 100) + 100);

        String userInput = "./keys/" + keyName + "_pub.key";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        new Menu().loadKeys();

        String expected = String.format("""
                Please enter a name for the key you want to load:\s
                >\s
                Could not load the file %s""", userInput);
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }
}