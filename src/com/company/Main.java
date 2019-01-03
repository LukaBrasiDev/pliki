package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // utworzenie pliku
        File file = new File("C:\\Users\\famiglia\\Desktop\\pliki\\test.txt");

        System.out.println(file.exists());
//przechwytywanie wyjatku
 /*       try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("nie udalo sie ytworrzyc pliku");
        }*/

//dodawanie zawartosci do pliku
        System.out.println("Podaj tresc dododania do pliku: " + file);
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();

        try {
            Files.write(file.toPath(), ("\r\n" + "dodany tekst: " + text).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
//odczyt danych z pliku
        List<String> lines = null;
        try {
            lines = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(lines);

//dodawanie zawartosci pliku ver 2

        String answer;
        do {
            System.out.println("Podaj wyraz do dodania do pliku, zakoncz slowem exit");
            answer = scanner.nextLine();
            try {
                Files.write(file.toPath(), (answer + "\r\n").getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (!answer.equals("exit"));


//tworzenie pliku lub katalogu
        String answer2;
        do {
            printMenu();
            answer2 = scanner.nextLine();
            File file2;
            switch (answer2) {
                case "1": {
                    System.out.println("podaj sciezke z nazwa pliku:");
                    String fileName = scanner.nextLine();
                    file2 = new File(fileName);
                    try {
                        file2.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "2": {
                    System.out.println("podaj sciezke z nazwa katalogu:");
                    String fileName2 = scanner.nextLine();
                    file2 = new File(fileName2);
                    file2.mkdir();
                    break;
                }

                case "3": {
                    System.out.println("podaj sciezke z nazwa do usuniecia:");
                    String fileName2 = scanner.nextLine();
                    file2 = new File(fileName2);
                    if (file2.exists() == true) {
                        file2.delete();
                    }


                    break;
                }
            }

        } while (!answer2.equals("exit"));

// podmiana slowa w pliku

        System.out.println("Podaj slowo ktore chcesz podmienic");
        String wordToReplace = scanner.next();
        System.out.println("Podaj slowo na ktore chcesz podmienic");
        String replacement = scanner.next();

        StringBuilder allText = new StringBuilder();
        for (String line : lines
        ) {
            allText.append(line.replace(wordToReplace, replacement));
        }
        try {
            Files.write(file.toPath(),allText.toString().getBytes(),StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void printMenu() {
        System.out.println("menu: 1 - stworz plik, 2 - stworz katalog, 3 - usun lub exit");
    }
}

