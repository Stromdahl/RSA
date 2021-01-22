package com.company.Menu;

import com.company.RSA.RSA;
import com.company.RSA.RSAKey;

import java.io.IOException;
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
                case 4 -> encryptMessage();
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
        }
    }

    void loadKeys() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a name for the key you want to load: ");
        System.out.println("> ");
        String keyName = scanner.nextLine();


        try {
            this.key = new RSAKey(keyName);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not load the file " + keyName);
            return;
        }
        System.out.println(keyName + " are now loaded");
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

    private void encryptMessage() {
        //Todo: encryptMessage();
        //Scanner scanner = new Scanner(System.in);
        System.out.println("Write the message to encrypt");
    }

    private void decryptMessage() {
        //Todo: decryptMessage();
        System.out.println("Decrypt Message");

    }

    private void exit() {
        System.exit(0);
    }

}
