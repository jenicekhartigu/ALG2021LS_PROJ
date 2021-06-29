package src.UTILS;

public class MenuStr {

    public MenuStr() {
    }
    static StringBuilder menu = new StringBuilder()
        .append("Zobrazení dat o COVID-19\n")
        .append("Zadej:\n")
        .append(" 1. Pro zobrazení dat o umrtí\n")
        .append(" 2. Pro zobrazení dat o testech\n")
        .append(" 3. Pro zobrazení dat o vylecenych/nakazenych\n")
        .append(" 4. Pro zobrazení dat o očkovani\n")
        .append(" 5. Pro ukončeni programu")
        ;

    static StringBuilder menuActual = new StringBuilder()
        .append("Zadej:\n")
        .append(" 1. Pro vypsaní dat od začatku pandemie\n") 
        .append(" 2. Pro zadaní kolik dnu nazpět\n")
        .append(" 3. Pro návrat zpět do hlavního menu")
        ;
    static StringBuilder menuOckovani = new StringBuilder()
        .append("Zadej:\n")
        .append(" 1. Pro vypsaní celkových dat\n")
        .append(" 2. Pro vypsani dat o očkovani Pfizer\n")
        .append(" 3. Pro vypsani dat o očkovani Moderna\n")
        .append(" 4. Pro vypsani dat o očkovani AstraZeneca\n")
        .append(" 5. Pro vypsani dat o očkovani Janssen")
        ;

    public static StringBuilder getMenu() {
        clearScreen();
        return menu;
    }
    public static StringBuilder getMenuActual() {
        clearScreen();
        return menuActual;
    }
    public static StringBuilder getMenuOckovani() {
        clearScreen();
        return menuOckovani;
    }
    public static void clearScreen() {   
        System.out.print("\033[H\033[2J");   
        System.out.flush();   
    } 


}
