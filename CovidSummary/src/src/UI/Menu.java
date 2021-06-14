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
            System.out.println("Zadej: \n 1. Pro vykresleni grafu \n 2. Pro nevim");
            int vyber = sc.nextInt();
            switch (vyber) {
                case 1:

                System.out.println("Vybral jsi 1");
                DrawChart.VykresliGraf();
                    break;
                case 2:
                System.out.println("Vyber 2");
                    break;

                default:
                x = 1;
                    break;
            }
        }
        System.out.println("konec");
    }
    
}