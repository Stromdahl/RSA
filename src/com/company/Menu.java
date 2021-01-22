package com.company;

import com.company.RSA.RSA;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    Menu() {
        printMenu();
    }

    private void printMenu() {
        String[] menuOptions = {
                "Generate keys",
                "Load key",
                "Get loaded key",
                "Encrypt message",
                "Decrypt message",
                "Exit"
        };


        while (true) {
            Scanner systemInScanner = new Scanner(System.in);
            System.out.println(">>>>>>>> RCA <<<<<<<<");
            System.out.println("=====================");
            printMenuOptions(menuOptions);

            int userIn = 0;

            while (true) {
                try {
                    System.out.print("> ");
                    userIn = systemInScanner.nextInt();
                } catch (InputMismatchException e) {
                    if(systemInScanner.hasNext()) systemInScanner.next();
                    continue;
                }
                break;
            }
            switch (userIn) {
                case 1 -> generateKeysOption();
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
            System.out.println((i+1) + ". " + menuOptions[i]);
        }
    }

    private void exit() {
        System.exit(0);
    }

    private void generateKeysOption() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a name of the keys: ");
        String keyName = scanner.nextLine();

        System.out.print("Enter the number of bits: ");
        int bitLength = scanner.nextInt();

        RSA.generateKeys(keyName, bitLength);
    }

    private void decryptMessage() {
        //Todo: decryptMessage();
        System.out.println("Decrypt Message");

    }

    private void encryptMessage() {
        //Todo: encryptMessage();
        System.out.println("Encrypt Message");
    }

    private void getLoadedKey() {
        //Todo: encryptMessage()
        System.out.println("Get load key");
    }

    private void loadKeys() {
        //Todo: loadKeys();
        System.out.println("");
    }
}
