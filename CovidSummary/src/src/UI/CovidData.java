package src.UI;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import src.APP.DrawChart;
import src.UTILS.MenuStr;

public class CovidData {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws IOException, ParseException {

        int x = 0;
        while(x == 0) {
            
            System.out.println(MenuStr.getMenu());

            int vyber = sc.nextInt();
            MenuStr.clearScreen();
            switch (vyber) {
                case 1:
                int y = 0;
                while (y == 0) {
                System.out.println("Zobrazeni dat o umrtí");
                System.out.println(MenuStr.getMenuActual());

                int vyber1 = sc.nextInt();
                switch (vyber1) {
                    case 1:
                    DrawChart.vykresliUmrti(0);
                    y = 1;
                    MenuStr.clearScreen();
                        break;
                    case 2:
                    System.out.print("Zadej počet dnu: ");
                    int pocet = sc.nextInt();
                    DrawChart.vykresliUmrti(pocet);
                    y = 1;
                    MenuStr.clearScreen();
                        break;
                    default:
                    System.out.println("Zadali jste špatnou hodnotu");
                        break;
                    }
                }
                    break;
                case 2:
                int z = 0;
                while (z == 0) {
                System.out.println("Zobrazení dat o testech");
                System.out.println(MenuStr.getMenuActual());

                int vyber2 = sc.nextInt();
                switch (vyber2) {
                    case 1:
                    DrawChart.vykresliTesty(0);
                    z = 1;
                    MenuStr.clearScreen();
                        break;
                    case 2:
                    System.out.print("Zadej počet dnu: ");
                    int pocet = sc.nextInt();
                    DrawChart.vykresliTesty(pocet);
                    z = 1;
                    MenuStr.clearScreen();
                        break;
                    default:
                    MenuStr.clearScreen();
                    System.out.println("Zadali jste špatnou hodnotu");
                        break;
                    }
                }
                    break;
                case 3:
                int zy = 0;
                while (zy == 0) {
                System.out.println("Zobrazení dat o vylecených/nakazenych");
                System.out.println(MenuStr.getMenuActual());

                int vyber2 = sc.nextInt();
                switch (vyber2) {
                    case 1:
                    DrawChart.vykresliNakazene(0);
                    zy = 1;
                    MenuStr.clearScreen();
                        break;
                    case 2:
                    System.out.print("Zadej počet dnu: ");
                    int pocet = sc.nextInt();
                    DrawChart.vykresliNakazene(pocet);
                    zy = 1;
                    MenuStr.clearScreen();
                        break;
                    default:
                    MenuStr.clearScreen();
                    System.out.println("Zadali jste špatnou hodnotu");
                        break;
                    }
                }
                    break;
                    case 4:
                    int zz = 0;
                    while (zz == 0) {
                    System.out.println("Zobrazení dat ockovani");
                    System.out.println(MenuStr.getMenuOckovani());
    
                    int vyber2 = sc.nextInt();
                    switch (vyber2) {
                        case 1:
                        DrawChart.vykresliOckovani(0);
                        zz = 1;
                        MenuStr.clearScreen();
                            break;
                        case 2:
                        System.out.print("Zadej počet dnu: ");
                        int pocet = sc.nextInt();
                        DrawChart.vykresliOckovani(pocet);
                        zz = 1;
                        MenuStr.clearScreen();
                            break;
                        
                            

                        default:
                        MenuStr.clearScreen();
                        System.out.println("Zadali jste špatnou hodnotu");
                            break;
                        }
                    }
                        break;
                case 5:
                    System.out.println("Ukončuji program");
                    x = 1;
                    break;
                default:
                MenuStr.clearScreen();
                System.out.println("Zadali jste špatnou hodnotu");
                    break;
            }
        }
        System.out.println("KONEC");
    }
    
}