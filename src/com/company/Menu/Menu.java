package com.company.Menu;

import com.company.RSA.RSA;
import com.company.RSA.RSAKey;

import java.io.File;
import java.io.FileNotFoundException;
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
                "Encrypt...",
                "Decrypt...",
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
        Scanner systemInScanner = new Scanner(System.in);
        System.out.print("Enter a name of the keys: ");
        String keyName = systemInScanner.nextLine();

        System.out.print("Enter the number of bits: ");
        int bitLength = systemInScanner.nextInt();

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
        Scanner systemInScanner = new Scanner(System.in);
        System.out.println("Please enter the number for the key you want to load: ");

        String[] availableKeys = getKeyFiles();
        for (int i = 0; i < availableKeys.length; i++) {
            System.out.println((i + 1) + ". " + availableKeys[i]);
        }

        String keyName = "";
        while (true) {
            try {
                keyName = availableKeys[systemInScanner.nextInt() - 1];
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
        if (this.key == null) {
            System.out.println("No key loaded - generate new keys or load a existing key");
            return;
        }
        Scanner systemInScanner = new Scanner(System.in);
        while (true) {
            System.out.println(">>>>>> Encrypt <<<<<<");
            System.out.println("=====================");

            System.out.println("Please choose below");
            System.out.println("1. Enter message to encrypt");
            System.out.println("2. Encrypt file");
            System.out.println("3. Back to main menu...");


            int userIn;
            String encryptedMessage = "";
            String message;
            while (true) {
                try {
                    System.out.print("> ");
                    userIn = systemInScanner.nextInt();
                    systemInScanner.nextLine();
                } catch (InputMismatchException e) {
                    continue;
                }
                break;
            }

            switch (userIn) {
                case 1:
                    System.out.print("Please enter the message you want you encrypt: ");
                    message = systemInScanner.nextLine();

                    encryptedMessage = this.key.encrypt(message);
                    break;
                case 2:
                    System.out.print("Enter the path to the file you want to encrypt: ");
                    while (encryptedMessage.isEmpty()){
                        String path = systemInScanner.next();
                        try {
                            message = RSA.readMessage(path);
                        } catch (FileNotFoundException e) {
                            System.out.println("Not a file");
                            continue;
                        }
                        encryptedMessage = key.encrypt(message);
                    }
                    break;
                case 3:
                    return;
                default:
                    continue;
            }


            System.out.print("Do you want to save the encrypted message to a file? (y/n): ");
            while (true) {
                String yOrN = systemInScanner.nextLine();

                switch (yOrN.toLowerCase()) {
                    case "n" -> {
                        System.out.println(encryptedMessage);
                        System.out.println("Press enter to continue");
                        systemInScanner.nextLine();
                        return;
                    }
                    case "y" -> {
                        System.out.print("Please enter the filename: ");
                        while (true) {
                            try {
                                RSA.saveEncryptedMessage(encryptedMessage, systemInScanner.nextLine());
                                return;
                            } catch (IOException e) {
                                System.out.println("Could not save to that file");
                            }
                        }
                    }
                }
            }
        }
    }

    private void decryptMessage() {
        if (this.key == null) {
            System.out.println("No key loaded - generate new keys or load a existing key");
            return;
        }
        Scanner systemInScanner = new Scanner(System.in);
        while (true) {
            System.out.println(">>>>>> Decrypt <<<<<<");
            System.out.println("=====================");

            System.out.println("Please choose below");
            System.out.println("1. Enter message to decrypt");
            System.out.println("2. Decrypt file");
            System.out.println("3. Back to main menu...");


            int userIn;
            String decryptedMessage = "";
            while (true) {
                try {
                    System.out.print("> ");
                    userIn = systemInScanner.nextInt();
                    systemInScanner.nextLine();
                } catch (InputMismatchException e) {
                    continue;
                }
                break;
            }

            switch (userIn) {
                case 1:
                    System.out.print("Please enter the message you want you decrypt: ");
                    String message = systemInScanner.nextLine();

                    decryptedMessage = this.key.decrypt(message);
                    break;
                case 2:
                    do {
                        System.out.print("Enter the path to the file you want to decrypt: ");
                        String path = systemInScanner.nextLine();
                        try {
                            decryptedMessage = key.decrypt(RSA.readMessage(path));
                        } catch (FileNotFoundException e) {
                            System.out.println("Not a file");
                        }
                    } while (decryptedMessage.isEmpty());
                    break;
                case 3:
                    return;
                default:
                    continue;
            }


            System.out.print("Do you want to save the decrypted message to a file? (y/n): ");
            while (true) {
                String yOrN = systemInScanner.nextLine();

                switch (yOrN.toLowerCase()) {
                    case "n" -> {
                        System.out.println("Message: " + decryptedMessage);
                        System.out.println("Press enter to continue");
                        systemInScanner.nextLine();
                        return;
                    }
                    case "y" -> {
                        while (true) {
                            System.out.print("Please enter the filename: ");
                            try {
                                RSA.saveEncryptedMessage(decryptedMessage, systemInScanner.nextLine());
                                return;
                            } catch (IOException e) {
                                System.out.println("Could not save to that file");
                            }
                        }
                    }
                }
            }
        }

    }

    private void exit() {
        System.exit(0);
    }

}
