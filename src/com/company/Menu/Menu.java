package com.company.Menu;

import com.company.RSA.RSA;
import com.company.RSA.RSAKey;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    RSAKey key;
    String keyName = "";


    public void printMenu() {
        String[] menuOptions = {
                "Generate keys",
                "Load key",
                "Get loaded key",
                "Encrypt message",
                "Decrypt message",
                "Exit"
        };

        //noinspection InfiniteLoopStatement
        while (true) {
            Scanner systemInScanner = new Scanner(System.in);
            System.out.println(">>>>>>>> RCA <<<<<<<<");
            System.out.println("=====================");
            printMenuOptions(menuOptions);

            int userIn;

            while (true) {
                try {
                    System.out.print("> ");
                    userIn = systemInScanner.nextInt();
                } catch (InputMismatchException e) {
                    if (systemInScanner.hasNext()) systemInScanner.next();
                    continue;
                }
                break;
            }
            switch (userIn) {
                case 1 -> generateKeys();
                case 2 -> loadKeys();
                case 3 -> getLoadedKey();
                case 4 -> printEncryptMenu();
                case 5 -> decryptMessage();
                case 6 -> exit();
            }
        }

    }

    private void printMenuOptions(String[] menuOptions) {
        for (int i = 0; i < menuOptions.length; i++) {
            System.out.println((i + 1) + ". " + menuOptions[i]);
        }
    }


    private void generateKeys() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a name of the keys: ");
        String keyName = scanner.nextLine();

        System.out.print("Enter the number of bits: ");
        int bitLength = scanner.nextInt();

        try {
            key = RSA.generateKeys(keyName, bitLength)[0];
            this.keyName = keyName;
        } catch (IOException e) {
            System.out.println("File already exists.");
            return;
        }
        System.out.println(keyName + " are now loaded \n Press enter to continue");

    }

    void loadKeys() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the number for the key you want to load: ");

        String[] availableKeys = getKeyFiles();
        for (int i = 0; i < availableKeys.length; i++) {
            System.out.println((i + 1) + ". " + availableKeys[i]);
        }

        String keyName = "";
        while (true) {
            try {
                keyName = availableKeys[scanner.nextInt() - 1];
                this.key = new RSAKey("./keys/" + keyName);
                break;
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Could not load the file " + keyName);
                return;
            } catch (Exception ignored) {
                System.out.println("not a valid input");
            }
        }
        System.out.println(keyName + " are now loaded");
    }

    public String[] getKeyFiles() {
        File dir = new File("keys");
        File[] files = dir.listFiles((dir1, name) -> name.toLowerCase().endsWith(".key"));

        String[] fileNames = files != null ? new String[files.length] : new String[0];
        for (int i = 0; i < fileNames.length; i++) {
            String[] splitPath = files[i].getName().split("/");
            fileNames[i] = splitPath[splitPath.length - 1];
        }
        return fileNames;
    }

    private void getLoadedKey() {
        if (key == null) {
            System.out.print("No key loaded");
        } else {
            System.out.print(this.keyName);
        }
        System.out.print(" - Press enter to continue");
        new Scanner(System.in).nextLine();
    }

    private void printEncryptMenu() {
        //Todo: encryptMessage();

        if (this.key == null) {
            System.out.println("No key loaded - generate new keys or load a existing key");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Pleas enter the message you want yo encrypt: ");
        String message = scanner.nextLine();
        String encryptedMessage = this.key.encrypt(message);
        System.out.println(encryptedMessage);
    }

    private void decryptMessage() {
        //Todo: decryptMessage();
        System.out.println("Decrypt Message");

    }

    private void exit() {
        System.exit(0);
    }

}
