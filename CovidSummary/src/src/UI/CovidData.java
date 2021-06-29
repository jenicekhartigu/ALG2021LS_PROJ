package src.UI;

import java.io.IOException;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import src.APP.DrawChart;
import src.UTILS.Choose;
import src.UTILS.MenuStr;

public class CovidData {
    static Scanner sc = new Scanner(System.in);
    private static boolean konecProgramu = false;
    
    public static void main(String[] args) throws IOException, ParseException {
        Choose data = new Choose();
        do {    
            
            System.out.println(MenuStr.getMenu());
            int volba = nacteniVolby();
            obsluhaVolby(volba);
        } while (!konecProgramu);
    }

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

    private static void obsluhaVolby(int volba) throws IOException, ParseException {
        switch (volba) {
            case 1:
                Choose.getUmrti();
                break;
            case 2:
                Choose.getTesty();
                break;
            case 3:
                Choose.getVylecene();
                break;
            case 4:
                DrawChart.vykresliOckovani(0);
                break;
            case 5:
                MenuStr.clearScreen();
                System.out.println("Ukončuji program");
                konecProgramu = true;
                break;
            default:
                MenuStr.clearScreen();
                System.out.println("Zadali jste špatnou hodnotu");
        }
    } 
}