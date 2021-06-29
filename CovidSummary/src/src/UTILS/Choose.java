package src.UTILS;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import src.APP.API;
import src.APP.App;
import src.APP.Vykresleni;
import src.APP.dataVUT;
import src.UI.CovidData;

public class Choose {
    static Scanner sc = new Scanner(System.in);
    private static boolean konecVyberu = false;
    public static List<dataVUT> data = new ArrayList<>();
    public Choose() throws IOException, ParseException {
        Choose.data = App.parseVUT(API.fetchApi().toString());
    };
    
    public static void getUmrti() throws IOException, ParseException {
        do {   
            System.out.println(MenuStr.getMenuActual());
            int volba = CovidData.nacteniVolby();
            obsluhaUmrti(volba);
        } while (!konecVyberu);
    }

    public static void getTesty() throws IOException, ParseException {
        do {
            System.out.println(MenuStr.getMenuActual());
            int volba = CovidData.nacteniVolby();
            obsluhaTesty(volba);
        } while (!konecVyberu);
    }

    public static void getVylecene() throws IOException, ParseException {
        do {
            System.out.println(MenuStr.getMenuActual());
            int volba = CovidData.nacteniVolby();
            obsluhaVyleceni(volba);
        } while (!konecVyberu);
    }

    private static void obsluhaUmrti(int volba) throws IOException, ParseException {
        switch (volba) {
            case 1:
                Vykresleni umrti = new Vykresleni(data);
                umrti.VykresliUmrti();
                konecVyberu = true;
                break;
            case 2:
                System.out.print("Zadej počet dnu: ");
                int pocet = sc.nextInt();
                Vykresleni umrti2 = new Vykresleni(data, pocet);
                umrti2.VykresliUmrti();
                konecVyberu = true;
                break;
            case 3:
                konecVyberu = true;
                break;
            default:
                MenuStr.clearScreen();
                System.out.println("Zadali jste špatnou hodnotu");
        }
    }
    private static void obsluhaTesty(int volba) throws IOException, ParseException {
        switch (volba) {
            case 1:
                Vykresleni testy = new Vykresleni(data);
                testy.VykresliTesty();
                konecVyberu = true;
                break;
            case 2:
                System.out.print("Zadej počet dnu: ");
                int pocet = sc.nextInt();
                Vykresleni testy2 = new Vykresleni(data,pocet);
                testy2.VykresliUmrti();
                konecVyberu = true;
                break;
            case 3:
                konecVyberu = true;
                break;
            default:
                MenuStr.clearScreen();
                System.out.println("Zadali jste špatnou hodnotu");
        }
    }
    private static void obsluhaVyleceni(int volba) throws IOException, ParseException {
        switch (volba) {
            case 1:
                Vykresleni nakazeni = new Vykresleni(data);
                nakazeni.VykresliNakazene();
                konecVyberu = true;
                break;
            case 2:
                System.out.print("Zadej počet dnu: ");
                int pocet = sc.nextInt();
                Vykresleni nakazeni2 = new Vykresleni(data,pocet);
                nakazeni2.VykresliNakazene();
                konecVyberu = true;
                break;
            case 3:
                konecVyberu = true;
                break;
            default:
                MenuStr.clearScreen();
                System.out.println("Zadali jste špatnou hodnotu");
        }
    }
    
}
