package com.company;

//        Todo: Modifiera programmet så att det använder en eller flera klasser som kan användas för att:
//        Todo: Generera nycklar och spara dem i filer
//        Todo:  Läsa in sparade nycklar
//        Todo:  Kunna kryptera och dekryptera strängar
//        Todo:  Kunna kryptera och dekryptera textfiler
//        Todo:  Om du har tid, kunna utföra signiering, alltså verifiera att någon har tillgång till den privata nyckeln för en publik nyckel som du själv har tillgång till
//        Todo:  Om du har tid, även kunna kryptera och dekryptera binärfiler
//        Todo: Lämna helst in koden i form av en länk till ett publikt GitHub-repo.

import com.company.Menu.Menu;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

//        for (String keyFile : new Menu().getKeyFiles()) {
//            System.out.println(keyFile);
//        }
        new Menu().printMenu();
    }
}