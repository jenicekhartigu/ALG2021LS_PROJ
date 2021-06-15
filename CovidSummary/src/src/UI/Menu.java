package src.UI;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import src.APP.DrawChart;

public class Menu {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws IOException, ParseException {

        int x = 0;
        while(x == 0) {
            System.out.println("Zadej: \n 1. Pro zobrazení dat o umrtí \n 2. Pro zobrazení dat o testech");
            int vyber = sc.nextInt();
            switch (vyber) {
                case 1:

                System.out.println("Zobrazeni dat o umrtí");
                System.out.println("Zadej: \n 1. Pro vypsaní dat od začatku pandemie \n 2. Pro zadaní kolik dnu nazpět");
                int vyber1 = sc.nextInt();
                switch (vyber1) {
                    case 1:
                    DrawChart.vykresliUmrti(0);
                        break;
                    case 2:
                    System.out.print("Zadej počet dnu: ");
                    int pocet = sc.nextInt();
                    DrawChart.vykresliUmrti(pocet);
                        break;
                    default:
                        break;
                }
                    break;
                case 2:
                System.out.println("Zobrazení dat o testech");
                System.out.println("Zadej: \n 1. Pro vypsaní dat od začatku pandemie \n 2. Pro zadaní kolik dnu nazpět");
                int vyber2 = sc.nextInt();
                switch (vyber2) {
                    case 1:
                    DrawChart.vykresliTesty(0);
                        break;
                    case 2:
                    System.out.print("Zadej počet dnu: ");
                    int pocet = sc.nextInt();
                    DrawChart.vykresliTesty(pocet);
                        break;
                    default:
                        break;
                }
                default:
                x = 1;
                    break;
            }
        }
        System.out.println("konec");
    }
    
}