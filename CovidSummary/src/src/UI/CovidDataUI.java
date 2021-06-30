package src.UI;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import src.APP.API;
import src.APP.ParseData;
import src.APP.Vykresleni;
import src.APP.dataVUT;
import src.UTILS.MenuStr;

public class CovidDataUI {
    /**
     * @author Jan Hartig - M18000104
     */

    static Scanner sc = new Scanner(System.in);
    public static boolean konecProgramu = false;
    public static List<dataVUT> data = new ArrayList<>();

    public static void fetchData() throws IOException, ParseException {
        data = ParseData.parseVUT(API.fetchApi().toString());
    };

    /**
     * 
     * @param args the command line arguments
     * @throws IOException
     * @throws ParseException
     */
    public static void main(String[] args) throws IOException, ParseException {
        fetchData();
        do {

            System.out.println(MenuStr.getMenu());
            int volba = nacteniVolby();
            obsluhaVolby(volba);
        } while (!konecProgramu);
    }

    /**
     * Načítani a kontrola volby menu z konzole
     * 
     * @return Navrat ověřené hodnoty
     */
    public static int nacteniVolby() {
        int volba = -1;
        System.out.println("Zadej zvolenou polozku menu: ");
        try {
            volba = sc.nextInt();
        } catch (InputMismatchException e) {
            volba = -1;
        } finally {
            sc.nextLine();
        }
        return volba;
    }

    /**
     * Obsluha a výběr položky v hlavním menu:
     * <p>
     * 1 - Pro zobrazení dat o umrtí
     * <p>
     * 2 - Pro zobrazení dat o testech
     * <p>
     * 3 - Pro zobrazení dat o vylecenych/nakazenych
     * 
     * @param volba Číslo představující položku v menu
     * @throws IOException
     * @throws ParseException
     */
    private static void obsluhaVolby(int volba) throws IOException, ParseException {
        switch (volba) {
            case 1:
                System.out.println(MenuStr.getMenuActual());
                int volbaUmrti = CovidDataUI.nacteniVolby();
                obsluhaUmrti(volbaUmrti);
                break;
            case 2:
                System.out.println(MenuStr.getMenuActual());
                int volbaTesty = CovidDataUI.nacteniVolby();
                obsluhaTesty(volbaTesty);
                break;
            case 3:
                System.out.println(MenuStr.getMenuActual());
                int volbaVyleceni = CovidDataUI.nacteniVolby();
                obsluhaVyleceni(volbaVyleceni);
                break;
            case 4:
                MenuStr.clearScreen();
                System.out.println("Ukončuji program");
                konecProgramu = true;
                break;
            default:
                MenuStr.clearScreen();
                System.out.println("Zadali jste špatnou hodnotu");
        }
    }

    /**
     * Obsluha a výběr položky ve výběru umrtí:
     * <p>
     * 1 - Pro vypsaní dat od začatku pandemie
     * <p>
     * 2 - Pro zadaní kolik dnu nazpět
     * 
     * @param volba Číslo představující položku v menu
     * @throws IOException
     * @throws ParseException
     */
    private static void obsluhaUmrti(int volba) throws IOException, ParseException {
        switch (volba) {
            case 1:
                Vykresleni umrti = new Vykresleni(data);
                umrti.VykresliUmrti();
                break;
            case 2:
                System.out.print("Zadej počet dnu: ");
                int pocet = sc.nextInt();
                Vykresleni umrti2 = new Vykresleni(data, pocet);
                umrti2.VykresliUmrti();
                break;
            default:
                MenuStr.clearScreen();
                System.out.println("Zadali jste špatnou hodnotu");
        }
    }

    /**
     * Obsluha a výběr položky ve výběru testy:
     * <p>
     * 1 - Pro vypsaní dat od začatku pandemie
     * <p>
     * 2 - Pro zadaní kolik dnu nazpět
     * 
     * @param volba Číslo představující položku v menu
     * @throws IOException
     * @throws ParseException
     */
    private static void obsluhaTesty(int volba) throws IOException, ParseException {
        switch (volba) {
            case 1:
                Vykresleni testy = new Vykresleni(data);
                testy.VykresliTesty();
                break;
            case 2:
                System.out.print("Zadej počet dnu: ");
                int pocet = sc.nextInt();
                Vykresleni testy2 = new Vykresleni(data, pocet);
                testy2.VykresliTesty();
                break;
            default:
                MenuStr.clearScreen();
                System.out.println("Zadali jste špatnou hodnotu");
        }
    }

    /**
     * Obsluha a výběr položky ve výběru nakažení/vyléčení:
     * <p>
     * 1 - Pro vypsaní dat od začatku pandemie
     * <p>
     * 2 - Pro zadaní kolik dnu nazpět
     * 
     * @param volba Číslo představující položku v menu
     * @throws IOException
     * @throws ParseException
     */
    private static void obsluhaVyleceni(int volba) throws IOException, ParseException {
        switch (volba) {
            case 1:
                Vykresleni nakazeni = new Vykresleni(data);
                nakazeni.VykresliNakazene();
                break;
            case 2:
                System.out.print("Zadej počet dnu: ");
                int pocet = sc.nextInt();
                Vykresleni nakazeni2 = new Vykresleni(data, pocet);
                nakazeni2.VykresliNakazene();
                break;
            default:
                MenuStr.clearScreen();
                System.out.println("Zadali jste špatnou hodnotu");
        }
    }
}